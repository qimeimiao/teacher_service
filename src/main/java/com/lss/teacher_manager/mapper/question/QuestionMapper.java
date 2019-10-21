package com.lss.teacher_manager.mapper.question;

import com.lss.teacher_manager.pojo.evaluation.QuestionDto;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuestionMapper  {

    @Select("select * from question")
    List<QuestionDto> findallquestion();
}
