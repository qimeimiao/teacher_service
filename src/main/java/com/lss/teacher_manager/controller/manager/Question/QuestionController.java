package com.lss.teacher_manager.controller.manager.Question;

import com.lss.teacher_manager.controller.BaseController;
import com.lss.teacher_manager.mapper.question.QuestionMapper;
import com.lss.teacher_manager.pojo.evaluation.QuestionDto;
import com.lss.teacher_manager.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/q")
public class QuestionController extends BaseController {
    @Autowired
    private QuestionService questionService;
//  查找所有题目 (用于评教时的题目展示)
    @PostMapping("/c")
    public List<QuestionDto> findallquestion(){
        return questionService.findallquestion();
    }

}
