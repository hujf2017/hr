package com.hu.service;

import com.hu.pojo.User;

import java.util.List;
/**   
 * @Description: 人事管理服务层接口
 */
public interface HrmService {

	/**
	 * 用户登录
	 * @param  loginname
	 * @param  password
	 * @return User对象
	 * */
	User login(String loginname, String password);
}
