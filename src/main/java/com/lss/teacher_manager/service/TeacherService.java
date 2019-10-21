package com.lss.teacher_manager.service;

import com.lss.teacher_manager.mapper.user.TeacherMapper;
import com.lss.teacher_manager.pojo.user.CourseDto;
import com.lss.teacher_manager.pojo.user.ManagerUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherService extends BaseUserService{
    @Autowired
    private TeacherMapper teacherMapper;

    //查询教师
    public ManagerUserDto getTeacherByid(){
        String uid=super.getCurrentManagerUser().getUserId();
        return teacherMapper.getTeacher(uid);
    }

    //修改登录的用户信息
    public boolean updateUserInfo(ManagerUserDto managerUserDto){
        String uid=super.getCurrentManagerUser().getUserId();
        managerUserDto.setUserId(uid);
        int i= teacherMapper.updateUserInfo(managerUserDto);
        if(i>0){
            return true;
        }else {
            return false;
        }
    }

    //查询登录的教师用户的科目
    public CourseDto getTeacherCourse(String uid){
        return teacherMapper.getTeacherCourse(uid);
    }
}
