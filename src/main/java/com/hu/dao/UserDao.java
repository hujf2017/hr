package com.hu.dao;

import com.hu.dao.provider.UserDynaSqlProvider;
import com.hu.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

import static com.hu.util.common.HrmConstants.USERTABLE;


/**   
 * @Description: UserMapper接口
 */
public interface UserDao {

	// 根据登录名和密码查询员工
	@Select("select * from "+USERTABLE+" where loginname = #{loginname} and password = #{password}")
	User selectByLoginnameAndPassword(
            @Param("loginname") String loginname,
            @Param("password") String password);

	@SelectProvider(type= UserDynaSqlProvider.class,method="count")
	Integer count(Map param);

	/*
	 * @Author xupc
	 * @Date 2019/4/22 10:31
	 * @return
	 * 问题2 ：此处改<User> 泛型为什么会导致报错
	 **/
	@SelectProvider(type= UserDynaSqlProvider.class,method="selectWhitParam")
	List<User> selectByPage(Map<String, Object>param);

	@Select("select * from "+USERTABLE+" where ID = #{id}")
	User selectById(Integer id);

	// 动态修改用户
	@SelectProvider(type=UserDynaSqlProvider.class,method="updateUser")
	void update(User user);

	@SelectProvider(type=UserDynaSqlProvider.class,method="insertUser")
	void save(User user);

	@Delete(" delete from "+USERTABLE+" where id = #{id} ")
	void deleteById(Integer id);
}
