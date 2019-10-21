package com.lss.teacher_manager.mapper.user;

import com.lss.teacher_manager.pojo.user.RoleDto;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public interface RoleMapper {

    @Insert("insert into t_role(role_id,role_name,remark,create_date,update_date)values" +
            "(#{roleId},#{roleName},#{remark},#{createDate},#{updateDate})")
    void save(RoleDto roleDto);

    @Delete("delete from t_role where role_id =#{0}")
    void delete(String roleId);


    @Results({
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "updateDate", column = "update_date")
    })
    @SelectProvider(type = RoleMapper.CommonProvider.class,method = "queryByPage")
    List<RoleDto> queryByPage(RoleDto roleDto);

    @Update("update t_role set role_name =#{roleName},remark =#{remark} where role_id =#{roleId}")
    void update(RoleDto roleDto);

    @Results({
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "updateDate", column = "update_date")
    })
    @Select("SELECT * FROM t_role")
    List<RoleDto> getRoles();
    @Results({
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "updateDate", column = "update_date")
    })
    @Select("SELECT r.* FROM t_manager_user_role t LEFT JOIN t_role r ON(t.role_id =r.role_id) WHERE t.user_id =#{0}")
    List<RoleDto> findUserRole(String userId);

    class  CommonProvider {
        public String queryByPage(RoleDto roleDto){
            SQL sql = new SQL().SELECT("t.*").FROM("t_role t").WHERE("1=1");
            if (!StringUtils.isEmpty(roleDto.getRoleName())){
                sql.WHERE("t.role_name =#{roleName}");
            }
            if (!StringUtils.isEmpty(roleDto.getRoleId())){
                sql.WHERE("t.role_id =#{roleId}");
            }
            sql.ORDER_BY("t.create_date");
            return sql.toString();

        }
    }

}
