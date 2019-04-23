package com.hu.service;

import com.hu.pojo.Employee;
import com.hu.util.common.PageModel;

import java.util.List;

/**
 * @Auther: xupc
 * @Date: 2019/4/23 10:02
 * @Description:
 */
public interface EmployeeService {
    List findEmployee(Employee employee, PageModel pageModel);

    Employee findEmployeeById(Integer id);

    void  updateEmployee(Employee employee);

    void deleteEmployee(Integer id);

    void saveEmployee(Employee employee);

}
