package com.lss.teacher_manager.pojo.user;

import lombok.Data;

@Data
public class CourseDto {
//    课程id
    private int cid;


    //    课程名
    private String cname;
//    教师工号
    private String uid;
//    院系id
    private String deptid;

    private String username;
    private String dept_name;
    private  ConnectionDto connectionDto;
}
