package com.lss.teacher_manager.mapper.user;

import com.lss.teacher_manager.pojo.user.UserRoleDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

@Component
public interface UserRoleMapper {


    @Insert("insert into t_manager_user_role(user_id,role_id)values(#{userId},#{roleId})")
    void save(UserRoleDto userRoleDto);

    @Delete("delete from t_manager_user_role where user_id =#{0}")
    void deleteByUserId(String userId);

    @Delete("delete from t_manager_user_role where role_id =#{0}")
    void deleteByRoleId(String roleId);

}
