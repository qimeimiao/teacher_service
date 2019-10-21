package com.lss.teacher_manager.controller.manager.user;

import com.lss.teacher_manager.controller.BaseController;
import com.lss.teacher_manager.pojo.user.CourseDto;
import com.lss.teacher_manager.pojo.user.ManagerUserDto;
import com.lss.teacher_manager.service.ManagerUserService;
import com.lss.teacher_manager.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import java.util.Map;

@RestController
@RequestMapping("/manager/teacher")
public class TeacherController extends BaseController {
    @Autowired
    TeacherService teacherService;

    @Autowired
    ManagerUserService managerUserService;

    //查询教师
    @GetMapping("/getTeacherByid")
    public String getTeacherByid(){
        System.out.println(teacherService.getTeacherByid());
       return successResult(teacherService.getTeacherByid());
    }

    //修改登录的用户的信息
    @PostMapping("/updateUserInfo")
    public String updateUserInfo(@RequestBody ManagerUserDto managerUserDto){
        return successResult(teacherService.updateUserInfo(managerUserDto));
    }

    //查询登录的教师用户的科目
    @GetMapping("/getTeacherCourse")
    public String getTeacherCourse(String uid){
        return successResult(teacherService.getTeacherCourse(uid));
    }
}
