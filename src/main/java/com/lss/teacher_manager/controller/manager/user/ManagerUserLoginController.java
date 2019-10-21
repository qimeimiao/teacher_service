package com.lss.teacher_manager.controller.manager.user;


import com.alibaba.fastjson.JSONObject;
import com.lss.teacher_manager.controller.BaseController;
import com.lss.teacher_manager.exception.APIError;
import com.lss.teacher_manager.pojo.user.ManagerUserDto;
import com.lss.teacher_manager.pojo.user.MenuDto;
import com.lss.teacher_manager.service.ManagerUserService;
import com.lss.teacher_manager.service.redis.RedisServiceImpl;

import com.lss.teacher_manager.service.user.MenuService;
import com.lss.teacher_manager.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manager/user/login")
public class ManagerUserLoginController extends BaseController {

    @Autowired
    ManagerUserService managerUserService;

    @Autowired
    RedisServiceImpl redisService;

    @Autowired
    MenuService menuService;

    @Value("${spring.redis.timeout}")
    long expireTokenTime;

    @PostMapping()
    public String login(@RequestBody String requestBody) {
        ManagerUserDto managerUserDto = super.getRequestBody(requestBody, ManagerUserDto.class);
        if (StringUtils.isEmpty(managerUserDto.getUsername())) {
            APIError.NOTEMPTY.expose();
        }
        ManagerUserDto managerUserDtoDb = managerUserService.queryByName(managerUserDto.getUsername());
        if (managerUserDtoDb == null) {
            APIError.CUSTOM.set(400, "用户不存在").expose();
        }
        if (!managerUserDtoDb.getPassword().equals(managerUserDto.getPassword())) {
            APIError.CUSTOM.set(400, "密码错误").expose();
        }
        List<MenuDto> userMenu = menuService.getUserMenu(managerUserDtoDb.getUserId());
        managerUserDtoDb.setMenuDtos(userMenu);
        String token = EncryptUtil.getMd5((new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()) + String.valueOf((int) (Math.random() * 900) + 100) + "-" + managerUserDtoDb.getUsername());
        redisService.expier(token, expireTokenTime);
        redisService.set(token, JSONObject.toJSONString(managerUserDtoDb));
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("token", token);
        retMap.put("user", managerUserDtoDb);
        System.out.println(retMap);
        return successResult(retMap);
    }


}