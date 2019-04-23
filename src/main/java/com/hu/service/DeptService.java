package com.hu.service;

import com.hu.pojo.Dept;
import com.hu.util.common.PageModel;

import java.util.List;

/**
 * @Auther: xupc
 * @Date: 2019/4/22 14:06
 * @Description:
 */
public interface DeptService {
    /*
     *	部门服务接口
     * @Author xupc
     * @Date 2019/4/22 13:53
     * @return
     *
     **/

    List<Dept> findDept(Dept dept , PageModel pageModel);

    Dept findDeptById(Integer id);

    void modifyDept(Dept dept) ;

    void addDept(Dept dept);

    void removeDept(Integer id);

    List findAllDept();
}
