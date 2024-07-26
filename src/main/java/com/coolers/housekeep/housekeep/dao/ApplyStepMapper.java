package com.coolers.housekeep.housekeep.dao;

import com.coolers.housekeep.housekeep.po.ApplyStep;
import com.coolers.housekeep.housekeep.po.ApplyStepExample;
import com.coolers.housekeep.housekeep.po.ApplyStepKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApplyStepMapper {
    long countByExample(ApplyStepExample example);

    int deleteByExample(ApplyStepExample example);

    int deleteByPrimaryKey(ApplyStepKey key);

    int insert(ApplyStep record);

    int insertSelective(ApplyStep record);

    List<ApplyStep> selectByExample(ApplyStepExample example);

    ApplyStep selectByPrimaryKey(ApplyStepKey key);

    int updateByExampleSelective(@Param("record") ApplyStep record, @Param("example") ApplyStepExample example);

    int updateByExample(@Param("record") ApplyStep record, @Param("example") ApplyStepExample example);

    int updateByPrimaryKeySelective(ApplyStep record);

    int updateByPrimaryKey(ApplyStep record);
}