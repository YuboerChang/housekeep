package com.coolers.housekeep.housekeep.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SMSRes extends BaseRes {
    private String verificationCode;
}
