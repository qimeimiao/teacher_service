package com.lss.teacher_manager.mapper;

import com.lss.teacher_manager.pojo.BaseDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseMapper<T extends BaseDto> {

    void save(T t);

    void delete(String id);

    void deleteX(String id);

    void update(T t);

    Long count();

    Long countToday();


    T queryById(String id);

    List<T> queryByPage(T t);

    List<T> queryAll();

    List<T> queryByFuzzy(String s1, String s2);

    T queryByName(String name);

    List<T> queryByType(String type);


}
