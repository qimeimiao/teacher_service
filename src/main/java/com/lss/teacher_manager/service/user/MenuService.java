package com.lss.teacher_manager.service.user;


import com.lss.teacher_manager.exception.APIError;
import com.lss.teacher_manager.mapper.user.MenuMapper;
import com.lss.teacher_manager.mapper.user.RoleMenuMapper;
import com.lss.teacher_manager.pojo.user.MenuDto;
import com.lss.teacher_manager.pojo.user.MenuTree;
import com.lss.teacher_manager.utils.SysUtils;
import com.lss.teacher_manager.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    RoleMenuMapper roleMenuMapper;


    public void save(MenuDto menuDto) {
        if(menuDto.getParentId() == null){
            menuDto.setParentId("0");
        }
        menuDto.setCreateDate(new Date());
        menuDto.setMenuId(SysUtils.getSysUUID());
        menuMapper.save(menuDto);
    }


    public void edit(MenuDto menuDto) {
        if (StringUtils.isEmpty(menuDto.getType())) {
            APIError.NOTEMPTY.expose();
        }
        //为菜单
        if ("0".equals(menuDto.getType())) {
            menuMapper.updateMenu(menuDto);
            //为按钮
        } else if ("1".equals(menuDto.getType())) {
            menuMapper.updateButton(menuDto);
        }
    }

    public void delete(Map<String, Object> requestMap) {
        String menuIds = (String) requestMap.get("menuIds");
        String[] split = menuIds.split(",");
        for (String menuId : split) {
            Set<String> menuSetIds = new HashSet<>();
            menuSetIds.add(menuId);
            findSubNode(menuSetIds,menuId);
            menuMapper.delete(menuSetIds);
            //删除角色关联的菜单
            roleMenuMapper.deleteByMenuIds(menuSetIds);

        }
    }
    private void findSubNode(Set<String> deleteIds, String menuId) {
        List<String> setDb = menuMapper.getSonMenu(menuId);
        for (String s : setDb) {
            deleteIds.add(s);
            findSubNode(deleteIds,s);
        }
    }

    public MenuTree<MenuDto> findMenus(MenuDto menuDto) {
        List<MenuDto> menuDtos =  menuMapper.findMenus(menuDto);
        List<MenuTree<MenuDto>> menuDtoMenuTree = this.convertMenus(menuDtos);
        return TreeUtil.buildMenuTree(menuDtoMenuTree);

    }

    private List<MenuTree<MenuDto>> convertMenus(List<MenuDto> menus) {
        List<MenuTree<MenuDto>> trees = new ArrayList<>();
        menus.forEach(menu -> {
            MenuTree<MenuDto> tree = new MenuTree<>();
            tree.setId(String.valueOf(menu.getMenuId()));
            tree.setParentId(String.valueOf(menu.getParentId()));
            tree.setTitle(menu.getMenuName());
            tree.setIcon(menu.getIcon());
            tree.setHref(menu.getUrl());
            tree.setData(menu);
            trees.add(tree);
        });
        return trees;
    }

    public List<MenuDto> findUserPermissions(String userId) {
        List<MenuDto> menuDtos = menuMapper.findUserPermissions(userId);
        return menuDtos;
    }


    public  List<MenuDto> getUserMenu(String userId) {
        List<MenuDto> menuDtos = menuMapper.getUserMenu(userId);
        return menuDtos;

}
    }
