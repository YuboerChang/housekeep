package com.coolers.housekeep.housekeep.service.impl;

import com.coolers.housekeep.housekeep.constant.RetMessage;
import com.coolers.housekeep.housekeep.constant.RetType;
import com.coolers.housekeep.housekeep.constant.UserConst;
import com.coolers.housekeep.housekeep.dao.UserMapper;
import com.coolers.housekeep.housekeep.dto.BussinessException;
import com.coolers.housekeep.housekeep.po.User;
import com.coolers.housekeep.housekeep.service.UserService;
import com.coolers.housekeep.housekeep.util.Encrypt;
import com.coolers.housekeep.housekeep.util.MathUtil;
import com.coolers.housekeep.housekeep.util.Method;
import com.coolers.housekeep.housekeep.util.RedisUtil;
import com.coolers.housekeep.housekeep.vo.SMSReq;
import com.coolers.housekeep.housekeep.vo.SMSRes;
import com.coolers.housekeep.housekeep.vo.UserReq;
import com.coolers.housekeep.housekeep.vo.UserRes;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public UserRes login(UserReq req) {
        User daoUser = userMapper.selectByPrimaryKey(req.getId());
        UserRes res = new UserRes();
        try {
            if (Encrypt.encryptByMD5(req.getPassword()).equals(daoUser.getPassword())) {
                BeanUtils.copyProperties(daoUser, res);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public UserRes register(UserReq req) {
        UserRes res = new UserRes();
        if (isExist(req.getId())) {
            throw new BussinessException(RetType.BUSSINESS_ERR, RetMessage.USER_EXIST);
        } else {
            User user = new User();
            BeanUtils.copyProperties(req, user);
            try {
                user.setPassword(Encrypt.encryptByMD5(user.getPassword()));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            userMapper.insert(user);
        }
        return res;
    }

    @Override
    public UserRes updateUser(UserReq req) {
        UserRes res = new UserRes();
        if (isExist(req.getId())) {
            User user = new User();
            BeanUtils.copyProperties(req, user);
            try {
                user.setPassword(Encrypt.encryptByMD5(user.getPassword()));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            userMapper.updateByPrimaryKey(user);

        } else {
            throw new BussinessException(RetType.BUSSINESS_ERR, RetMessage.USER_NOT_EXIST);
        }
        return res;
    }

    @Override
    public UserRes unsubscribe(UserReq req) {
        UserRes res = new UserRes();
        if (isExist(req.getId())) {
            userMapper.deleteByPrimaryKey(req.getId());
        } else {
            throw new BussinessException(RetType.BUSSINESS_ERR, RetMessage.USER_NOT_EXIST);
        }
        return res;
    }


    public boolean isExist(String id) {
        return Method.isNotEmptyObject(userMapper.selectByPrimaryKey(id));
    }

    @Override
    public SMSRes getVerificationCode(SMSReq req) {
        if (redisUtil.exists(req.getPhone())) {
            throw new BussinessException(RetType.BUSSINESS_ERR, RetMessage.TOO_OFTEN);
        }
        String verificationCode = MathUtil.getRandomNum(UserConst.VERIFICATION_CODE_DIGIT);
        // 调用短信发送接口，发送记录入库
        // SMSRecord.buildParams
        // SMS.sendMessage(SMSRecord)
        redisUtil.set(req.getPhone(), verificationCode, UserConst.VERIFICATION_CODE_TIMEOUT);
        return new SMSRes(verificationCode);
    }
}
