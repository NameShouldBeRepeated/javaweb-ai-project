package com.itheima.tliaswebmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.tliaswebmanagement.mapper.EmpExprMapper;
import com.itheima.tliaswebmanagement.mapper.EmpMapper;
import com.itheima.tliaswebmanagement.pojo.*;
import com.itheima.tliaswebmanagement.service.EmpLogService;
import com.itheima.tliaswebmanagement.service.EmpService;
import com.itheima.tliaswebmanagement.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;
    //----------------------------原始分页查询------------------
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        //获取总记录数
//        Long count = empMapper.count();
//        //获取分页查询结果
//        Integer start = (page - 1)*pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//        //返回封装结果
//        return new PageResult<>(count,rows);
//
//    }

    /**
     * 根据pagehelper分页查询
     *
     * @param empQueryParam@return 封装符合条件员工集合
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //设置分页参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //查询
        List<Emp> list = empMapper.list(empQueryParam.getName(),empQueryParam.getGender(),empQueryParam.getBegin(),empQueryParam.getEnd());
        //封装结果
        //这里强转不会报错，因为Page是ArrayList的子类，而ArrayList是List子类
        Page<Emp> p =(Page<Emp>)list;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

    /**
     * 保存员工数据
     *
     * @param emp JSON传来的员工对象
     */
    @Transactional(rollbackFor = {Exception.class})//事务注解 默认设置下（不带括号）RunTimeException才会回滚
    @Override
    public void save(Emp emp) {
        try {
            //保存员工的基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);
            //保存员工的工作经历
            List<EmpExpr> empExpr = emp.getExprList();
            if(!CollectionUtils.isEmpty(empExpr)){
                empExpr.forEach(item->{item.setEmpId(emp.getId());});
                empExprMapper.insertBatch(empExpr);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"员工信息"+emp);
            empLogService.insertLog(empLog);
        }

    }

    /**
     * 批量删除员工
     *
     * @param ids 员工id数组
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //删除员工基本信息
        empMapper.deleteById(ids);

        //删除员工工作经历信息
        empExprMapper.deleteByEmpId(ids);
    }

    /**
     * 根据员工ID查询员工信息
     *
     * @param id 员工ID
     * @return 一个员工对象
     */
    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 修改员工数据
     *
     * @param emp 前端传来的员工对象
     */
    @Transactional(rollbackFor = {Exception.class})//为方法添加事务
    @Override
    public void update(Emp emp) {
        //根据ID修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //根据ID修改员工工作经历
        //先删除这个员工的工作经历
        empExprMapper.deleteByEmpId(Arrays.asList(emp.getId()));
        //再写入新的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(item->{item.setEmpId(emp.getId());});//员工工作经历ID与员工ID绑定

        }
    }

    /**
     * 员工输入账号密码进行登录
     *
     * @param emp 前端传来的员工对象
     * @return
     */
    @Override
    public LoginInfo login(Emp emp) {
        //根据用户名和密码，查询员工的信息
        Emp e = empMapper.selectByUsernameAndPassword(emp);
        //判断员工是否存在
        if(e==null){
            return null;
        }
        log.info("登录成功，员工信息：{}",emp);
        //生成jwt令牌
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",e.getId());
        claims.put("username",e.getUsername());
        String Jwt = JwtUtils.generateJwt(claims);
        return new LoginInfo(emp.getId(), emp.getUsername(), emp.getName(),Jwt);
    }
}
