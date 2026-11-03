package com.itheima.tliaswebmanagement.mapper;

import com.itheima.tliaswebmanagement.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    //-------------------------原始分页方法-------------------------
    /**
     * 查询总记录数
     *
     * //@return 总记录数
     */
//    @Select("SELECT COUNT(*) FROM emp e left join dept d ON e.dept_id = d.id")
//    public long count();

    /**
     * 分页查询员工数据
     *
     * //@param start    起始页数
     * //@param pageSize 查询大小多少
     * //@return
     */
//    @Select("SELECT e.*,d.name dept_name /*这里给d.name起别名，是因为在emp中的name属性是员工名字，已经在emp中重新定义一个deptName*/ FROM emp e left join dept d on d.id = e.dept_id ORDER BY e.update_time DESC LIMIT #{start},#{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);

    //------------------------使用pagehelper进行分页

    /**
     * 使用pagehelper分页
     * @param name 姓名
     * @param gender 性别
     * @param begin 开始时间
     * @param end 结束时间
     * @return 返回一个装满符合条件员工的集合
     */
    //@Select("SELECT e.*,d.name dept_name /*这里给d.name起别名，是因为在emp中的name属性是员工名字，已经在emp中重新定义一个deptName*/ FROM emp e left join dept d on d.id = e.dept_id ORDER BY e.update_time DESC")
    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    @Options(useGeneratedKeys = true ,keyProperty = "id")//意思是获取主键，把“id”变成主键 --主键返回
    @Insert("INSERT INTO emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) "
    +"VALUES (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);



    void deleteById(List<Integer> ids);
}
