package com.itheima.tliaswebmanagement.service.impl;

import com.itheima.tliaswebmanagement.mapper.EmpMapper;
import com.itheima.tliaswebmanagement.mapper.StudentMapper;
import com.itheima.tliaswebmanagement.pojo.ClazzCountOption;
import com.itheima.tliaswebmanagement.pojo.JobOption;
import com.itheima.tliaswebmanagement.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 统计员工职位人数
     *
     * @return
     */
    @Override
    public JobOption getEmpJobData() {
        //先调用Mapper接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        //组装结果并返回
        List<Object> jobList = list.stream().map(data -> data.get("pos")).toList();
        List<Object> dataList = list.stream().map(data -> data.get("num")).toList();
        return new JobOption(jobList,dataList);
    }

    /**
     * 统计员工性别信息
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> countEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    /**
     * 统计班级学生人数
     *
     * @return
     */
    @Override
    public ClazzCountOption countClazzData() {
        List<Map<String,Object>> list = studentMapper.countClazzData();
        List<Object> clazzList = list.stream().map(data -> data.get("cname")).toList();
        List<Object> dataList = list.stream().map(data -> data.get("scount")).toList();
        return new ClazzCountOption(clazzList,dataList);
    }

    /**
     * 统计学生学历
     *
     * @return
     */
    @Override
    public List<Map> getStudentDegreeData() {
        return studentMapper.getStudentDegreeData();
    }
}
