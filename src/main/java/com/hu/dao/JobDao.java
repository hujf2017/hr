package com.hu.dao;

import com.hu.dao.provider.DeptDynaSqlProvider;
import com.hu.dao.provider.JobDynaSqlProvider;
import com.hu.dao.provider.UserDynaSqlProvider;
import com.hu.pojo.Job;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static com.hu.util.common.HrmConstants.JOBTABLE;
import static com.hu.util.common.HrmConstants.USERTABLE;

/**
 * @Auther: xupc
 * @Date: 2019/4/22 19:19
 * @Description:
 */
public interface JobDao {
    @SelectProvider(type= JobDynaSqlProvider.class,method="selectWhitParam")
    List <Job>selectByPage(Map<String, Object>params);

    @SelectProvider(type= JobDynaSqlProvider.class,method="count")
    Integer count(Map<String, Object>params);

    @Select("select * from "+JOBTABLE+" where ID = #{id}")
    Job selectById(Integer id);

    // 动态修改职位
    @SelectProvider(type=JobDynaSqlProvider.class,method="updateJob")
    Job modifyJob(Job job);

    @Delete(" delete from "+JOBTABLE+" where id = #{id} ")
    void deleteJob(Integer id);

    @SelectProvider(type= JobDynaSqlProvider.class,method="insertJob")
    void insertJob(Job job);

}
