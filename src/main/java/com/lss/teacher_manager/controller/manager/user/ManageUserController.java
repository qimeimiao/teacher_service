//package com.lss.teacher_manager.controller.manager.user;
//
//import com.zhongchou.controller.BaseController;
//import com.zhongchou.dao.entity.user.ManagerUserDto;
//import com.zhongchou.service.user.ManagerUserService;
//import com.zhongchou.service.user.RoleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/manager/user")
//public class ManageUserController extends BaseController {
//
//    @Autowired
//    ManagerUserService managerUserService;
//
//    @Autowired
//    RoleService roleService;
//
//
//    /**
//     * 管理员列表
//     */
//    @GetMapping("/managerList")
//    public String managerList(ManagerUserDto managerUserDto) {
//        List<ManagerUserDto> managerList = managerUserService.queryByPage(managerUserDto);
//        return listResult(managerList, managerUserDto.getPage());
//    }
//
////    /**
////     * 获取自己的角色
////     * @param userId
////     * @return
////     */
////    @GetMapping("/getrole")
////    public String getRole(String userId){
////        List<RoleDto> roleDtos = roleService.findUserRole(userId);
////        return listResult(roleDtos);
////    }
//
//    /**
//     * 添加用户
//     *
//     * @param requestBody
//     * @return
//     */
//    @PostMapping("/add")
//    public String saveUser(@RequestBody String requestBody) {
//        ManagerUserDto managerUserDto = super.getRequestBody(requestBody, ManagerUserDto.class);
//        managerUserService.save(managerUserDto);
//        return successResult();
//    }
//
//    /**
//     * 删除用户
////     *
//     * @param requestBody
//     * @return
//     */
//    @PostMapping("/delete")
//    public String delete(@RequestBody String requestBody) {
//        Map<String, Object> requestMap = super.getRequestBody(requestBody);
//        managerUserService.delete(requestMap);
//        return successResult();
//    }
//
//
//    /**
//     * 重置密码
//     *
//     * @param requestBody
//     * @return
//     */
//    @PostMapping("/password/reset")
//    public String passwordReset(@RequestBody String requestBody) {
//        Map<String, Object> requestMap = super.getRequestBody(requestBody);
//        managerUserService.passwordReset(requestMap);
//        return successResult();
//    }
//
//
//    /**
//     * 修改密码
//     *
//     * @param requestBody
//     * @return
//     */
//    @PostMapping("/password/update")
//    public String passwordUpdate(@RequestBody String requestBody) {
//        Map<String, Object> requestMap = super.getRequestBody(requestBody);
//        managerUserService.passwordUpdate(requestMap);
//        return successResult();
//    }
//
//    /**
//     * 根据ID查询用户信息
//     *
//     * @param requestBody
//     * @return
//     */
//    @GetMapping("/userById")
//    public String getUserInfo(@RequestBody String requestBody) {
//        Map<String, Object> requestMap = super.getRequestBody(requestBody);
//        ManagerUserDto userInfo = managerUserService.getUserInfo(requestMap);
//        return successResult(userInfo);
//    }
//
//    /**
//     * 获取当前登录用户
//     *
//     * @return
//     */
//    @GetMapping("/userinfo")
//    public String getCurrUser() {
//        return successResult(managerUserService.getCurrUser());
//    }
//
//    /**
//     * 更新用户
//     *
//     * @param requestBody
//     * @return
//     */
//    @PostMapping("/update")
//    public String update(@RequestBody String requestBody) {
//        ManagerUserDto managerUserDto = super.getRequestBody(requestBody, ManagerUserDto.class);
//        managerUserService.update(managerUserDto);
//        return successResult();
//    }
//
//    /**
//     * 退出登录
//     * @return
//     */
//    @GetMapping("/logout")
//    public String logout() {
//        managerUserService.logout();
//        return successResult();
//    }
//
//
//}
