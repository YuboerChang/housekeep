package com.coolers.housekeep.housekeep.service;

import com.coolers.housekeep.housekeep.vo.SMSReq;
import com.coolers.housekeep.housekeep.vo.SMSRes;
import com.coolers.housekeep.housekeep.vo.UserReq;
import com.coolers.housekeep.housekeep.vo.UserRes;

public interface UserService {
    public UserRes login(UserReq req);

    public UserRes register(UserReq req);

    public UserRes updateUser(UserReq req);

    public UserRes unsubscribe(UserReq req);

    public SMSRes getVerificationCode(SMSReq req);

    public UserRes queryUsers(UserReq req);

}
