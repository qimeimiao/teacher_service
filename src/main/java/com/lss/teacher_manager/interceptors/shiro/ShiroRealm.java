package com.lss.teacher_manager.interceptors.shiro;

import com.alibaba.fastjson.JSONObject;
import com.lss.teacher_manager.exception.APIError;
import com.lss.teacher_manager.pojo.user.ManagerUserDto;
import com.lss.teacher_manager.pojo.user.MenuDto;
import com.lss.teacher_manager.pojo.user.RoleDto;
import com.lss.teacher_manager.service.redis.RedisServiceImpl;
import com.lss.teacher_manager.service.user.MenuService;
import com.lss.teacher_manager.service.user.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 自定义实现 ShiroRealm，包含认证和授权两大模块
 *
 * @author MrBird
 */
public class ShiroRealm extends AuthorizingRealm {

    @Value("${spring.redis.timeout}")
    long expireTokenTime;

    @Autowired
    RedisServiceImpl redisService;

    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;



   // @Autowired
   // private FebsProperties properties;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }



    /**`
     * 授权模块，获取用户角色和权限
     *
     * @param token token
     * @return AuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
        //String username = JWTUtil.getUsername(token.toString());
        String encryptTokenInRedis = null;
        try {
            encryptTokenInRedis = redisService.get(token.toString());
       } catch (Exception ignore) {
        }
        if (encryptTokenInRedis==null){
            APIError.CUSTOM.set(400,"没有用户信息").expose();
       }
        final ManagerUserDto managerUserDto = JSONObject.parseObject(encryptTokenInRedis, ManagerUserDto.class);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();


        // 获取用户角色集
        List<RoleDto> roleList = this.roleService.findUserRole(managerUserDto.getUserId());
        Set<String> roleSet = roleList.stream().map(RoleDto::getRoleName).collect(Collectors.toSet());
        simpleAuthorizationInfo.setRoles(roleSet);

        // 获取用户权限集
        List<MenuDto> permissionList = this.menuService.findUserPermissions(managerUserDto.getUserId());
        Set<String> permissionSet = permissionList.stream().map(MenuDto::getPerms).collect(Collectors.toSet());
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;

    }

    /**
     * 用户认证
     *
     * @param authenticationToken 身份认证 token
     * @return AuthenticationInfo 身份认证信息
     * @throws AuthenticationException 认证相关异常
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
//        if (!"prd".equals(ProfileUtil.getActiveProfile())) {
////        authenticationToken  instanceof SimplePass
//            // 这里的 token是从 JWTFilter 的 executeLogin 方法传递过来的，已经经过了解密
//
//            // 从 redis里获取这个 token
//            //HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
//            //String ip = IPUtil.getIpAddr(request);
//
         //String encryptToken = FebsUtil.encryptToken(token);
            String encryptTokenInRedis = null;
            try {
                encryptTokenInRedis = redisService.get(token);
            } catch (Exception ignore) {
            }
            // 如果找不到，说明已经失效
            if (StringUtils.isBlank(encryptTokenInRedis)) {
                //throw new AuthenticationException("token已经过期");
                APIError.CUSTOM.set(400, "token已经过期").expose();
            }


        ManagerUserDto managerUserDto = JSONObject.parseObject(encryptTokenInRedis, ManagerUserDto.class);
        if (ManagerUserDto.STATUS_LOCK.equals(managerUserDto.getStatus())) {
            APIError.CUSTOM.set(400, "账号已被锁定,请联系管理员！").expose();
        }
        redisService.expier(token,expireTokenTime);
//        String username = JWTUtil.getUsername(token);
//
//        if (StringUtils.isBlank(username))
//            throw new AuthenticationException("token校验不通过");

        // 通过用户名查询用户信息
        //User user = userManager.getUser(username);

//        if (user == null)
//            throw new AuthenticationException("用户名或密码错误");
//        if (!JWTUtil.verify(token, username, user.getPassword()))
//            throw new AuthenticationException("token校验不通过");

        return new SimpleAuthenticationInfo(token, token, "febs_shiro_realm");
    }


}
