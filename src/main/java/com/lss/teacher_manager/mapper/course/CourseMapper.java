package com.lss.teacher_manager.mapper.course;

import com.lss.teacher_manager.pojo.user.CourseDto;
import com.lss.teacher_manager.pojo.user.ManagerUserDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public interface CourseMapper {
    @Insert("insert into course(cname,uid,deptid) values(#{cname},#{uid},#{deptid})")
    int addcourse(CourseDto courseDto);


    @Select("select c.cid,c.cname,t.username,c.uid,c.deptid,d.dept_name from course c left join manager_user t on c.uid=t.user_id left join t_dept d on c.deptid=d.dept_id")
    List<CourseDto> findallcourse();

    @Delete("delete from course where cid=#{0}")
    int deletebyid(int cid);

    @Update("UPDATE course SET cname=#{cname},deptid=#{deptid},uid=#{uid} where cid=#{cid}")
    int updatebyid(CourseDto courseDto);

    //查询没有排课的老师
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username")
    })
    @Select("select u.user_id,u.username from manager_user u where not exists(select 1 from course c where u.user_id=c.uid) and u.role=1")
    List<ManagerUserDto> getNoCourseTeacher();

    //修改教师部门
    @Update("update manager_user set dept_id=#{dept_id} where user_id=#{user_id}")
    int updateTeacherDept(int dept_id,String user_id);

}

