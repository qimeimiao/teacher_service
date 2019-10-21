package com.lss.teacher_manager.mapper.user;


import com.lss.teacher_manager.pojo.user.ConnectionDto;
import com.lss.teacher_manager.pojo.user.CourseDto;
import com.lss.teacher_manager.pojo.user.DeptDto;
import com.lss.teacher_manager.pojo.user.ManagerUserDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentMapper {

    //学生添加课程
    @Insert("insert into t_connection(uid,cid) values(#{uid},#{cid})")
    int addCourseStudent(ConnectionDto connectionDto);

    //删除某学生所有课程
    @Delete("delete from t_connection where uid=#{uid}")
    int delCourseStudentById(String uid);

    //查询没有被学生选中的课程
    @Select("select * from course where not exists(select 1 from t_connection where course.cid=t_connection.cid  and t_connection.uid=#{uid})")
    List<CourseDto> getNotSelectCourse(String uid);

    //删除学生某门课程
    @Delete("delete from t_connection where conid=#{conid}")
    int delCourse(int conid);



}
