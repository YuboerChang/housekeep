package com.coolers.housekeep.housekeep.dao;

import com.coolers.housekeep.housekeep.po.ApplyTemplateStep;
import com.coolers.housekeep.housekeep.po.ApplyTemplateStepExample;
import com.coolers.housekeep.housekeep.po.ApplyTemplateStepKey;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ApplyTemplateStepMapper {
    long countByExample(ApplyTemplateStepExample example);

    int deleteByExample(ApplyTemplateStepExample example);

    int deleteByPrimaryKey(ApplyTemplateStepKey key);

    int insert(ApplyTemplateStep record);

    int insertSelective(ApplyTemplateStep record);

    List<ApplyTemplateStep> selectByExample(ApplyTemplateStepExample example);

    ApplyTemplateStep selectByPrimaryKey(ApplyTemplateStepKey key);

    int updateByExampleSelective(@Param("record") ApplyTemplateStep record, @Param("example") ApplyTemplateStepExample example);

    int updateByExample(@Param("record") ApplyTemplateStep record, @Param("example") ApplyTemplateStepExample example);

    int updateByPrimaryKeySelective(ApplyTemplateStep record);

    int updateByPrimaryKey(ApplyTemplateStep record);
}