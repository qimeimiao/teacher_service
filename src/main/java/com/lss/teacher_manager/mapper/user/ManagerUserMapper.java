package com.lss.teacher_manager.mapper.user;

import com.lss.teacher_manager.mapper.BaseMapper;
import com.lss.teacher_manager.mapper.BaseProvider;
import com.lss.teacher_manager.pojo.user.ConnectionDto;
import com.lss.teacher_manager.pojo.user.CourseDto;
import com.lss.teacher_manager.pojo.user.ManagerUserDto;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public interface ManagerUserMapper extends BaseMapper<ManagerUserDto> {

    @Insert("insert into manager_user(user_id,username,password,dept_id,email,mobile,status,create_date," +
            "update_date,last_login_date,ssex,tab,theme,avatar,description)values" +
            "(#{userId},#{username},#{password},#{deptId},#{email},#{mobile},#{status},#{createDate},#{updateDate},#{lastLoginDate}," +
            "#{sex},#{tab},#{theme},#{avatar},#{description})")
    @Override
    void save(ManagerUserDto managerUserDto);


    @Delete("delete from manager_user where user_id =#{0}")
    @Override
    void delete(String userId);


//    @Update("update manager_user set password =#{0},update_date =#{updateDate} where id =#{1}")
//    void updatePassword(String password, String userId);


    @Update("update manager_user set password =#{password} where user_id =#{userId}")
    void updatePassword(ManagerUserDto managerUserDto);


    @Update("update manager_user set username =#{username},mobile =#{mobile}," +
            "email =#{email},dept_id =#{deptId},status=#{status},description =#{description} where user_id =#{userId}")
    @Override
    void update(ManagerUserDto managerUserDto);

    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "updateDate ", column = "update_date"),
            @Result(property = "lastLoginDate", column = "last_login_date")
    })
    @Select("select * from manager_user where user_id =#{0}")
    @Override
    ManagerUserDto queryById(String userId);


    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "updateDate", column = "update_date"),
            @Result(property = "lastLoginDate", column = "last_login_date")
    })
    @Select("select * from manager_user where username =#{0}")
    @Override
    ManagerUserDto queryByName(String username);

    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "updateDate", column = "update_date"),
            @Result(property = "lastLoginDate", column = "last_login_date")
    })
    @Select("select * from manager_user")
    List<ManagerUserDto>  getUsers();

    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "updateDate", column = "update_date"),
            @Result(property = "lastLoginDate", column = "last_login_date")

    })
    @Select("select manager_user.* from manager_user where manager_user.user_id in(SELECT course.uid from course ,`t_connection` where course.cid=`t_connection`.cid and `t_connection`.uid=#{0})")
    ManagerUserDto querytByid(String user_id);
//    @Select("select c.cid,c.cname,m.username,c.uid,c.deptid,d.dept_name from course c,manager_user m,t_dept d where c.uid=m.user_id and c.deptid=d.dept_id and c.cid in(SELECT `t_connection`.cid from `t_connection` where `t_connection`.uid=#{0})")
//    List<CourseDto> querycByid(String user_id);

    @Results({
            @Result(property = "tid", column = "user_id")
    })
    @Select("select c.*,d.dept_name,m.username,m.user_id,cou.cname from t_connection c,t_dept d,manager_user m ,course cou where c.cid=cou.cid and cou.deptid=d.dept_id and cou.uid=m.user_id and c.uid=#{0}")
    List<ConnectionDto> querycByid(String uid);

    @Select("select manager_user.* from manager_user where dept_id=#{dept_id} and role=1")
    List<ManagerUserDto> querytBydid(String dept_id);
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "updateDate", column = "update_date"),
            @Result(property = "lastLoginDate", column = "last_login_date")
    })
    @SelectProvider(type= ManagerUserMapper.CommonProvider.class,method="queryByPage")
    @Override
    List<ManagerUserDto> queryByPage(ManagerUserDto managerUserDto);

    class CommonProvider extends BaseProvider {

        public String queryByPage(ManagerUserDto m){
            SQL sql = new SQL().SELECT("m.*").FROM("manager_user m").WHERE("1=1");
            if (!StringUtils.isEmpty(m.getUserId())){
                sql.WHERE("m.user_id =#{userId}");
            }
            if (!StringUtils.isEmpty(m.getUsername())){
                sql.WHERE("m.username like concat('%',#{username},'%')");
            }
            if (!StringUtils.isEmpty(m.getMobile())){
                sql.WHERE("m.mobile =#{mobile}");
            }
            if (!StringUtils.isEmpty(m.getStatus())){
                sql.WHERE("m.status =#{status}");
            }

            return  sql.toString();
        }
    }

    //查询不同角色的所有用户信息
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "dName", column = "dept_name"),
    })
    @Select("select u.user_id,u.username,u.mobile,r.role_name,d.dept_name from manager_user u left join t_dept d on u.dept_id=d.dept_id left join t_role r on u.role=r.role_id where u.role=#{role}")
    List<ManagerUserDto> getUserInfoByRolr(String role);

    //查询课程
    @Results({
            @Result(property = "cid", column = "cid"),
            @Result(property = "cname", column = "cname"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "deptid", column = "deptid"),
    })

    @Select("select * from course")
    List<CourseDto> getCourse();

}
