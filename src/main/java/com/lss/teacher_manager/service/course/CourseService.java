package com.lss.teacher_manager.service.course;

import com.lss.teacher_manager.mapper.course.CourseMapper;
import com.lss.teacher_manager.pojo.user.CourseDto;
import com.lss.teacher_manager.pojo.user.ManagerUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService{
    @Autowired
    private CourseMapper courseMapper;


    public boolean addcourse(CourseDto courseDto) {
        int i=courseMapper.addcourse(courseDto);
        if(i>0){
            return true;
        }else {
            return false;
        }
    }

    public List<CourseDto> findallcourse() {
        return courseMapper.findallcourse();
    }

    public boolean deletebyid(int cid) {
        int i=courseMapper.deletebyid(cid);
        if(i>0){
            return true;
        }else {
            return false;
        }
    }

    public boolean updatebyid(CourseDto courseDto) {
        int i=courseMapper.updatebyid(courseDto);
        if(i>0){
            return true;
        }else {
            return false;
        }
    }

    //查询没有排课的老师
    public List<ManagerUserDto> getNoCourseTeacher(){
        return courseMapper.getNoCourseTeacher();
    }

    //修改教师院系
    public boolean updateTeacherDept(int dept_id,String user_id){
        int i=courseMapper.updateTeacherDept(dept_id,user_id);
        if(i>0){
            return true;
        }else {
            return false;
        }
    }
}
