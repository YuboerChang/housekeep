package com.coolers.housekeep.housekeep.service.impl;

import com.coolers.housekeep.housekeep.constant.UserConst;
import com.coolers.housekeep.housekeep.dao.UserMapper;
import com.coolers.housekeep.housekeep.po.User;
import com.coolers.housekeep.housekeep.service.UserService;
import com.coolers.housekeep.housekeep.util.Encrypt;
import com.coolers.housekeep.housekeep.util.Method;
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
            res.setMsg(UserConst.USER_EXIST);
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
            res.setMsg(UserConst.USER_NOT_EXIST);
        }
        return res;
    }

    @Override
    public UserRes unsubscribe(UserReq req) {
        UserRes res = new UserRes();
        if (isExist(req.getId())) {
            userMapper.deleteByPrimaryKey(req.getId());
        } else {
            res.setMsg(UserConst.USER_NOT_EXIST);
        }
        return res;
    }

    public boolean isExist(String id) {
        return Method.isNotEmptyObject(userMapper.selectByPrimaryKey(id));
    }
}
