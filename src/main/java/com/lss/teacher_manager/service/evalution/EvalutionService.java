package com.lss.teacher_manager.service.evalution;

import com.lss.teacher_manager.mapper.evalution.EvalutionMapper;
import com.lss.teacher_manager.pojo.evaluation.EvaluationDto;
import com.lss.teacher_manager.pojo.user.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvalutionService{
    @Autowired
    private EvalutionMapper evalutionMapper;

    public List<EvaluationDto> findEvaluationbyuid(String id) {
        return evalutionMapper.findEvaluationbyuid(id);
    }

    public EvaluationDto findEvaluationbyuidandtid(EvaluationDto evaluationDto) {
        return evalutionMapper.findEvaluationbyuidandtid(evaluationDto);
    }
    //将评教结果保存到数据库
    public boolean insertevalution(EvaluationDto evaluationDto) {
        int i=evalutionMapper.insertevalution(evaluationDto);
        if(i>0){
            return true;
        }else {
            return false;
        }

    }

    public List<EvaluationDto> findEvaluationbytid(String tid) {
        return evalutionMapper.findEvaluationbytid(tid);
    }

    //删除所有已经评价的教师
    public boolean delAllEvaluation(){
        int i=evalutionMapper.delAllEvaluation();
        if(i>0){
            return true;
        }else {
            return false;
        }
    }

    //查看同行
    public List<CourseDto> findColleague(int deptid,String uid){
        return evalutionMapper.findColleague(deptid,uid);
    }

}
