package com.coolers.housekeep.housekeep.service;

import com.coolers.housekeep.housekeep.vo.UserReq;
import com.coolers.housekeep.housekeep.vo.UserRes;

public interface UserService {
    public UserRes login(UserReq req);

    public UserRes register(UserReq req);

    public UserRes updateUser(UserReq user);

    public UserRes unsubscribe(UserReq user);
}
