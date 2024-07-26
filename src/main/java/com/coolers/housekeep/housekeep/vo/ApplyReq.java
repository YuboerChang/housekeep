package com.coolers.housekeep.housekeep.vo;

import com.coolers.housekeep.housekeep.po.ApplyStep;
import lombok.Data;

import java.util.List;

@Data
public class ApplyReq extends BaseReq {
    public Integer id;
    private Integer templateId;
    private String name;
    private String introduce;
    private String creatorId;
    private Boolean isFinish;
    private List<ApplyStep> steps;
}
