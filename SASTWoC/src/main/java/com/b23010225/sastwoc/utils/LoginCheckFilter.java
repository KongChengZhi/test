package com.b23010225.sastwoc.utils;

import com.b23010225.sastwoc.pojo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;



//过滤器
@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //过滤器初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        Filter.super.init(filterConfig);
        System.out.println("过滤器已经初始化");
    }

    //过滤
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)throws IOException,ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ObjectMapper objectMapper = new ObjectMapper(); //调用objectMapper

        //若为登录操作直接放行
        String url = request.getRequestURI().toString();
        log.info("请求的url:{}",url);
        if(url.contains("login")){
            log.info("正在登录，放行。");
            filterChain.doFilter(request,response);
            return;
        }

        //不是登录操作，检查JWT令牌
        String jwt = request.getHeader("token");

        //检查令牌是否存在(现在在Postman测试时需手动在请求头（Headers）中加入token)
        if(!StringUtils.hasLength(jwt)){
            log.info("令牌不存在，返回未登录错误信息。");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = objectMapper.writeValueAsString(error);
            response.getWriter().write(notLogin);
            return;
        }

        //检查令牌是否正确
        try{
            JwtUtils.parseJWT(jwt);
        } catch (Exception exception) {
            exception.printStackTrace(); //定位异常信息
            log.info("令牌解析失败，返回未登录错误信息。");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = objectMapper.writeValueAsString(error);
            response.getWriter().write(notLogin);
            return;
        }

        //检查完毕，请求非登录但已有合法的JWT令牌，放行。
        log.info("令牌合法，放行");
        filterChain.doFilter(request,response);

    }

    //销毁过滤器
    @Override
    public void destroy(){
        Filter.super.destroy();
        System.out.println("过滤器已销毁");
    }


}
