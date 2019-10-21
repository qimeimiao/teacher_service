package com.lss.teacher_manager.pojo.user;

import com.lss.teacher_manager.pojo.BaseDto;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

@Data
@Excel("菜单信息表")
public class MenuDto extends BaseDto {

    // 菜单
    public static final String TYPE_MENU = "0";
    // 按钮
    public static final String TYPE_BUTTON = "1";

    public static final Long TOP_NODE = 0L;

    /**
     * 菜单/按钮ID
     */

    private String menuId;

    /**
     * 上级菜单ID
     */
    private String parentId;

    /**
     * 菜单/按钮名称
     */
    @ExcelField(value = "名称")
    private String menuName;

    /**
     * 菜单URL
     */
    @ExcelField(value = "地址")
    private String url;

    /**
     * 权限标识
     */
    @ExcelField(value = "权限")
    private String perms;

    /**
     * 图标
     */
    @ExcelField(value = "图标")
    private String icon;

    /**
     * 类型 0菜单 1按钮
     */
    @ExcelField(value = "类型", writeConverterExp = "0=按钮,1=菜单")
    private String type;

    /**
     * 排序
     */

    private Long orderNum;

    public static String getTypeMenu() {
        return TYPE_MENU;
    }

    public static String getTypeButton() {
        return TYPE_BUTTON;
    }

    public static Long getTopNode() {
        return TOP_NODE;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }
}
