package com.coolers.housekeep.housekeep.vo;

import com.coolers.housekeep.housekeep.po.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserRes extends BaseRes {
    private String id;
    private String password;
    private String name;
    private String introduce;
    private String phone;
    private String role;
    private String department;
    private byte[] picture;
    private List<User> users;
}
