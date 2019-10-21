package com.lss.teacher_manager.mapper.evalution;

import com.lss.teacher_manager.mapper.BaseMapper;
import com.lss.teacher_manager.pojo.evaluation.EvaluationDto;
import com.lss.teacher_manager.pojo.user.CourseDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EvalutionMapper {
    @Select("select * from evaluation where uid=#{0}")
    List<EvaluationDto> findEvaluationbyuid(String id);

    @Select("select * from evaluation where uid=#{uid} and tid=#{tid}")
    EvaluationDto findEvaluationbyuidandtid(EvaluationDto evaluationDto);

//    @Insert("insert INTO evaluation(tid,uid,point,qid1,qid2,qid3,qid4,qid5,qpoint1,qpoint2,qpoint3,qpoint4,qpoint5)values(#{tid},#{uid},#{point},#{qid1},#{qid2},#{qid3},#{qid4},#{qid5},#{qpoint1},#{qpoint2},#{qpoint3},#{qpoint4},#{qpoint5})")
//    void insertevalution(EvaluationDto evaluationDto);

    @Insert("insert INTO evaluation(tid,uid,point,qpoint1,qpoint2,qpoint3,qpoint4,qpoint5)values(#{tid},#{uid},#{point},#{qpoint1},#{qpoint2},#{qpoint3},#{qpoint4},#{qpoint5})")
    int insertevalution(EvaluationDto evaluationDto);

    @Select("select * from evaluation where tid=#{tid}")
    List<EvaluationDto> findEvaluationbytid(String tid);

    @Delete("delete from evaluation")
    int delAllEvaluation();

    //查找同行
    @Select("select d.dept_name,m.username,cou.* from t_dept d,manager_user m ,course cou where cou.deptid=d.dept_id and cou.uid=m.user_id and cou.deptid=#{deptid} and cou.uid!=#{uid}")
    List<CourseDto> findColleague(int deptid,String uid);

}
