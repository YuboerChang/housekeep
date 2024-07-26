package com.coolers.housekeep.housekeep.dao;

import com.coolers.housekeep.housekeep.dto.Page;
import com.coolers.housekeep.housekeep.po.ApplyExample;
import com.coolers.housekeep.housekeep.po.ApplyStep;
import com.coolers.housekeep.housekeep.po.ApplyTemplate;
import com.coolers.housekeep.housekeep.po.ApplyTemplateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplyTemplateCustomMapper {
    List<ApplyTemplate> selectByExampleAndPage(@Param("page") Page page, @Param("example") ApplyTemplateExample example);

}