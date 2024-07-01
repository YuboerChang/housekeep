package com.coolers.housekeep.housekeep.dao;

import com.coolers.housekeep.housekeep.po.Apply;
import java.util.List;

public interface ApplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Apply record);

    Apply selectByPrimaryKey(Integer id);

    List<Apply> selectAll();

    int updateByPrimaryKey(Apply record);
}