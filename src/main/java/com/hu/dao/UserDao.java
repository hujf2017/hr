package com.hu.dao;

import com.hu.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
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
}
