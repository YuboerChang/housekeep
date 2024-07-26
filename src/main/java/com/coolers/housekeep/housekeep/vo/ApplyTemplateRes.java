package com.coolers.housekeep.housekeep.vo;

import com.coolers.housekeep.housekeep.po.ApplyTemplate;
import com.coolers.housekeep.housekeep.po.ApplyTemplateStep;
import lombok.Data;

import java.util.List;

@Data
public class ApplyTemplateRes extends BaseRes {
    private ApplyTemplate applyTemplate;
    private ApplyTemplateStep applyTemplateStep;
    private List<ApplyTemplate> applyTemplateList;
    private List<ApplyTemplateStep> applyTemplateStepList;
}
