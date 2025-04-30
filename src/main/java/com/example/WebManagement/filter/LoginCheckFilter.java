package com.example.WebManagement.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.WebManagement.controller.DeptController;
import com.example.WebManagement.pojo.Result;
import com.example.WebManagement.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;


//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(DeptController.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求的url: {}", url);

        //判断请求url中是否含有login, 如果有则放行
        if(url.contains("login")){
            log.info("登录操作, 放行.");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //若不是登录操作, 获取请求头中的token
        String jwt = request.getHeader("token");

        //判断jwt令牌是否存在, 不存在则返回错误结果(未登录
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空, 返回未登录");
            Result result = Result.failure();
            result.setMessage("NOT_LOGIN");
            //手动将result转为json
            String not_login_js = JSONObject.toJSONString(result);
            response.getWriter().write(not_login_js);
            return;
        }

        //如果jwt令牌存在, 则校验jwt令牌
        try{
            JwtUtils.parseJwt(jwt);
        }catch(Exception e){ //解析失败
            log.info("解析令牌失败");
            Result result = Result.failure();
            result.setMessage("NOT_LOGIN");
            //手动将result转为json
            String not_login_js = JSONObject.toJSONString(result);
            response.getWriter().write(not_login_js);
            return;
        }

        //通过前面的校验, 放行
        log.info("令牌校验通过, 放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
