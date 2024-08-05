package com.coolers.housekeep.housekeep.service.impl;

import com.coolers.housekeep.housekeep.constant.RetMsgConst;
import com.coolers.housekeep.housekeep.constant.RetTypeConst;
import com.coolers.housekeep.housekeep.dao.ApplyTemplateCustomMapper;
import com.coolers.housekeep.housekeep.dao.ApplyTemplateMapper;
import com.coolers.housekeep.housekeep.dao.ApplyTemplateStepCustomMapper;
import com.coolers.housekeep.housekeep.dao.ApplyTemplateStepMapper;
import com.coolers.housekeep.housekeep.dto.BussinessException;
import com.coolers.housekeep.housekeep.dto.Page;
import com.coolers.housekeep.housekeep.po.*;
import com.coolers.housekeep.housekeep.service.ApplyTemplateService;
import com.coolers.housekeep.housekeep.util.BaseUtil;
import com.coolers.housekeep.housekeep.vo.ApplyTemplateReq;
import com.coolers.housekeep.housekeep.vo.ApplyTemplateRes;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ApplyTemplateServiceImpl implements ApplyTemplateService {
    @Autowired
    ApplyTemplateMapper applyTemplateMapper;
    @Autowired
    ApplyTemplateStepMapper applyTemplateStepMapper;
    @Autowired
    ApplyTemplateCustomMapper applyTemplateCustomMapper;
    @Autowired
    ApplyTemplateStepCustomMapper applyTemplateStepCustomMapper;


    /**
     * @param req id空时查全部template，非空时查单个template的step
     */
    @Override
    public ApplyTemplateRes queryApplyTemplate(ApplyTemplateReq req) {
        ApplyTemplateRes res = new ApplyTemplateRes();
        Page page = new Page(req.getPageNum(), req.getPageSize());
        if (BaseUtil.isEmptyMathObject(req.getId())) {
            ApplyTemplateExample applyTemplateExample = new ApplyTemplateExample();
            res.setTotal(applyTemplateMapper.countByExample(applyTemplateExample));
            res.setApplyTemplateList(applyTemplateCustomMapper.selectByExampleAndPage(page, applyTemplateExample));
        } else {
            ApplyTemplateStepExample applyTemplateStepExample = new ApplyTemplateStepExample();
            res.setTotal(applyTemplateStepMapper.countByExample(applyTemplateStepExample));
            res.setApplyTemplateStepList(applyTemplateStepCustomMapper.selectByExampleAndPage(page, applyTemplateStepExample));
        }
        return res;
    }

    @Override
    @Transactional
    public ApplyTemplateRes updateApplyTemplate(ApplyTemplateReq req) {
        ApplyTemplate po = new ApplyTemplate();
        BeanUtils.copyProperties(req, po);
        po.setCreateTime(new Date());
        applyTemplateMapper.updateByPrimaryKey(po);
        if (BaseUtil.isNotEmptyCollection(req.getStepList())) {
            for (ApplyTemplateStep step : req.getStepList()) {
                applyTemplateStepMapper.updateByPrimaryKey(step);
            }
        }
        return new ApplyTemplateRes();
    }

    @Override
    @Transactional
    public ApplyTemplateRes addApplyTemplate(ApplyTemplateReq req) {
        if (BaseUtil.isNotEmptyObject(applyTemplateMapper.selectByPrimaryKey(req.getId()))) {
            throw new BussinessException(RetTypeConst.BUSSINESS_ERR, RetMsgConst.TEMPLATE_EXIST);
        }
        if (BaseUtil.isEmptyCollection(req.getStepList())) {
            throw new BussinessException(RetTypeConst.BUSSINESS_ERR, RetMsgConst.TEMPLATE_STEP_MISS);
        }
        ApplyTemplate po = new ApplyTemplate();
        BeanUtils.copyProperties(req, po);
        po.setCreateTime(new Date());
        applyTemplateMapper.insert(po);
        for (ApplyTemplateStep step : req.getStepList()) {
            applyTemplateStepMapper.insert(step);
        }
        return new ApplyTemplateRes();
    }

    @Override
    @Transactional
    public ApplyTemplateRes deleteApplyTemplate(ApplyTemplateReq req) {
        applyTemplateMapper.deleteByPrimaryKey(req.getId());
        ApplyTemplateStepExample applyTemplateStepExample = new ApplyTemplateStepExample();
        applyTemplateStepExample.createCriteria().andTemplateIdEqualTo(req.getId());
        applyTemplateStepMapper.deleteByExample(applyTemplateStepExample);
        return new ApplyTemplateRes();
    }

    @Override
    public ApplyTemplateRes queryTemplateStep(ApplyTemplateReq req) {
        ApplyTemplateStepKey applyTemplateStepKey = new ApplyTemplateStepKey();
        applyTemplateStepKey.setStepId(req.getStepId());
        applyTemplateStepKey.setTemplateId(req.getId());
        ApplyTemplateRes applyTemplateRes = new ApplyTemplateRes();
        applyTemplateRes.setApplyTemplateStep(applyTemplateStepMapper.selectByPrimaryKey(applyTemplateStepKey));
        return applyTemplateRes;
    }

}
