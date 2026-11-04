package com.itheima.tliaswebmanagement.service;

import com.itheima.tliaswebmanagement.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计员工职位人数
     * @return
     */
    JobOption getEmpJobData();

    /**
     * 统计员工性别信息
     * @return
     */
    List<Map<String, Object>> countEmpGenderData();
}
