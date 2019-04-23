package com.hu.service.impl;

import com.hu.dao.EmployeeDao;
import com.hu.pojo.Employee;
import com.hu.service.EmployeeService;
import com.hu.util.common.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xupc
 * @Date: 2019/4/23 10:04
 * @Description:
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeDao employeeDao;
    @Override
    public List findEmployee(Employee employee, PageModel pageModel) {
        Map map = new HashMap();
        map.put("employee",employee);
        int recordCount = employeeDao.count(map);
        pageModel.setRecordCount(recordCount);//定义模板最大数量
        if(recordCount>0){
            /** 开始分页查询数据：查询第几页的数据 */
            map.put("pageModel", pageModel);
        }
        List depts = employeeDao.selectByPage(map);
        //List employees =employeeDao(map);
        return depts;
    }

    @Override
    public Employee findEmployeeById(Integer id) {
        return employeeDao.selectById(id);
    }

    public void  updateEmployee(Employee employee){
         employeeDao.updateEmployee(employee);
    }

    public void deleteEmployee(Integer id){
        employeeDao.deleteEmployee(id);
    }


    public void saveEmployee(Employee employee) {
        employeeDao.save(employee);
    }
}
