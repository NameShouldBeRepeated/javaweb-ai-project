package com.itheima.tliaswebmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentQueryParam {
    private String name;      // 姓名
    private Integer degree;   // 学历
    private Integer clazzId;  // 班级ID
    private Integer page = 1;      // 页码，默认1
    private Integer pageSize = 10;  // 每页条数，默认10
}
