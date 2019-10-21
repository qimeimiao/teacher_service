package com.lss.teacher_manager.controller.manager.course;

import com.lss.teacher_manager.controller.BaseController;
import com.lss.teacher_manager.pojo.user.CourseDto;
import com.lss.teacher_manager.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager/cc")
public class CourseController extends BaseController {
    @Autowired
    private CourseService courseService;
//    添加课程(返回1为成功0为失败)
    @PostMapping("/addcourse")
    public String addcourse(@RequestBody CourseDto courseDto){
        return successResult(courseService.addcourse(courseDto));
    }
//    查找所有课程
    @PostMapping("/findallcourse")
    public String findallcourse(){
       return listResult(courseService.findallcourse());
    }
//    修改指定id课程(返回1为成功0为失败)
    @PostMapping("/updatecoursebyid")
    public String updatecoursebyid(@RequestBody CourseDto courseDto){
        return successResult(courseService.updatebyid(courseDto));
    }
//    删除指定id课程(返回1为成功0为失败)
    @GetMapping("/deletecouresbyid")
    public String deletecouresbyid(Integer cid){
        return successResult(courseService.deletebyid(cid));
    }

    //查询没有排课的老师
    @GetMapping("/getNoCourseTeacher")
    public String getNoCourseTeacher(){
        return listResult(courseService.getNoCourseTeacher());
    }

    //修改教师院系
    @GetMapping("/updateTeacherDept")
    public String updateTeacherDept(Integer dept_id,String user_id){
        return successResult(courseService.updateTeacherDept(dept_id,user_id));
    }
}
