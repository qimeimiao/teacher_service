package com.lss.teacher_manager.utils;

import com.lss.teacher_manager.pojo.user.DeptTree;
import com.lss.teacher_manager.pojo.user.MenuTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtil {

    // ----------------------------------------------------------------------------------------------- new HashMap
    /**
     * 新建一个HashMap
     *
     * @param <K> Key类型
     * @param <V> Value类型
     * @return HashMap对象
     */
    public static <K, V> HashMap<String, Object> newHashMap(String s,Object o) {
        return new HashMap<String, Object>(newHashMap(s,o));
    }


}
