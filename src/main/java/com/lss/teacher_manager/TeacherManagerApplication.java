package com.lss.teacher_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.lss.teacher_manager.mapper")
//@ComponentScan(basePackages = { "com.lss.teacher_manager"})
public class TeacherManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeacherManagerApplication.class, args);
        System.out.println("启动成功");
    }

}
