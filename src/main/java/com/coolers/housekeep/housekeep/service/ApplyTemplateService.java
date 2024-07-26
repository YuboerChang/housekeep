package com.coolers.housekeep.housekeep.service;

import com.coolers.housekeep.housekeep.vo.*;

public interface ApplyTemplateService {
    public ApplyTemplateRes queryApplyTemplate(ApplyTemplateReq req);

    public ApplyTemplateRes updateApplyTemplate(ApplyTemplateReq req);

    public ApplyTemplateRes addApplyTemplate(ApplyTemplateReq req);

    public ApplyTemplateRes deleteApplyTemplate(ApplyTemplateReq req);

    public ApplyTemplateRes queryTemplateStep(ApplyTemplateReq req);

}
