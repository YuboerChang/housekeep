package com.coolers.housekeep.housekeep.dao;

import com.coolers.housekeep.housekeep.po.WebLog;
import com.coolers.housekeep.housekeep.po.WebLogExample;
import com.coolers.housekeep.housekeep.po.WebLogWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebLogMapper {
    long countByExample(WebLogExample example);

    int deleteByExample(WebLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WebLogWithBLOBs record);

    int insertSelective(WebLogWithBLOBs record);

    List<WebLogWithBLOBs> selectByExampleWithBLOBs(WebLogExample example);

    List<WebLog> selectByExample(WebLogExample example);

    WebLogWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WebLogWithBLOBs record, @Param("example") WebLogExample example);

    int updateByExampleWithBLOBs(@Param("record") WebLogWithBLOBs record, @Param("example") WebLogExample example);

    int updateByExample(@Param("record") WebLog record, @Param("example") WebLogExample example);

    int updateByPrimaryKeySelective(WebLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(WebLogWithBLOBs record);

    int updateByPrimaryKey(WebLog record);
}