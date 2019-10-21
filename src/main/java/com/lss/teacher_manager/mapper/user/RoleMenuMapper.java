package com.lss.teacher_manager.mapper.user;

import com.lss.teacher_manager.pojo.user.RoleMenuDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface RoleMenuMapper {

    @Insert("insert into t_role_menu(role_id,menu_id)values(#{roleId},#{menuId})")
    void save(RoleMenuDto roleMenuDto);

    @Delete("delete from t_role_menu where role_id =#{0}")
    void deleteByRoleId(String roleId);


    @Delete("delete from t_role_menu where menu_id =#{0}")
    void deleteByMenuId(String menuId);

    @DeleteProvider(type = RoleMenuMapper.CommonProvider.class, method = "deleteByMenuIds")
    void deleteByMenuIds(Set<String> menuSetIds);

    @Select("select menu_id from t_role_menu where role_id =#{0}")
    List<String> queryByRoleId(String roleId);

    class CommonProvider {

        public String deleteByMenuIds(Set<String> menuSetIds) {
            SQL sql = new SQL().DELETE_FROM("t_role_menu");
            if (!menuSetIds.isEmpty()) {
                filterFieldId(sql, "menu_id", menuSetIds);
            } else {
                sql.WHERE("menu_id =''");
            }

            return sql.toString();
        }


        public void filterFieldId(SQL sql, String fieldName, Set<String> menuSetIds) {
            if (menuSetIds.isEmpty()) {
                return;
            }
            StringBuilder menuIds = new StringBuilder();
            for (String menuId : menuSetIds) {
                menuIds.append("'" + menuId + "'");
//                menuIds.append(StringPool.COMMA);
            }
            String ids = menuIds.toString();
            sql.WHERE(fieldName + "  IN (" + ids.substring(0, ids.length() - 1) + ")");
        }
    }
}
