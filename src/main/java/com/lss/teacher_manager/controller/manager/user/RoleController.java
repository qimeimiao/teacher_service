package com.lss.teacher_manager.controller.manager.user;


import com.lss.teacher_manager.controller.BaseController;
import com.lss.teacher_manager.pojo.user.ManagerUserDto;
import com.lss.teacher_manager.pojo.user.RoleDto;
import com.lss.teacher_manager.service.user.RoleService;
import com.wuwenze.poi.ExcelKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manager/role")
public class RoleController extends BaseController {


    @Autowired
    RoleService roleService;

    private String message;

    /**
     * 查询角色列表
     *
     * @param roleDto
     * @return
     */
    @GetMapping("/listuser")
    public String listUser(RoleDto roleDto) {
        List<RoleDto> roleDtos = roleService.queryByPage(roleDto);
        return successResult(roleDtos);

    }


    /**
     * 删除角色
     * @param requestBody
     * @return
     */
    @PostMapping("/delete")
    public String delete(@RequestBody String requestBody){
        Map<String,Object> rquestMap = super.getRequestBody(requestBody);
        roleService.delete(rquestMap);
        return successResult();
    }


    /**
     * 添加角色
     * @param requestBody
     * @return
     */
    @PostMapping("/add")
    public String add(@RequestBody String requestBody){
        RoleDto roleDto = super.getRequestBody(requestBody ,RoleDto.class);
        roleService.add(roleDto);
        return successResult();

    }

    /**
     * 修改角色
     * @param requestBody
     * @return
     */
    @PostMapping("/update")
    public String update(@RequestBody String requestBody){
        RoleDto roleDto = super.getRequestBody(requestBody ,RoleDto.class);
        roleService.update(roleDto);
        return successResult();
    }

    @GetMapping("excel")
//    @RequiresPermissions("dept:export")
    public void export( HttpServletResponse response){
        try {
            List<RoleDto> roles = this.roleService.getRoles();
            ExcelKit.$Export(RoleDto.class, response).downXlsx(roles, false);
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
        }
    }

}
