package com.lss.teacher_manager.pojo.evaluation;

import lombok.Data;

@Data
public class EvaluationDto {
//    评价表id
    private int eid;
//    被评教师id
    private String tid;
//    评价者id(不论教师或者学生)
    private String uid;


    //    最终得分
    private String point;
//    问题1id
    private int qid1;
//    问题2id
    private int qid2;
//    问题3id
    private int qid3;
//    问题4id
    private int qid4;
//    问题5id
    private int qid5;
//    问题一得分
    private String qpoint1;
//    问题二得分
    private String qpoint2;
//    问题三得分
    private String qpoint3;
//    问题四得分
    private String qpoint4;
//    问题五得分
    private String qpoint5;
}
