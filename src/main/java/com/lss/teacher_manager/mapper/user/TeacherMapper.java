package com.lss.teacher_manager.mapper.user;

import com.lss.teacher_manager.mapper.BaseMapper;
import com.lss.teacher_manager.pojo.user.CourseDto;
import com.lss.teacher_manager.pojo.user.ManagerUserDto;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;


@Component
public interface TeacherMapper extends BaseMapper<ManagerUserDto> {
    //查询登录的用户信息
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "dName", column = "dept_name"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "deptId", column = "dept_id"),
    })
    @Select("SELECT m.*,d.dept_name,d.dept_id,r.role_name FROM manager_user m,t_role r,t_dept d WHERE m.user_id=#{userid} and m.dept_id=d.dept_id and m.role=r.role_id")
    ManagerUserDto getTeacher(String userid);

    //修改登录的用户信息
    @Update("update manager_user set username =#{username},mobile =#{mobile} where user_id =#{userId}")
    int updateUserInfo(ManagerUserDto managerUserDto);

    //查询登录的教师用户的科目
    @Select("select * from course where uid=#{uid}")
    CourseDto getTeacherCourse(String uid);
}
