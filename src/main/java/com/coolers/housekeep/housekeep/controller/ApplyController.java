package com.coolers.housekeep.housekeep.controller;

import com.coolers.housekeep.housekeep.service.ApplyService;
import com.coolers.housekeep.housekeep.vo.ApplyReq;
import com.coolers.housekeep.housekeep.vo.ApplyRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplyController {
    @Autowired
    ApplyService applyService;

    @PostMapping("/queryApply")
    public ApplyRes queryApply(ApplyReq req) {
        return applyService.queryApply(req);
    }

    @PostMapping("/updateApply")
    public ApplyRes updateApply(ApplyReq req) {
        return applyService.updateApply(req);
    }

    @PostMapping("/addApply")
    public ApplyRes addApply(ApplyReq req) {
        return applyService.addApply(req);
    }

    @PostMapping("/deleteApply")
    public ApplyRes deleteApply(ApplyReq req) {
        return applyService.deleteApply(req);
    }

}
