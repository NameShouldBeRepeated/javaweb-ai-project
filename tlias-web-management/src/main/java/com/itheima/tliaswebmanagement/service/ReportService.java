package com.itheima.tliaswebmanagement.service;

import com.itheima.tliaswebmanagement.pojo.ClazzCountOption;
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

    /**
     * 统计班级学生人数
     * @return
     */
    ClazzCountOption countClazzData();

    /**
     * 统计学生学历
     * @return
     */
    List<Map> getStudentDegreeData();
}
