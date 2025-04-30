package com.example.WebManagement.service;

import com.example.WebManagement.pojo.Dept;
import com.example.WebManagement.pojo.PageBean;

import java.util.List;

public interface DeptService {
    PageBean list(Integer page, Integer pageSize);

    void deleteById(Integer id);

    void insertDept(Dept dept);
}
