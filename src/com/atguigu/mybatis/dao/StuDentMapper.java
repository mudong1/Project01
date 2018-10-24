package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.bean.StuDent;

/**
 * @author lhd
 */
public interface StuDentMapper {
    public StuDent getStuDentById(Integer id);
}
