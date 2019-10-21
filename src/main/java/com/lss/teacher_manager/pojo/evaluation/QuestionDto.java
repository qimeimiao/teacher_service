package com.lss.teacher_manager.pojo.evaluation;

import com.lss.teacher_manager.pojo.BaseDto;
import lombok.Data;

@Data
public class QuestionDto {
    private int qid;
    private String question;

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
