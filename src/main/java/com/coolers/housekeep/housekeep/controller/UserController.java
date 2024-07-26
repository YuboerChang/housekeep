package com.coolers.housekeep.housekeep.controller;

import com.coolers.housekeep.housekeep.service.UserService;
import com.coolers.housekeep.housekeep.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public UserRes login(UserReq req) {
        return userService.login(req);
    }

    @PostMapping("/register")
    public UserRes register(UserReq req) {
        return userService.register(req);
    }

    @PostMapping("/unsubscribe")
    public UserRes unsubscribe(UserReq req) {
        return userService.unsubscribe(req);
    }

    @PostMapping("/updateUser")
    public UserRes updateUser(UserReq req) {
        return userService.updateUser(req);
    }

    @PostMapping("/getVerificationCode")
    public SMSRes getVerificationCode(SMSReq req) {
        return userService.getVerificationCode(req);
    }

    @PostMapping("/queryUsers")
    public UserRes queryUsers(UserReq req) {
        return userService.queryUsers(req);
    }

}
