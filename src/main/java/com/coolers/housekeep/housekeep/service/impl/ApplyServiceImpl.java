package com.coolers.housekeep.housekeep.service.impl;

import com.coolers.housekeep.housekeep.dao.ApplyCustomMapper;
import com.coolers.housekeep.housekeep.dao.ApplyMapper;
import com.coolers.housekeep.housekeep.dao.ApplyStepCustomMapper;
import com.coolers.housekeep.housekeep.dao.ApplyStepMapper;
import com.coolers.housekeep.housekeep.dto.Page;
import com.coolers.housekeep.housekeep.po.Apply;
import com.coolers.housekeep.housekeep.po.ApplyExample;
import com.coolers.housekeep.housekeep.po.ApplyStep;
import com.coolers.housekeep.housekeep.po.ApplyStepExample;
import com.coolers.housekeep.housekeep.service.ApplyService;
import com.coolers.housekeep.housekeep.util.BaseUtil;
import com.coolers.housekeep.housekeep.vo.ApplyReq;
import com.coolers.housekeep.housekeep.vo.ApplyRes;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    ApplyMapper applyMapper;
    @Autowired
    ApplyStepMapper applyStepMapper;
    @Autowired
    ApplyCustomMapper applyCustomMapper;
    @Autowired
    ApplyStepCustomMapper applyStepCustomMapper;

    /**
     * @param req id空时查全部，非空时查单个的step
     */
    @Override
    public ApplyRes queryApply(ApplyReq req) {
        ApplyRes res = new ApplyRes();
        Page page = new Page(req.getPageNum(), req.getPageSize());
        if (BaseUtil.isEmptyMathObject(req.getId())) {
            ApplyExample applyExample = new ApplyExample();
            res.setTotal(applyMapper.countByExample(applyExample));
            res.setApplyList(applyCustomMapper.selectByExampleAndPage(page, applyExample));
        } else {
            ApplyStepExample applyStepExample = new ApplyStepExample();
            res.setTotal(applyStepMapper.countByExample(applyStepExample));
            res.setApplyStepList(applyStepCustomMapper.selectByExampleAndPage(page, applyStepExample));
        }
        return res;
    }

    /**
     * 更新当前步骤状态>建立下一步骤状态>通知相应人员
     */
    @Override
    @Transactional
    public ApplyRes updateApply(ApplyReq req) {
        // 更新当前流程，只更新完成标志 isFinish
        if (req.getIsFinish()) {
            Apply apply = new Apply();
            BeanUtils.copyProperties(req, apply);
            applyMapper.updateByPrimaryKeySelective(apply);
        }
        // 步骤更新
        ApplyStep step = req.getSteps().get(0);
        applyStepMapper.updateByPrimaryKeySelective(step);
        if (req.getSteps().size() > 1) {
            ApplyStep nextStep = req.getSteps().get(1);
            step.setCreateTime(new Date());
            applyStepMapper.insert(nextStep);
        }
        // 通知下一步骤处理人员，还没确定什么形式
        return new ApplyRes();
    }


    @Override
    @Transactional
    public ApplyRes addApply(ApplyReq req) {
        // 建立当前流程
        Apply apply = new Apply();
        BeanUtils.copyProperties(req, apply);
        apply.setCreateTime(new Date());
        applyMapper.insert(apply);
        // 步骤建立
        for (ApplyStep step : req.getSteps()) {
            step.setCreateTime(new Date());
            applyStepMapper.insert(step);
        }
        // 通知下一步骤处理人员，还没确定什么形式
        return new ApplyRes();
    }

    @Override
    @Transactional
    public ApplyRes deleteApply(ApplyReq req) {
        applyMapper.deleteByPrimaryKey(req.getId());
        ApplyStepExample applyStepExample = new ApplyStepExample();
        applyStepExample.createCriteria().andApplyIdEqualTo(req.getId());
        applyStepMapper.deleteByExample(applyStepExample);
        return new ApplyRes();
    }

}
