package com.lss.teacher_manager.service;

import com.lss.teacher_manager.mapper.evaluation.EvaluationMapper;
import com.lss.teacher_manager.pojo.evaluation.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {
    @Autowired
    private EvaluationMapper evaluationMapper;

    //添加题目
    public boolean addEvaluation(QuestionDto questionDto){
        int i=evaluationMapper.addEvaluation(questionDto);
        if(i>0){
            return true;
        }else {
            return false;
        }
    }

    //查询评教题目
    public List<QuestionDto> getQuestion(){
        return evaluationMapper.getQuestion();
    }

    //删除评教题目
    public boolean deleteQuestionById(int qid){
        int i=evaluationMapper.deleteQuestionById(qid);
        if(i>0){
            return true;
        }else {
            return false;
        }
    }

    //修改评教题目
    public boolean updateQuestionById(QuestionDto questionDto){
        int i=evaluationMapper.updateQuestionById(questionDto);
        if(i>0){
            return true;
        }else {
            return false;
        }
    }

    //查询评教题目数量
    public int getQuestionCount(){
        return evaluationMapper.getQuestionCount();
    }
}
