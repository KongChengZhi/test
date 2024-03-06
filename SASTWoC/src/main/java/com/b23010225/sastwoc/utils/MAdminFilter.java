package com.b23010225.sastwoc.utils;

import com.b23010225.sastwoc.pojo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Objects;

@Slf4j
@WebFilter(urlPatterns = {"/pancake","/pancake/*"})
public class MAdminFilter implements Filter {

    //过滤器初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        Filter.super.init(filterConfig);
        System.out.println("身份过滤器已经初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ObjectMapper objectMapper = new ObjectMapper(); //调用objectMapper
        String signKey = "b23010225";

        //首先判断是什么请求 是put和delete再进行身份验证，不是直接通过。
        String method = request.getMethod();
        if(!(method.equalsIgnoreCase("PUT") || method.equalsIgnoreCase("DELETE"))){
            log.info("非管理员操作，放行。");
            filterChain.doFilter(request,response);
            return;
        }
        /*检查令牌中的身份信息
        * 1.初始化令牌和声明
        * 2.密钥验证签名
        * 3.提取role
        * 4.检查
        * */
        String jwt = request.getHeader("token");
        Claims claims = Jwts.parser().setSigningKey(signKey).parseClaimsJws(jwt).getBody();
        Integer role = claims.get("role",Integer.class);
        if(role == 1){
            log.info("管理员身份验证通过！");
            filterChain.doFilter(request,response);
            return;
        }
        log.info("无权限！");
        Result error = Result.error("NO_PERMISSION");
        String noPermission = objectMapper.writeValueAsString(error);
        response.getWriter().write(noPermission);

    }

    @Override
    public void destroy(){
        Filter.super.destroy();
        System.out.println("身份过滤器已销毁");
    }
}
