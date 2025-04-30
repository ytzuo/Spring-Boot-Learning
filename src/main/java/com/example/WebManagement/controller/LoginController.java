package com.example.WebManagement.controller;

import com.example.WebManagement.pojo.Emp;
import com.example.WebManagement.pojo.Result;
import com.example.WebManagement.service.LoginService;
import com.example.WebManagement.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class LoginController {
    private static Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工登录: {}, {}", emp.getUsername(), emp.getPassword());
        Emp e = loginService.login(emp);
        if(e != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name", e.getName());
            claims.put("username", e.getUsername());
            String jwt =  JwtUtils.genJwt(claims); //包含了登录用户的信息
            return Result.success(jwt);
        }
        return Result.failure();
    }
}
