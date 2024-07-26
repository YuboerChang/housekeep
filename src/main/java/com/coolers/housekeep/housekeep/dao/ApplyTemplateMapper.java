package com.coolers.housekeep.housekeep.dao;

import com.coolers.housekeep.housekeep.po.ApplyTemplate;
import com.coolers.housekeep.housekeep.po.ApplyTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApplyTemplateMapper {
    long countByExample(ApplyTemplateExample example);

    int deleteByExample(ApplyTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ApplyTemplate record);

    int insertSelective(ApplyTemplate record);

    List<ApplyTemplate> selectByExample(ApplyTemplateExample example);

    ApplyTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ApplyTemplate record, @Param("example") ApplyTemplateExample example);

    int updateByExample(@Param("record") ApplyTemplate record, @Param("example") ApplyTemplateExample example);

    int updateByPrimaryKeySelective(ApplyTemplate record);

    int updateByPrimaryKey(ApplyTemplate record);
}