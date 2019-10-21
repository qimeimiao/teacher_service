package com.lss.teacher_manager.pojo.user;

import lombok.Data;

@Data
public class RoleMenuDto {
    private static final long serialVersionUID = -5200596408874170216L;
    /**
     * 角色ID
     */

    private String roleId;

    /**
     * 菜单/按钮ID
     */

    private String menuId;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
