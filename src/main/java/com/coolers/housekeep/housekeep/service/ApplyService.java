package com.coolers.housekeep.housekeep.service;

import com.coolers.housekeep.housekeep.vo.ApplyReq;
import com.coolers.housekeep.housekeep.vo.ApplyRes;

public interface ApplyService {
    public ApplyRes queryApply(ApplyReq req);

    public ApplyRes updateApply(ApplyReq req);

    public ApplyRes addApply(ApplyReq req);

    public ApplyRes deleteApply(ApplyReq req);

}
