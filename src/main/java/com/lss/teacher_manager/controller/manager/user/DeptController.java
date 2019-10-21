package com.lss.teacher_manager.controller.manager.user;


import com.google.common.collect.Lists;
import com.lss.teacher_manager.controller.BaseController;
import com.lss.teacher_manager.pojo.user.DeptDto;
import com.lss.teacher_manager.pojo.user.DeptTree;
import com.lss.teacher_manager.pojo.user.ManagerUserDto;
import com.lss.teacher_manager.pojo.user.MenuTree;
import com.lss.teacher_manager.service.user.DeptService;
import com.lss.teacher_manager.utils.MapUtil;
import com.wuwenze.poi.ExcelKit;
import com.wuwenze.poi.handler.ExcelReadHandler;
import com.wuwenze.poi.pojo.ExcelErrorField;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 部门相关累
 */
@RestController
@RequestMapping("/manager/dept")
public class DeptController extends BaseController {

    private String message;

    @Autowired
    DeptService deptService;

    /**
     * @param requestBody
     * @return
     * @Log("新增部门")
     */
    @PostMapping("/add")
    public String addDept(@RequestBody String requestBody) {
        DeptDto deptDto = super.getRequestBody(requestBody, DeptDto.class);
        deptService.save(deptDto);
        return successResult();
    }

    /**
     * @param requestBody
     * @return
     * @Log("修改部门")
     */
    @PostMapping("/update")
    public String updateDept(@RequestBody String requestBody) {
        DeptDto deptDto = super.getRequestBody(requestBody, DeptDto.class);
        deptService.update(deptDto);
        return successResult();
    }

    /**
     * @param requestBody
     * @return
     * @Log("删除部门")
     */
    @PostMapping("/delete")
    public String delete(@RequestBody String requestBody) {
        Map<String, Object> requestMap = super.getRequestBody(requestBody);
        deptService.delete(requestMap);
        return successResult();
    }

    /**
     * 查询部门树最外层
     *
     * @return
     */
    @GetMapping("/department")
    public String getDeptTree() {
        DeptTree<DeptDto> deptDtos = deptService.getDeptTree();
        return successResult(deptDtos);
    }


    /**
     * @param deptName
     * @return
     */
    @GetMapping("/sontree")
    public String getSonTree(String deptName) {
        DeptTree<DeptDto> deptDtos = deptService.getSontree(deptName);
        return successResult(deptDtos);
    }

    @GetMapping("excel")
//    @RequiresPermissions("dept:export")
    public void export( HttpServletResponse response){
        try {
            List<DeptDto> depts = this.deptService.getDepts();
            ExcelKit.$Export(DeptDto.class, response).downXlsx(depts, false);
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
        }
    }

    @PostMapping("excel")
//    @RequiresPermissions("dept:export")
    public String importUser(@RequestParam MultipartFile file) throws IOException {
        long beginMillis = System.currentTimeMillis();

        List<DeptDto> successList = Lists.newArrayList();
        List<Map<String, Object>> errorList = Lists.newArrayList();

        ExcelKit.$Import(DeptDto.class)
                .readXlsx(file.getInputStream(), new ExcelReadHandler<DeptDto>() {


                    @Override
                    public void onSuccess(int sheetIndex, int rowIndex, DeptDto entity) {
                        successList.add(entity); // 单行读取成功，加入入库队列。

                    }
                    @Override
                    public void onError(int sheetIndex, int rowIndex,
                                        List<ExcelErrorField> errorFields) {

                        System.out.println(errorFields);
                    }
                });

        // TODO: 执行successList的入库操作。
        List<DeptDto> list=successList;
        list.forEach(d ->deptService.save(d));
        return successResult();
    }

}
