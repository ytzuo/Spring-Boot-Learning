package com.example.WebManagement.service.impl;

import com.example.WebManagement.mapper.DeptMapper;
import com.example.WebManagement.mapper.EmpMapper;
import com.example.WebManagement.pojo.Dept;
import com.example.WebManagement.pojo.PageBean;
import com.example.WebManagement.service.DeptService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean list(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Dept> deptList = deptMapper.list();
        Page<Dept> deptPage = (Page<Dept>) deptList;
        return new PageBean(deptPage.getTotal(), deptPage.getResult());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
        empMapper.deleteByDeptId(id);
    }

    @Override
    public void insertDept(Dept dept) {
        dept.setCreate_time(Timestamp.valueOf(LocalDateTime.now()));
        dept.setUpdate_time(Timestamp.valueOf(LocalDateTime.now()));
        deptMapper.insertDept(dept);
    }
}
