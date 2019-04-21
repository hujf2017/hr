package com.hu.service.impl;

import com.hu.dao.UserDao;
import com.hu.pojo.User;
import com.hu.service.HrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: xupc
 * @Date: 2019/4/21 20:44
 * @Description:
 */

@Service
public class HrmServiceImpl implements HrmService {

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

}
