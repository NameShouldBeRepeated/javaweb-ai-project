package com.itheima.tliaswebmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.tliaswebmanagement.exception.BusinessException;
import com.itheima.tliaswebmanagement.mapper.ClazzMapper;
import com.itheima.tliaswebmanagement.mapper.StudentMapper;
import com.itheima.tliaswebmanagement.pojo.Clazz;
import com.itheima.tliaswebmanagement.pojo.PageResult;
import com.itheima.tliaswebmanagement.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 分页查找
     *
     * @param name     班级名称
     * @param begin    范围匹配的开始时间(开课时间)
     * @param end      范围匹配的结束时间(结课时间)
     * @param page     分页页码
     * @param pageSize 每页的元素数量
     * @return
     */
    @Override
    public PageResult<Clazz> Page(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<Clazz> dataList = clazzMapper.list(name,begin,end);
        Page<Clazz> p = (Page<Clazz>)dataList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    /**
     * 根据ID删除班级信息
     *
     * @param id 班级ID
     */
    @Transactional
    @Override
    public void delete(Integer id) {
        Integer count = studentMapper.countByClazzId(id);
        if(count>0){
            throw new BusinessException("班级里有学生！无法删除");
        }
        clazzMapper.delete(id);
    }

    /**
     * 新增班级
     *
     * @param clazz 新增的班级类
     */
    @Override
    public void insert(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    /**
     * 根据班级ID查看班级信息
     *
     * @param id 班级ID
     * @return 查找到的班级类
     */
    @Override
    public Clazz getInfo(Integer id) {
        return clazzMapper.getInfo(id);
    }

    /**
     * 查找所有班级
     *
     * @return 一个包含所有班级的集合
     */
    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }

    /**
     * 修改班级信息
     *
     * @param clazz 传入的班级信息
     */
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }
}
