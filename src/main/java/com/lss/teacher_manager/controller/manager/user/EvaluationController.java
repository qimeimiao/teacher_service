package com.lss.teacher_manager.controller.manager.user;

import com.lss.teacher_manager.controller.BaseController;
import com.lss.teacher_manager.pojo.evaluation.QuestionDto;
import com.lss.teacher_manager.pojo.user.ManagerUserDto;
import com.lss.teacher_manager.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager/evaluation")
public class EvaluationController extends BaseController {
    @Autowired
    private EvaluationService evaluationService;

//    //添加题目
//    @GetMapping("/addEvaluation")
//    public void addEvaluation(String question){
//        evaluationService.addEvaluation(question);
//        System.out.print(question);
//    }

    //添加题目
    @PostMapping("/addEvaluation")
    public String addEvaluation(@RequestBody QuestionDto questionDto){
//        QuestionDto questionDto = super.getRequestBody(requestBody, QuestionDto.class);
        System.out.print(questionDto.getQuestion());
        return successResult(evaluationService.addEvaluation(questionDto));

    }

    //查询评教题目
    @PostMapping("/getQuestion")
    public String getQuestion(){
        List<QuestionDto> queList=evaluationService.getQuestion();
        return listResult(queList);
    }

    //删除评教问题
    @GetMapping("/deleteQuestionById")
    public String deleteQuestionById(Integer qid){
        return successResult(evaluationService.deleteQuestionById(qid));
    }

    //修改评教问题
    @PostMapping("/updateQuestionById")
    public String updateQuestionById(@RequestBody QuestionDto questionDto){
        return successResult(evaluationService.updateQuestionById(questionDto));
    }

    //查询评教题目数量
    @PostMapping("/getQuestionCount")
    public String getQuestionCount(){
        return successResult(evaluationService.getQuestionCount());
    }


}
