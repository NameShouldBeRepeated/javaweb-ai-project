package com.itheima.tliaswebmanagement.controller;

import com.itheima.tliaswebmanagement.pojo.ClazzCountOption;
import com.itheima.tliaswebmanagement.pojo.JobOption;
import com.itheima.tliaswebmanagement.pojo.Result;
import com.itheima.tliaswebmanagement.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 统计员工职位人数
     * @return
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计员工职位人数");
        JobOption jobOption =  reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 统计员工性别信息
     * @return
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别信息");
        List<Map<String,Object>> genderList = reportService.countEmpGenderData();
        return Result.success(genderList);
    }

    /**
     * 统计班级人数
     * @return
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("统计班级人数");
        ClazzCountOption clazzCountOption = reportService.countClazzData();
        return Result.success(clazzCountOption);
    }

    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计学生学历");
        List<Map> dataList = reportService.getStudentDegreeData();
        return Result.success(dataList);
    }
}
