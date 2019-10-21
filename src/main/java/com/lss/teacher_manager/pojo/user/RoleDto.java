package com.lss.teacher_manager.pojo.user;

import com.lss.teacher_manager.pojo.BaseDto;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

@Data
@Excel("角色信息表")
public class RoleDto extends BaseDto {

    /**
     * 角色ID
     */

    private String roleId;

    /**
     * 角色名称
     */
    @ExcelField(value = "角色名称")
    private String roleName;

    /**
     * 角色描述
     */
    @ExcelField(value = "角色描述")
    private String remark;

    /**
     * 角色对应的菜单（按钮） id
     */
    private  String menuIds;

}
