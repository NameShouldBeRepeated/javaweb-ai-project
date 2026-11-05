package com.itheima.tliaswebmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzCountOption {
    private List clazzList;//班级列表
    private List dataList;//人数列表
}
