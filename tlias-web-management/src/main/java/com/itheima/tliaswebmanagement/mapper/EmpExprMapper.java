package com.itheima.tliaswebmanagement.mapper;

import com.itheima.tliaswebmanagement.pojo.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 员工工作经历Mapper
 */
@Mapper
public interface EmpExprMapper {
    /**
     * 批量保存工作经历
     * @param empExpr 工作经历JSON
     */
    void insertBatch(List<EmpExpr> empExpr);
}
