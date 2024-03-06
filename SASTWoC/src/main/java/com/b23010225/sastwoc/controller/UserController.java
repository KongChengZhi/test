package com.b23010225.sastwoc.controller;

import com.b23010225.sastwoc.Service.UserService;
import com.b23010225.sastwoc.pojo.User;
import com.b23010225.sastwoc.pojo.Result;
import com.b23010225.sastwoc.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public Result login(@RequestBody User user){
        User user1 = userService.login(user); //登录校验
        if (user1 != null){                         //用户名和密码正确
            Map<String,Object> claims = new HashMap<>();
            claims.put("id", user1.getId());
            claims.put("username", user1.getUsername());
            claims.put("role",user1.getRole());

            //生成JWT令牌
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        log.info("用户{}登录", user.getUsername());
        return Result.error();

    }

}
