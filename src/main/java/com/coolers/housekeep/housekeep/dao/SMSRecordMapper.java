package com.coolers.housekeep.housekeep.dao;

import com.coolers.housekeep.housekeep.po.SMSRecord;
import com.coolers.housekeep.housekeep.po.SMSRecordExample;
import com.coolers.housekeep.housekeep.po.SMSRecordKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SMSRecordMapper {
    long countByExample(SMSRecordExample example);

    int deleteByExample(SMSRecordExample example);

    int deleteByPrimaryKey(SMSRecordKey key);

    int insert(SMSRecord record);

    int insertSelective(SMSRecord record);

    List<SMSRecord> selectByExample(SMSRecordExample example);

    SMSRecord selectByPrimaryKey(SMSRecordKey key);

    int updateByExampleSelective(@Param("record") SMSRecord record, @Param("example") SMSRecordExample example);

    int updateByExample(@Param("record") SMSRecord record, @Param("example") SMSRecordExample example);

    int updateByPrimaryKeySelective(SMSRecord record);

    int updateByPrimaryKey(SMSRecord record);
}