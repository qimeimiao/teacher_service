package com.lss.teacher_manager.pojo.user;

import com.lss.teacher_manager.pojo.BaseDto;
import com.lss.teacher_manager.utils.TimeConverter;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@Excel("用户信息表")
public class ManagerUserDto extends BaseDto {

    // 用户状态：有效
    public static final String STATUS_VALID = "1";
    // 用户状态：锁定
    public static final String STATUS_LOCK = "0";
    // 默认头像
    public static final String DEFAULT_AVATAR = "default.jpg";
    // 默认密码
    public static final String DEFAULT_PASSWORD = "1234qwer";
    // 性别男
    public static final String SEX_MALE = "0";
    // 性别女
    public static final String SEX_FEMALE = "1";
    // 性别保密
    public static final String SEX_UNKNOW = "2";
    // 黑色主题
    public static final String THEME_BLACK = "black";
    // 白色主题
    public static final String THEME_WHITE = "white";
    // TAB开启
    public static final String TAB_OPEN = "1";
    // TAB关闭
    public static final String TAB_CLOSE = "0";


    /**
     * 用户 ID
     */
    private String userId;

    /**
     * 用户名
     */
    @ExcelField(value = "用户名")
    private String username;

    /**
     * 密码
     */

    private String password;

    /**
     * 部门 ID
     */

    private String deptId;

    /**
     * 部门名称
     */
    @ExcelField(value = "部门")
    private transient String deptName;

    /**
     * 邮箱
     */
    @ExcelField(value = "邮箱")
    private String email;

    /**
     * 联系电话
     */

    @ExcelField(value = "手机号")
    private String mobile;

    /**
     * 状态 0锁定 1有效
     */

    @ExcelField(value = "状态", writeConverterExp = "0=锁定,1=有效")
    private String status;


    /**
     * 最近访问时间
     */
    @ExcelField(value = "最后登录时间", writeConverter = TimeConverter.class)
    private Date lastLoginDate;

    /**
     * 性别 0男 1女 2 保密
     */
    @ExcelField(value = "性别", writeConverterExp = "0=男,1=女,2=保密")
    private String sex;

    /**
     * 头像
     */

    private String avatar;

    /**
     * 主题
     */

    private String theme;

    /**
     * 是否开启 tab 0开启，1关闭
     */

    private String tab;

    /**
     * 描述
     */
    @ExcelField(value = "个人描述")
    private String description;



    private String createTimeFrom;

    private String createTimeTo;

    /**
     * 角色 ID
     */

    private String roleId;

    @ExcelField(value = "角色")
    private String roleName;

    private String dName;

    private List<MenuDto> menuDtos;

    private DeptDto deptDto;

//    private RoleDto roleDto;
}
