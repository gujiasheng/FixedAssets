package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.CheckRecordStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CheckRecordStatusMapper {

    int insert(CheckRecordStatus checkRecordStatus);

    //查询某个领用资产的所有流程
    List<CheckRecordStatus> selectAllNodeByRecordId(Integer checkRecordId);

    //查询某个领用资产的最新流程节点
    CheckRecordStatus selectNewNodeByRecordId(Integer checkRecordId);

    //根据recordId和nodeId查询备注内容
    List<String> selectRemarkByRidNid(@Param("recordId") Integer recordId);
}