package com.lss.teacher_manager.service.question;

import com.lss.teacher_manager.mapper.question.QuestionMapper;
import com.lss.teacher_manager.pojo.evaluation.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    public List<QuestionDto> findallquestion(){
        return questionMapper.findallquestion();
    }
}
