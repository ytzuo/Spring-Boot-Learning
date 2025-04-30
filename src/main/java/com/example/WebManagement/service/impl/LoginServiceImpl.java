package com.example.WebManagement.service.impl;

import com.example.WebManagement.mapper.LoginMapper;
import com.example.WebManagement.pojo.Emp;
import com.example.WebManagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Emp login(Emp emp) {
        return loginMapper.getByUsernameAndPassword(emp);
    }
}
