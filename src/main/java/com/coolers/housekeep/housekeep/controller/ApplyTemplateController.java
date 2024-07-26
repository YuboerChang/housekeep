package com.coolers.housekeep.housekeep.controller;

import com.coolers.housekeep.housekeep.service.ApplyTemplateService;
import com.coolers.housekeep.housekeep.service.UserService;
import com.coolers.housekeep.housekeep.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplyTemplateController {
    @Autowired
    ApplyTemplateService applyTemplateService;

    @PostMapping("/queryApplyTemplate")
    public ApplyTemplateRes queryApplyTemplate(ApplyTemplateReq req) {
        return applyTemplateService.queryApplyTemplate(req);
    }

    @PostMapping("/updateApplyTemplate")
    public ApplyTemplateRes updateApplyTemplate(ApplyTemplateReq req) {
        return applyTemplateService.updateApplyTemplate(req);
    }

    @PostMapping("/addApplyTemplate")
    public ApplyTemplateRes addApplyTemplate(ApplyTemplateReq req) {
        return applyTemplateService.addApplyTemplate(req);
    }

    @PostMapping("/deleteApplyTemplate")
    public ApplyTemplateRes deleteApplyTemplate(ApplyTemplateReq req) {
        return applyTemplateService.deleteApplyTemplate(req);
    }

    @PostMapping("/queryTemplateStep")
    public ApplyTemplateRes queryTemplateStep(ApplyTemplateReq req) {
        return applyTemplateService.queryTemplateStep(req);
    }

}
