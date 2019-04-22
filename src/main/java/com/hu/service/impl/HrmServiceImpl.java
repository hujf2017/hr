package com.hu.service.impl;

import com.hu.dao.DeptDao;
import com.hu.dao.UserDao;
import com.hu.pojo.Dept;
import com.hu.pojo.User;
import com.hu.service.HrmService;
import com.hu.util.common.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xupc
 * @Date: 2019/4/21 20:44
 * @Description:
 */

@Service
public class HrmServiceImpl implements HrmService {
/*
 * 问题1：为什么这里只能用resource，是否和mybatis  xml配置有关
 **/
    @Resource
    private UserDao userDao;

    /*****************用户服务接口实现*************************************/
    /**
     * HrmServiceImpl接口login方法实现
     *  @see { HrmService }
     * */
    @Override
    public User login(String loginname, String password) {

		System.out.println(userDao);

        return userDao.selectByLoginnameAndPassword(loginname, password);
    }

    @Override
    public List<User> findUser(User user, PageModel pageModel)  {
        /** 当前需要分页的总数据条数  */
        Map<String,Object> params = new HashMap<>();
        params.put("user", user);
        int recordCount = userDao.count(params);
        pageModel.setRecordCount(recordCount);
        if(recordCount > 0){
            /** 开始分页查询数据：查询第几页的数据 */
            params.put("pageModel", pageModel);
        }
        List<User> users = userDao.selectByPage(params);

        return users;
    }

    @Override
    public User findUserById(Integer id) {
        return  userDao.selectById(id);
    }

    @Override
    public void modifyUser(User user) {
        userDao.update(user);
    }

    @Override
    public void addUser(User user) {
        userDao.save(user);
    }

    @Override
    public void removeUserById(Integer id) {
        userDao.deleteById(id);
    }



}
