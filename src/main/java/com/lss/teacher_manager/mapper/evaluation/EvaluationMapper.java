package com.lss.teacher_manager.mapper.evaluation;

import com.lss.teacher_manager.pojo.evaluation.QuestionDto;
import com.lss.teacher_manager.pojo.user.ManagerUserDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EvaluationMapper {

    // 添加评教问题
    @Insert("insert into question(question)" +
            "values(#{question})")
    int addEvaluation(QuestionDto questionDto);

    //查询评教问题
    @Results({
            @Result(property = "qid", column = "qid"),
            @Result(property = "question", column = "question"),
    })

    @Select("select * from question")
    List<QuestionDto> getQuestion();

    //删除评教问题
    @Delete("delete from question where qid=#{qid}")
    int deleteQuestionById(int qid);

    //修改评教题目
    @Update("update question set question=#{question} where qid=#{qid}")
    int updateQuestionById(QuestionDto questionDto);

    //查询评教问题数量
    @Select("select count(*) from question")
    int getQuestionCount();
}
