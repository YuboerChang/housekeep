package com.coolers.housekeep.housekeep.dto;

import lombok.Data;

@Data
public class VerificationCode {
    private String phone;
    private String code;
}
