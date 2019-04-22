package com.hu.dao.provider;

import com.hu.pojo.Dept;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.hu.util.common.HrmConstants.DEPTTABLE;

/**
 * @Auther: xupc
 * @Date: 2019/4/22 14:02
 * @Description:
 */
public class DeptDynaSqlProvider {
    //分页查询
    public String selectWhitParam(final Map<String ,Object> params){
        String sql =  new SQL(){
            {
                SELECT("*");
                FROM(DEPTTABLE);
                if(params.get("dept") != null){
                    Dept dept = (Dept) params.get("dept");
                    if(dept.getName() != null && !dept.getName().equals("")){
                        WHERE("  name LIKE CONCAT ('%',#{dept.name},'%') ");
                    }
                }
            }
        }.toString();
        if(params.get("pageModel") != null){
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
        }
        return sql;
    }

    public String count(final Map<String ,Object> params){
        return new SQL(){
            {
                SELECT("count(*)");
                FROM(DEPTTABLE);
                if(params.get("dept") != null){
                    Dept dept = (Dept) params.get("dept");
                    if(dept.getName() != null && !dept.getName().equals("")){
                        WHERE("  name LIKE CONCAT ('%',#{dept.name},'%') ");
                    }
                }
            }
        }.toString();
    }

    public String  updateDept(final Dept dept){
        return new SQL(){
            {
                UPDATE(DEPTTABLE);
                if(dept.getName() != null){
                    SET(" name = #{name} ");
                }
                if(dept.getRemark() != null){
                    SET(" remark = #{remark} ");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }
    // 动态插入
    public String insertDept(final Dept dept){
        return new SQL(){
            {
                INSERT_INTO(DEPTTABLE);
                if(dept.getName() != null && !dept.getName().equals("")){
                    VALUES("name", "#{name}");
                }
                if(dept.getRemark() != null && !dept.getRemark().equals("")){
                    VALUES("remark", "#{remark}");
                }
            }
        }.toString();
    }
}
