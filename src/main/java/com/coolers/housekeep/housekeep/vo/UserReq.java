package com.coolers.housekeep.housekeep.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserReq extends BaseReq {
    private String id;

    private String password;

    private String name;

    private String introduce;

    private String phone;

    private String role;

    private String department;

    private byte[] picture;
}
