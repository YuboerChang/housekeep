package com.coolers.housekeep.housekeep.util;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.Common;
import com.coolers.housekeep.housekeep.constant.BaseConst;
import com.coolers.housekeep.housekeep.constant.FormatConst;
import com.coolers.housekeep.housekeep.constant.UserConst;
import com.coolers.housekeep.housekeep.dao.SMSRecordMapper;
import com.coolers.housekeep.housekeep.po.SMSRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SMS {
    private final Client client;
    @Autowired
    private SMSRecordMapper smsRecordMapper;

    public SMS() throws Exception {
        Config config = new Config();
        config.accessKeyId = "";
        config.accessKeySecret = "";
        this.client = new Client(config);
    }

    /**
     * 短信发送服务，params的phoneNumbers可以支持多个号码
     * 同一批次的号码不能重复
     */
    public List<SMSRecord> sendMessage(SMSRecord smsRecord) throws Exception {
        List<SMSRecord> result = new ArrayList<>();
        SendSmsRequest sendReq = new SendSmsRequest().setPhoneNumbers(smsRecord.getPhoneNumber()).setTemplateCode(smsRecord.getTemplateCode()).setTemplateParam(smsRecord.getTemplateParams());
        SendSmsResponse sendRes = this.client.sendSms(sendReq);
        String code = sendRes.body.code;
        if (!Common.equalString(code, BaseConst.OK)) {
            return result;
        }
        smsRecord.setBizid(sendRes.body.bizId);
        // 等待 10 秒后查询结果
        Common.sleep(10000);
        // 查询结果
        List<String> phoneNums = com.aliyun.darabonbastring.Client.split(smsRecord.getPhoneNumber(), ",", -1);
        for (String phoneNum : phoneNums) {
            smsRecord.setPhoneNumber(phoneNum);
            List<QuerySendDetailsResponseBody.QuerySendDetailsResponseBodySmsSendDetailDTOsSmsSendDetailDTO> dtos = checkSendStatus(smsRecord);
            // 入库备份
            for (QuerySendDetailsResponseBody.QuerySendDetailsResponseBodySmsSendDetailDTOsSmsSendDetailDTO dto : dtos) {
                SMSRecord daoRecode = new SMSRecord();
                daoRecode.setPhoneNumber(phoneNum);
                BeanUtils.copyProperties(dto, daoRecode);
                smsRecordMapper.insert(daoRecode);
                result.add(daoRecode);
            }
        }
        return result;
    }

    /**
     * 查询短信发送结果，查询最多3次，每次间隔3秒，还未查询会按超时处理
     * 不需要处理分页，应当只有一条记录
     */
    public List<QuerySendDetailsResponseBody.QuerySendDetailsResponseBodySmsSendDetailDTOsSmsSendDetailDTO> checkSendStatus(SMSRecord smsRecord) throws Exception {
        int count = 1;
        List<QuerySendDetailsResponseBody.QuerySendDetailsResponseBodySmsSendDetailDTOsSmsSendDetailDTO> dtos = null;
        QuerySendDetailsRequest queryReq = new QuerySendDetailsRequest().setPhoneNumber(Common.assertAsString(smsRecord.getPhoneNumber())).setBizId(smsRecord.getBizid()).setSendDate(com.aliyun.darabonbatime.Client.format(FormatConst.YYYYMMDD));
        while (count < 4) {
            QuerySendDetailsResponse queryRes = client.querySendDetails(queryReq);
            dtos = queryRes.body.smsSendDetailDTOs.smsSendDetailDTO;
            for (QuerySendDetailsResponseBody.QuerySendDetailsResponseBodySmsSendDetailDTOsSmsSendDetailDTO dto : dtos) {
                if (UserConst.WATTING.equals(dto.getSendStatus().toString()) && ++count < 4) {
                    // 等待重试
                    Common.sleep(3000);
                }
            }
        }
        return dtos;
    }
}
