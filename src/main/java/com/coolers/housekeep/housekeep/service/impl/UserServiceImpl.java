package com.coolers.housekeep.housekeep.service.impl;

import com.coolers.housekeep.housekeep.constant.RetMsgConst;
import com.coolers.housekeep.housekeep.constant.RetTypeConst;
import com.coolers.housekeep.housekeep.constant.UserConst;
import com.coolers.housekeep.housekeep.dao.UserMapper;
import com.coolers.housekeep.housekeep.dto.BussinessException;
import com.coolers.housekeep.housekeep.po.User;
import com.coolers.housekeep.housekeep.po.UserExample;
import com.coolers.housekeep.housekeep.service.UserService;
import com.coolers.housekeep.housekeep.util.*;
import com.coolers.housekeep.housekeep.vo.SMSReq;
import com.coolers.housekeep.housekeep.vo.SMSRes;
import com.coolers.housekeep.housekeep.vo.UserReq;
import com.coolers.housekeep.housekeep.vo.UserRes;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public UserRes login(UserReq req) {
        User daoUser = userMapper.selectByPrimaryKey(req.getId());
        if (BaseUtil.isEmptyObject(daoUser)) {
            throw new BussinessException(RetTypeConst.BUSSINESS_ERR, RetMsgConst.USER_NOT_EXIST);
        }
        UserRes res = new UserRes();
        try {
            if (EncryptUtil.encryptByMD5(req.getPassword()).equals(daoUser.getPassword())) {
                BeanUtils.copyProperties(daoUser, res);
                Map<String, String> userMap = new HashMap<>();
                userMap.put("userId", res.getId());
                res.setToken(TokenUtil.createToken(userMap));
            } else {
                throw new BussinessException(RetTypeConst.BUSSINESS_ERR, RetMsgConst.PASSWORD_ERROR);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new BussinessException(RetTypeConst.SYSTEM_ERR, RetMsgConst.SYS_ERR);
        }
        return res;
    }

    @Override
    public UserRes register(UserReq req) {
        UserRes res = new UserRes();
        if (isExist(req.getId())) {
            throw new BussinessException(RetTypeConst.BUSSINESS_ERR, RetMsgConst.USER_EXIST);
        } else {
            User user = new User();
            BeanUtils.copyProperties(req, user);
            try {
                user.setPassword(EncryptUtil.encryptByMD5(user.getPassword()));
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
                user.setPassword(EncryptUtil.encryptByMD5(user.getPassword()));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            userMapper.updateByPrimaryKey(user);

        } else {
            throw new BussinessException(RetTypeConst.BUSSINESS_ERR, RetMsgConst.USER_NOT_EXIST);
        }
        return res;
    }

    @Override
    public UserRes unsubscribe(UserReq req) {
        UserRes res = new UserRes();
        if (isExist(req.getId())) {
            userMapper.deleteByPrimaryKey(req.getId());
        } else {
            throw new BussinessException(RetTypeConst.BUSSINESS_ERR, RetMsgConst.USER_NOT_EXIST);
        }
        return res;
    }


    public boolean isExist(String id) {
        return BaseUtil.isNotEmptyObject(userMapper.selectByPrimaryKey(id));
    }

    @Override
    public SMSRes getVerificationCode(SMSReq req) {
        if (redisUtil.exists(req.getPhone())) {
            throw new BussinessException(RetTypeConst.BUSSINESS_ERR, RetMsgConst.TOO_OFTEN);
        }
        String verificationCode = MathUtil.getRandomNum(UserConst.VERIFICATION_CODE_DIGIT);
        // 调用短信发送接口，发送记录入库
        // SMSRecord.buildParams
        // SMS.sendMessage(SMSRecord)
        redisUtil.set(req.getPhone(), verificationCode, UserConst.VERIFICATION_CODE_TIMEOUT);
        return new SMSRes(verificationCode);
    }

    /**
     * @param req req.department and req.role not null
     */
    @Override
    public UserRes queryUsers(UserReq req) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andDepartmentEqualTo(req.getDepartment()).andRoleEqualTo(req.getRole());
        UserRes userRes = new UserRes();
        userRes.setUsers(userMapper.selectByExample(userExample));
        return userRes;
    }


}
