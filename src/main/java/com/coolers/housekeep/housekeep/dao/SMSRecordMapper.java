package com.coolers.housekeep.housekeep.dao;

import com.coolers.housekeep.housekeep.po.SMSRecord;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SMSRecordMapper {
    int deleteByPrimaryKey(@Param("phoneNumber") String phoneNumber, @Param("recordTime") Date recordTime);

    int insert(SMSRecord record);

    SMSRecord selectByPrimaryKey(@Param("phoneNumber") String phoneNumber, @Param("recordTime") Date recordTime);

    List<SMSRecord> selectAll();

    int updateByPrimaryKey(SMSRecord record);
}