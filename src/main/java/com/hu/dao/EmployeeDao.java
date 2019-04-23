package com.hu.dao;

import com.hu.dao.provider.DeptDynaSqlProvider;
import com.hu.dao.provider.EmployeeDynaSqlProvider;
import com.hu.pojo.Dept;
import com.hu.pojo.Employee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static com.hu.util.common.HrmConstants.EMPLOYEETABLE;

/**
 * @Auther: xupc
 * @Date: 2019/4/23 09:50
 * @Description:
 */
public interface EmployeeDao {
    @SelectProvider(type= EmployeeDynaSqlProvider.class,method="count")
    Integer count(Map<String, Object>  map);

    /*
     * @Author xupc
     * @Date 2019/4/23 10:48
     * @return
     * 问题3 ：此处泛型 会影响数据库插入？
     **/
    @SelectProvider(type= EmployeeDynaSqlProvider.class,method="selectWhitParam")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="CARD_ID",property="cardId"),
            @Result(column="POST_CODE",property="postCode"),
            @Result(column="QQ_NUM",property="qqNum"),
            @Result(column="BIRTHDAY",property="birthday",javaType=java.util.Date.class),
            @Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
            @Result(column="DEPT_ID",property="dept",
                    one=@One(select="com.hu.dao.DeptDao.selectById",
                            fetchType= FetchType.EAGER)),
            @Result(column="JOB_ID",property="job",
                    one=@One(select="com.hu.dao.JobDao.selectById",
                            fetchType=FetchType.EAGER))
    })
    List<Employee>  selectByPage(Map<String, Object>map);

    @Select("select * from "+EMPLOYEETABLE+" where id = #{id}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="CARD_ID",property="cardId"),
            @Result(column="POST_CODE",property="postCode"),
            @Result(column="QQ_NUM",property="qqNum"),
            @Result(column="BIRTHDAY",property="birthday",javaType=java.util.Date.class),
            @Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
            @Result(column="DEPT_ID",property="dept",
                    one=@One(select="com.hu.dao.DeptDao.selectById",
                            fetchType= FetchType.EAGER)),
            @Result(column="JOB_ID",property="job",
                    one=@One(select="com.hu.dao.JobDao.selectById",
                            fetchType=FetchType.EAGER))
    })
    Employee selectById(Integer id);

    // 动态修改员工
    @SelectProvider(type=EmployeeDynaSqlProvider.class,method="updateEmployee")
    void updateEmployee(Employee employee);

    @Delete(" delete from "+EMPLOYEETABLE+" where id = #{id} ")
    void deleteEmployee(Integer id);

    @SelectProvider(type=EmployeeDynaSqlProvider.class,method="insertEmployee")
    void save(Employee employee);
}
