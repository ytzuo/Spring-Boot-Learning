package com.example.WebManagement.aop;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class MyAspect {
    private Logger log = LoggerFactory.getLogger(MyAspect.class);
    @Around("@annotation(com.example.WebManagement.aop.myLog)")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] arr = joinPoint.getArgs();

        Long begin = System.currentTimeMillis();
        Object ob = joinPoint.proceed();
        Long end = System.currentTimeMillis();

        Object result = JSONObject.toJSONString(ob);
        Long time = end-begin;
        log.info("方法{}调用, 传入参数{}, 返回结果{}, 执行总耗时{}", methodName, arr, result, time);
        return ob;
    }
}
