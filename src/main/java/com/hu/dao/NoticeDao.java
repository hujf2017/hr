package com.hu.dao;

import com.hu.dao.provider.JobDynaSqlProvider;
import com.hu.dao.provider.NoticeDynaSqlProvider;
import com.hu.pojo.Notice;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static com.hu.util.common.HrmConstants.NOTICETABLE;

/**
 * @Auther: xupc
 * @Date: 2019/4/23 13:53
 * @Description:
 */
public interface NoticeDao {

    // 动态查询
    @SelectProvider(type= NoticeDynaSqlProvider.class,method="selectWhitParam")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
            @Result(column="USER_ID",property="user",
                    one=@One(select="com.hu.dao.UserDao.selectById",
                            fetchType= FetchType.EAGER))
    })
    List<Notice> selectNotice(Map<String ,Object> map);

    @SelectProvider(type= NoticeDynaSqlProvider.class,method="count")
    Integer count(Map<String, Object>params);

    @Delete("delete from "+NOTICETABLE+" where id = #{id} ")
    void deleteNotice(Integer id);

    @SelectProvider(type= NoticeDynaSqlProvider.class,method="updateNotice")
    void updateNotice(Notice notice);

    @Select("select * from "+NOTICETABLE+" where id = #{id}")
    Notice selectNoticeById(Integer id);

    @SelectProvider(type= NoticeDynaSqlProvider.class,method="insertNotice")
    void addNotice(Notice notice);
}
