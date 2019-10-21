package com.lss.teacher_manager.service;

import com.lss.teacher_manager.exception.APIError;
import com.lss.teacher_manager.mapper.user.ManagerUserMapper;
import com.lss.teacher_manager.mapper.user.RoleMapper;
import com.lss.teacher_manager.mapper.user.UserRoleMapper;
import com.lss.teacher_manager.pojo.user.*;
import com.lss.teacher_manager.utils.SysUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ManagerUserService extends BaseUserService {

    @Autowired
    private ManagerUserMapper managerUserMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    RoleMapper roleMapper;

    public List<ManagerUserDto> queryByPage(ManagerUserDto managerUserDto){
        List<ManagerUserDto> managerUserDtos = managerUserMapper.queryByPage(managerUserDto);
        for (ManagerUserDto userDto : managerUserDtos) {
            List<RoleDto> roleDtos = roleMapper.findUserRole(userDto.getUserId());
            String[] roleIds = roleDtos.stream().map(RoleDto -> String.valueOf(RoleDto.getRoleId())).toArray(String[]::new);
            String[] roleNames = roleDtos.stream().map(RoleDto -> String.valueOf(RoleDto.getRoleName())).toArray(String[]::new);
            String roleId = org.apache.commons.lang3.StringUtils.join(roleIds, ",");
            String roleName = org.apache.commons.lang3.StringUtils.join(roleNames, ",");
            userDto.setRoleId(roleId);
            userDto.setRoleName(roleName);
        }
        return managerUserDtos;
    }

    public void save(ManagerUserDto managerUserDto) {
        if (StringUtils.isEmpty(managerUserDto.getRoleId())){
            APIError.CUSTOM.set(400,"部门或者角色不能为空").expose();
        }
        String sysUUID = SysUtils.getSysUUID();
        managerUserDto.setUserId(sysUUID);
        managerUserDto.setCreateDate(new Date());
        managerUserDto.setStatus(ManagerUserDto.STATUS_VALID);
        managerUserDto.setPassword(ManagerUserDto.DEFAULT_PASSWORD);
        managerUserMapper.save(managerUserDto);
        String[] split = managerUserDto.getRoleId().split(",");
        for (String s : split) {
            UserRoleDto userRoleDto = new UserRoleDto();
            userRoleDto.setRoleId(s);
            userRoleDto.setUserId(sysUUID);
            userRoleMapper.save(userRoleDto);
        }
    }

    public void delete(Map<String, Object> requestMap) {
        String userIds = (String) requestMap.get("userIds");
        String[] split = userIds.split(",");
        for (String userId : split) {
            managerUserMapper.delete(userId);
            userRoleMapper.deleteByUserId(userId);
        }
    }

//    public void passwordReset(Map<String, Object> requestMap) {
//        String userIds = (String) requestMap.get("userIds");
//        String[] split = userIds.split(",");
//        for (String userId : split) {
//            managerUserMapper.updatePassword(ManagerUserDto.DEFAULT_PASSWORD,userId);
//        }
//    }

    public void passwordUpdate(ManagerUserDto managerUserDto) {
        managerUserMapper.updatePassword(managerUserDto);
    }

    public ManagerUserDto getUserInfo(Map<String, Object> requestMap) {
        String userId = (String) requestMap.get("userId");
        ManagerUserDto managerUserDto = managerUserMapper.queryById(userId);
        if (managerUserDto==null){
            APIError.NOT_FOUND.expose();
        }
        return managerUserDto;
    }

    public void update(ManagerUserDto managerUserDto) {
        managerUserMapper.update(managerUserDto);
        userRoleMapper.deleteByUserId(managerUserDto.getUserId());
        String[] split = managerUserDto.getRoleId().split(",");
        for (String s : split) {
            UserRoleDto userRoleDto = new UserRoleDto();
            userRoleDto.setRoleId(s);
            userRoleDto.setUserId(managerUserDto.getUserId());
            userRoleMapper.save(userRoleDto);
        }
    }

    public ManagerUserDto queryByName(String username) {
        return managerUserMapper.queryByName(username);
    }

    public Object getCurrUser() {
        return super.getCurrentManagerUser();
    }

    public void logout(){
//        super.refreshCache("");
    }

    public List<ManagerUserDto> getUsers(){
        return managerUserMapper.getUsers();
    }

    //    通过学生id查询该学生的授课教师信息
    public ManagerUserDto querytByid(String user_id) {
        return managerUserMapper.querytByid("1");
    }
    //    通过学生id查询该学生的课程信息
    public List<ConnectionDto> querycByid(String uid) {
        //System.out.println(super.getCurrentManagerUser().getUserId());
        return managerUserMapper.querycByid(uid);
    }
    //    通过院系id查询所有该院系的教师
    public List<ManagerUserDto> querytBydid(String dept_id){
        return managerUserMapper.querytBydid(dept_id);
    }


    //查询不同角色的所有用户信息
    public List<ManagerUserDto> getUserInfoByRolr(String role){
        return managerUserMapper.getUserInfoByRolr(role);
    }

    //查询课程
    public List<CourseDto> getCourse(){
        return managerUserMapper.getCourse();
    }
}
