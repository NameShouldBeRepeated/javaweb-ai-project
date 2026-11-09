package com.itheima.tliaswebmanagement.mapper;

import com.itheima.tliaswebmanagement.pojo.OperateLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogMapper {

    List<OperateLog> list();
}
