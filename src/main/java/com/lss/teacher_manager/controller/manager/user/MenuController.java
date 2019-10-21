package com.lss.teacher_manager.controller.manager.user;


import com.lss.teacher_manager.controller.BaseController;
import com.lss.teacher_manager.pojo.user.MenuDto;
import com.lss.teacher_manager.pojo.user.MenuTree;
import com.lss.teacher_manager.service.user.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/manager/menu")
public class MenuController extends BaseController {
    @Autowired
    MenuService menuService;

    /**
     * 添加菜单和按钮
     * @param requestBody
     * @return
     */
    @PostMapping("/add")
    public String addMenu(@RequestBody String requestBody){
        MenuDto menuDto = super.getRequestBody(requestBody,MenuDto.class);
        menuService.save(menuDto);
        return successResult();
    }

    /**
     * 编辑部门
     * @param requestBody
     * @return
     */
    @PostMapping("/edit")
    public String deit(@RequestBody String requestBody){
        MenuDto menuDto = super.getRequestBody(requestBody,MenuDto.class);
        menuService.edit(menuDto);
        return successResult();
    }

    /**
     * 删除菜单
     * @param requestBody
     * @return
     */
    @PostMapping("/delete")
    public String delete(@RequestBody String requestBody){
        Map<String,Object> requestMap = super.getRequestBody(requestBody);
        menuService.delete(requestMap);
        return successResult();
    }


    /**
     * 查询树菜单
     * @param menuDto
     * @return
     */
    @GetMapping("/tree")
    public String getMenuTree(MenuDto menuDto){
        MenuTree<MenuDto> menus = menuService.findMenus(menuDto);
        return successResult(menus);

    }


}
