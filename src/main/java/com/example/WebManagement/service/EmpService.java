package com.example.WebManagement.service;

import com.example.WebManagement.pojo.Emp;
import com.example.WebManagement.pojo.PageBean;

import java.util.List;

public interface EmpService {
    //List<Emp> list();

    void deleteById(Integer id);

    void insertEmp(Emp emp);

    PageBean page(Integer page, Integer pageSize, String name, Integer gender);

    Emp getById(Integer id);

    void updateById(Emp emp);
}
