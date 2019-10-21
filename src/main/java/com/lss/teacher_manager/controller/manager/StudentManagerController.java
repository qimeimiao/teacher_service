package com.lss.teacher_manager.controller.manager;

import com.lss.teacher_manager.controller.BaseController;
import com.lss.teacher_manager.pojo.user.ConnectionDto;
import com.lss.teacher_manager.pojo.user.CourseDto;
import com.lss.teacher_manager.pojo.user.ManagerUserDto;
import com.lss.teacher_manager.service.ManagerUserService;
import com.lss.teacher_manager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager/user/sut")
public class StudentManagerController extends BaseController {
//    @Autowired
//    private ManagerUserMapper managerUserMapper;

    @Autowired
    private ManagerUserService mManagerUserService;
    @Autowired
    private StudentService studentService;
    //    通过id找到任课教师
    @PostMapping("/t")
    public ManagerUserDto findtByuserid(@RequestBody ManagerUserDto managerUserDto) {
        // System.out.println(mManagerUserService.querytByid(userid).getUsername());
        return mManagerUserService.querytByid(managerUserDto.getUserId());
    }
    //    通过id找到课程信息
    @GetMapping("/findcByuserid")
    public String findcByuserid(String uid) {
        return listResult(mManagerUserService.querycByid(uid));
    }
//    @PostMapping("/q")
//    public UserDto queryByid(@RequestParam String userId){
//        return mManagerUserService.querypByid(userId);
//    }

    //学生添加课程
    @PostMapping("/addCourseStudent")
    public String addCourseStudent(@RequestBody ConnectionDto connectionDto){
        return successResult(studentService.addCourseStudent(connectionDto));
    }

    //删除某学生所有课程
    @GetMapping("/delCourseStudentById")
    public String delCourseStudentById(String uid){
        return successResult(studentService.delCourseStudentById(uid));
    }

    //查询没有被学生选中的课程
    @GetMapping("/getNotSelectCourse")
    public String getNotSelectCourse(String uid){
        return listResult(studentService.getNotSelectCourse(uid));
    }

    //删除学生某门课程
    @GetMapping("/delCourse")
    public String delCourse(int conid){
        return successResult(studentService.delCourse(conid));
    }
}
