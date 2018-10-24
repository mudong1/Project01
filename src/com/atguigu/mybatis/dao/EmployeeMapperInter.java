package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author lhd
 */
public interface EmployeeMapperInter {

    public Employee getEmployeeById(Integer id);

    /*告诉mybatis封装这个map的时候使用哪个属性作为map的key*/
    @MapKey("id")
    public Map<Integer,Employee> getMap(Integer id);

    public Employee getEmp(@Param("id") Integer id, @Param("lastName") String name);

    public Employee getEmpByMap(Map<String,Object> map);

    public void addEmp(Employee employee);

    public Long updateEmp(Employee employee);

    public abstract boolean deleteEmpById(Integer id);

}
