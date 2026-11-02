package com.itheima.tliaswebmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页结果封装类
 * @param <T> 封装的是什么类型（用户查询里则会脸面写的是用户类）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private long total;
    private List<T> rows;
}
