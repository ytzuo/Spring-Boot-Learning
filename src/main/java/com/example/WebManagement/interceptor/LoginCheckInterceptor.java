package com.example.WebManagement.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.WebManagement.controller.DeptController;
import com.example.WebManagement.pojo.Result;
import com.example.WebManagement.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    private static Logger log = LoggerFactory.getLogger(DeptController.class);

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Object handler) throws Exception {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取请求url
        String url = request.getRequestURL().toString();
        log.info("interceptor: 请求的url: {}", url);

        //判断请求url中是否含有login, 如果有则放行
        if(url.contains("login")){
            log.info("interceptor: 登录操作, 放行.");
            return true;
        }

        //若不是登录操作, 获取请求头中的token
        String jwt = request.getHeader("token");

        //判断jwt令牌是否存在, 不存在则返回错误结果(未登录
        if(!StringUtils.hasLength(jwt)){
            log.info("interceptor: 请求头token为空, 返回未登录");
            Result result = Result.failure();
            result.setMessage("NOT_LOGIN");
            //手动将result转为json
            String not_login_js = JSONObject.toJSONString(result);
            response.getWriter().write(not_login_js);
            return false;
        }

        //如果jwt令牌存在, 则校验jwt令牌
        try{
            JwtUtils.parseJwt(jwt);
        }catch(Exception e){ //解析失败
            log.info("interceptor: 解析令牌失败");
            Result result = Result.failure();
            result.setMessage("NOT_LOGIN");
            //手动将result转为json
            String not_login_js = JSONObject.toJSONString(result);
            response.getWriter().write(not_login_js);
            return false;
        }

        //通过前面的校验, 放行
        log.info("interceptor: 令牌校验通过, 放行");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
