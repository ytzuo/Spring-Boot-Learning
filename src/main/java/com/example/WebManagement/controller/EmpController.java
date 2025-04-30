package com.example.WebManagement.controller;

import com.example.WebManagement.pojo.Emp;
import com.example.WebManagement.pojo.PageBean;
import com.example.WebManagement.pojo.Result;
import com.example.WebManagement.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.WebManagement.aop.myLog;
import java.util.List;

@CrossOrigin
@RestController
public class EmpController {
    private static Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private EmpService empService;
    //@RequestMapping(value = "/emps", method = RequestMethod.GET) //指定请求方式为GET

    /*
    @GetMapping("/emps") //等效
    public Result list() {
        log.info("查询全部员工的数据");
        List<Emp> empList = empService.list();
        return Result.success(empList);
    }
    */

    @myLog
    @DeleteMapping("/emps/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除员工: {}", id);
        empService.deleteById(id);
        return Result.success(null);
    }

    @myLog
    @PostMapping("/emps/add")
    public Result insertEmp(@RequestBody Emp emp){
        log.info("新增员工: {}", emp);
        empService.insertEmp(emp);
        return Result.success(null);
    }

    @myLog
    @GetMapping("/emps")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name,
                       Integer gender){
        log.info("分页查询员工, 参数: {}, {}, {}, {}", page-1, pageSize, name, gender);
        PageBean pb =  empService.page(page, pageSize, name, gender);
        return Result.success(pb);
    }

    @myLog
    @GetMapping("/emps/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询员工信息: id={}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @myLog
    @PostMapping("/emps/update")
    public Result updateById(@RequestBody Emp emp){
        log.info("根据id修改员工信息: {}", emp);
        empService.updateById(emp);
        return Result.success(null);
    }

}
