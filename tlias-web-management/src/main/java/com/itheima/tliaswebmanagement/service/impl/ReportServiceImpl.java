package com.itheima.tliaswebmanagement.service.impl;

import com.itheima.tliaswebmanagement.mapper.EmpMapper;
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
}
