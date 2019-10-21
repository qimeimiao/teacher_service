package com.lss.teacher_manager.pojo.user;

import lombok.Data;

@Data
public class UserRoleDto {

    private static final long serialVersionUID = 2354394771912648574L;
    /**
     * 用户ID
     */

    private String userId;

    /**
     * 角色ID
     */

    private String roleId;
}
