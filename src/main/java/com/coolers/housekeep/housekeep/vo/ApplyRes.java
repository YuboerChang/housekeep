package com.coolers.housekeep.housekeep.vo;

import com.coolers.housekeep.housekeep.po.Apply;
import com.coolers.housekeep.housekeep.po.ApplyStep;
import lombok.Data;

import java.util.List;

@Data
public class ApplyRes extends BaseRes {
    private List<Apply> applyList;
    private List<ApplyStep> applyStepList;
}
