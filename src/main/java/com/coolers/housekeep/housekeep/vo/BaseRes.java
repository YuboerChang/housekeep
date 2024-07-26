package com.coolers.housekeep.housekeep.vo;

import com.coolers.housekeep.housekeep.constant.BaseConst;
import lombok.Data;

@Data
public class BaseRes {
    private String type = BaseConst.SUCCESS;
    private String msg;
    private long total;
}
