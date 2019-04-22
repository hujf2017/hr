package com.hu.service;

import com.hu.pojo.Dept;
import com.hu.pojo.User;
import com.hu.util.common.PageModel;

import java.util.List;
/**   
 * @Description: 人事管理服务层接口
 */
public interface HrmService {

	/**
	 * 用户服务接口
	 * @param  loginname
	 * @param  password
	 * @return User对象
	 * */
	User login(String loginname, String password);

	List<User> findUser(User user, PageModel pageModel);

	User findUserById(Integer id);

	void modifyUser(User user);

	void addUser(User user);

	void removeUserById(Integer id);


}
