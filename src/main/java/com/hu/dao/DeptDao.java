package com.hu.dao;

import com.hu.dao.provider.DeptDynaSqlProvider;
import com.hu.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static com.hu.util.common.HrmConstants.DEPTTABLE;
import static com.hu.util.common.HrmConstants.JOBTABLE;

/**
 * @Auther: xupc
 * @Date: 2019/4/22 13:57
 * @Description:
 */
public interface DeptDao {

    @SelectProvider(type= DeptDynaSqlProvider.class,method="selectWhitParam")
    List<Dept> selectByPage(Map<String, Object> params);

    @SelectProvider(type=DeptDynaSqlProvider.class,method="count")
    Integer count(Map<String, Object> params);

    @Select("select * from "+DEPTTABLE+" where ID = #{id}")
    Dept selectById(Integer id);

    // 动态修改用户
    @SelectProvider(type=DeptDynaSqlProvider.class,method="updateDept")
    void update(Dept dept);

    //动态插入部门
    @SelectProvider(type=DeptDynaSqlProvider.class,method="insertDept")
    void save(Dept dept);

    // 根据id删除部门
    @Delete(" delete from "+DEPTTABLE+" where id = #{id} ")
    void delete(Integer id);

    @Select("select * from "+DEPTTABLE+" ")
    List<Dept> selectAll();

}
