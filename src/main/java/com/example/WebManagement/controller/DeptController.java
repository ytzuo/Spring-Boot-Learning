package com.example.WebManagement.controller;

import com.example.WebManagement.aop.myLog;
import com.example.WebManagement.pojo.Dept;
import com.example.WebManagement.pojo.PageBean;
import com.example.WebManagement.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.example.WebManagement.service.DeptService;
import java.util.List;

//@Slf4j
@CrossOrigin
@RestController
public class DeptController {

    private static Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;
    //@RequestMapping(value = "/depts", method = RequestMethod.GET) //指定请求方式为GET

    //获取所有部门
    @myLog
    @GetMapping("/depts") //等效
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("分页查询员工, 参数: {}, {}", page, pageSize);
        PageBean deptList = deptService.list(page, pageSize);
        return Result.success(deptList);
    }

    //根据id删除
    @myLog
    @DeleteMapping("/depts/{id}")
    public Result deleteById(@PathVariable Integer id){
        log.info("根据id删除部门:{}", id);
        deptService.deleteById(id);
        return Result.success(null);
    }

    //插入新部门
    @myLog
    @PostMapping("/depts/add")
    public Result insertDept(@RequestBody Dept dept){
        log.info("新增部门: {}", dept);
        deptService.insertDept(dept);
        return Result.success(null);
    }
}
