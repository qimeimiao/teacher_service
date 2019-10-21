package com.lss.teacher_manager.pojo.user;

import lombok.Data;

@Data
public class ConnectionDto {
//    联系id
    private int conid;
//    学生学号
    private String uid;
//    课程号
    private int cid;

    private String username;
    private String dept_name;
    private String cname;
    private String tid;


}
