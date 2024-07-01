package com.coolers.housekeep.housekeep.dao;

import com.coolers.housekeep.housekeep.po.ApplyTemplate;
import java.util.List;

public interface ApplyTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ApplyTemplate record);

    ApplyTemplate selectByPrimaryKey(Integer id);

    List<ApplyTemplate> selectAll();

    int updateByPrimaryKey(ApplyTemplate record);
}