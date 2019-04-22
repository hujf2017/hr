package com.hu.service.impl;

import com.hu.dao.DeptDao;
import com.hu.pojo.Dept;
import com.hu.service.DeptService;
import com.hu.util.common.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xupc
 * @Date: 2019/4/22 14:06
 * @Description:
 */
@Service
public class DeptServiceImpl implements DeptService {


    @Resource
    private DeptDao deptDao;
    /*
     * 部门服务接口实现类
     * @Author hujf
     * @Date 2019/4/22 13:55
     * @return
     **/

    @Override
    public List<Dept> findDept(Dept dept, PageModel pageModel) {
        /** 当前需要分页的总数据条数  */
        Map<String,Object> params = new HashMap<>();
        params.put("dept", dept);
        int recordCount = deptDao.count(params);
        pageModel.setRecordCount(recordCount);//定义模板最大数量
        if(recordCount>0){
            /** 开始分页查询数据：查询第几页的数据 */
            params.put("pageModel", pageModel);
        }
        List depts = deptDao.selectByPage(params);
        return depts;
    }

    @Override
    public Dept findDeptById(Integer id) {
        return deptDao.selectById(id);
    }

    @Override
    public void modifyDept(Dept dept) {
        deptDao.update(dept);
    }

    @Override
    public void addDept(Dept dept) {
        deptDao.save(dept);
    }

    @Override
    public void removeDept(Integer id) {
        deptDao.delete(id);
    }
}
