package com.coolers.housekeep.housekeep.dao;

import com.coolers.housekeep.housekeep.dto.Page;
import com.coolers.housekeep.housekeep.po.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplyTemplateStepCustomMapper {
    List<ApplyTemplateStep> selectByExampleAndPage(@Param("page") Page page, @Param("example") ApplyTemplateStepExample example);

}