package com.coolers.housekeep.housekeep.dao;

import com.coolers.housekeep.housekeep.dto.Page;
import com.coolers.housekeep.housekeep.po.Apply;
import com.coolers.housekeep.housekeep.po.ApplyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplyCustomMapper {
    List<Apply> selectByExampleAndPage(@Param("page") Page page, @Param("example") ApplyExample example);

}