package com.coolers.housekeep.housekeep.dao;

import com.coolers.housekeep.housekeep.po.ApplyStep;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApplyStepMapper {
    int deleteByPrimaryKey(@Param("applyId") Integer applyId, @Param("stepId") Byte stepId);

    int insert(ApplyStep record);

    ApplyStep selectByPrimaryKey(@Param("applyId") Integer applyId, @Param("stepId") Byte stepId);

    List<ApplyStep> selectAll();

    int updateByPrimaryKey(ApplyStep record);
}