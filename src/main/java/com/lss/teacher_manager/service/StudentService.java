package com.lss.teacher_manager.service;

import com.lss.teacher_manager.mapper.user.StudentMapper;
import com.lss.teacher_manager.pojo.user.ConnectionDto;
import com.lss.teacher_manager.pojo.user.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    //学生添加课程
    public boolean addCourseStudent(ConnectionDto connectionDto){
        int i=studentMapper.addCourseStudent(connectionDto);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    //删除某学生所有课程
    public boolean delCourseStudentById(String uid){
        int i=studentMapper.delCourseStudentById(uid);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    //查询没有被学生选中的课程
    public List<CourseDto> getNotSelectCourse(String uid){
        return studentMapper.getNotSelectCourse(uid);
    }

    //删除学生某门课程
    public boolean delCourse(int conid){
        int i=studentMapper.delCourse(conid);
        if(i>0){
            return true;
        }else{
            return false;
        }
    }
}
