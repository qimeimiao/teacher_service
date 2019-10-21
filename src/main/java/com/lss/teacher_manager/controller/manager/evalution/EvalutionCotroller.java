package com.lss.teacher_manager.controller.manager.evalution;

import com.lss.teacher_manager.controller.BaseController;
import com.lss.teacher_manager.pojo.evaluation.EvaluationDto;
import com.lss.teacher_manager.pojo.user.CourseDto;
import com.lss.teacher_manager.service.evalution.EvalutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager/e")
public class EvalutionCotroller extends BaseController {
    @Autowired
    private EvalutionService evalutionService;
//    查看自己已经评教的教师
    @GetMapping("/findevaluationbyid")
    public String findevaluationbyid(String id){
        return listResult(evalutionService.findEvaluationbyuid(id));
    }
//    将评教结果保存入数据库返回结果为0时表示已经评教过了，返回结果为1时表示成功，结果为2时表示失败
//    @PostMapping("/i")
//    public int insertEvluation(@RequestBody EvaluationDto evaluationDto){
//        if(evalutionService.findEvaluationbyuidandtid(evaluationDto)!=null){
//            return 0;
//        }else{
//
//
//        evalutionService.insertevalution(evaluationDto);
//        if (evalutionService.findEvaluationbyuidandtid(evaluationDto)!=null){
//            return 1;
//        }else
//        {
//            return 2;
//        }
//        }
//    }

    //    将评教结果保存入数据库
    @PostMapping("/insertEvluation")
    public String insertEvluation(@RequestBody EvaluationDto evaluationDto){
        return successResult(evalutionService.insertevalution(evaluationDto));
    }

//    查看教师自己被评教的结果
    @GetMapping("/findEvaluationbytid")
    public String findEvaluationbytid(String tid){
        return listResult(evalutionService.findEvaluationbytid(tid));
    }

    //删除评教的记录
    @GetMapping("/delAllEvaluation")
    public String delAllEvaluation(){
        return successResult(evalutionService.delAllEvaluation());
    }
    //查看给教师评教的结果
    @PostMapping("/findEvaluationbyuidandtid")
    public String findEvaluationbyuidandtid(@RequestBody EvaluationDto evaluationDto){
        return successResult(evalutionService.findEvaluationbyuidandtid(evaluationDto));
    }

    //教师查看同行
    @GetMapping("/findColleague")
    public String findColleague(Integer deptid,String uid){
        return listResult(evalutionService.findColleague(deptid,uid));
    }
}
