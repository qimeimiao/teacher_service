package com.lss.teacher_manager.service.user;

import com.lss.teacher_manager.exception.APIError;
import com.lss.teacher_manager.mapper.user.RoleMapper;
import com.lss.teacher_manager.mapper.user.RoleMenuMapper;
import com.lss.teacher_manager.mapper.user.UserRoleMapper;
import com.lss.teacher_manager.pojo.user.RoleDto;
import com.lss.teacher_manager.pojo.user.RoleMenuDto;
import com.lss.teacher_manager.utils.SysUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    RoleMenuMapper roleMenuMapper;

    public List<RoleDto> queryByPage(RoleDto roleDto) {
        List<RoleDto> roleDtos = roleMapper.queryByPage(roleDto);
        for (RoleDto dto : roleDtos) {
            List<String> menuIds = roleMenuMapper.queryByRoleId(dto.getRoleId());
            String join = StringUtils.join(menuIds, ",");
            dto.setMenuIds(join);
        }
        return roleDtos;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Map<String, Object> rquestMap) {
        String roleIds = (String) rquestMap.get("roleIds");
        String[] split = roleIds.split(",");
        for (String roleId : split) {
            roleMapper.delete(roleId);
            //删除用户与角色关联关系
            userRoleMapper.deleteByRoleId(roleId);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void add(RoleDto roleDto) {
        String roleId = SysUtils.getSysUUID();
        roleDto.setRoleId(roleId);
        roleDto.setCreateDate(new Date());
        roleMapper.save(roleDto);
        String memuIds = roleDto.getMenuIds();
        if (StringUtils.isBlank(memuIds)){
            APIError.CUSTOM.set(400,"菜单为空").expose();
        }
        String[] split = memuIds.split(",");
        for (String menuId : split) {
            RoleMenuDto roleMenuDto = new RoleMenuDto();
            roleMenuDto.setMenuId(menuId);
            roleMenuDto.setRoleId(roleId);
            roleMenuMapper.save(roleMenuDto);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(RoleDto roleDto) {
        roleMapper.update(roleDto);
        //删除角色与菜单对应关系
        roleMenuMapper.deleteByRoleId(roleDto.getRoleId());
        String memuIds = roleDto.getMenuIds();
        if (StringUtils.isBlank(memuIds)){
            APIError.CUSTOM.set(400,"菜单为空").expose();
        }
        String[] split = memuIds.split(",");
        for (String menuId : split) {
            RoleMenuDto roleMenuDto = new RoleMenuDto();
            roleMenuDto.setMenuId(menuId);
            roleMenuDto.setRoleId(roleDto.getRoleId());
            roleMenuMapper.save(roleMenuDto);
        }

    }

    public List<RoleDto> findUserRole(String userId) {
        List<RoleDto> roleDtos = roleMapper.findUserRole(userId);
        return roleDtos;
    }

    public List<RoleDto> getRoles(){
       return roleMapper.getRoles();
    }
}
