package com.coolers.housekeep.housekeep.vo;

import com.coolers.housekeep.housekeep.po.ApplyTemplateStep;
import lombok.Data;

import java.util.List;

@Data
public class ApplyTemplateReq extends BaseReq {
    public Integer id;
    private String name;
    private String introduce;
    private String creatorId;
    private Boolean isValid;
    private Byte stepId;
    private List<ApplyTemplateStep> stepList;
}
