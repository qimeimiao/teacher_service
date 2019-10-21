package com.lss.teacher_manager.pojo.user;

import com.lss.teacher_manager.pojo.BaseDto;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import java.util.List;

@Data
@Excel("部门信息表")
public class DeptDto extends BaseDto {

    /**
     * 部门 ID
     */
    private String deptId;

    /**
     * 上级部门 ID
     */
    private String parentId;

    /**
     * 部门名称
     */
    @ExcelField(value = "部门名称")
    private String deptName;

    /**
     * 排序
     */
    private Long orderNum;

    /**
     * 部门自行西
     */
    private List<DeptDto> deptDtos;

}
