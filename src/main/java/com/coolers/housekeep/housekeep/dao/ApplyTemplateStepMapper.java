package com.coolers.housekeep.housekeep.dao;

import com.coolers.housekeep.housekeep.po.ApplyTemplateStep;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApplyTemplateStepMapper {
    int deleteByPrimaryKey(@Param("templateId") Integer templateId, @Param("stepId") Byte stepId);

    int insert(ApplyTemplateStep record);

    ApplyTemplateStep selectByPrimaryKey(@Param("templateId") Integer templateId, @Param("stepId") Byte stepId);

    List<ApplyTemplateStep> selectAll();

    int updateByPrimaryKey(ApplyTemplateStep record);
}