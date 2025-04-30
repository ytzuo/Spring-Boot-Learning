package com.example.WebManagement.mapper;

import com.example.WebManagement.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpMapper {
    //@Select("SELECT * FROM emp")
    List<Emp> list(String name, Integer gender);

    @Delete("DELETE FROM emp WHERE id = #{id}")
    void deleteById(Integer id);

    @Insert("INSERT INTO emp" +
            "(id, username, password, name, gender, img, job, dept_id, entry_time, create_time, update_time) " +
            "VALUES" +
            "(#{id}, #{username}, #{password}, #{name}, #{gender}, #{img}, #{job}, #{dept_id}, #{entry_time}, #{create_time}, #{update_time})")
    void insertEmp(Emp emp);

    @Select("SELECT * FROM emp WHERE id = #{id}")
    Emp getById(Integer id);

    @Update("UPDATE emp "+
            "SET id = #{id}, username = #{username}, password = #{password}, name  = #{name}, gender = #{gender}, img = #{img}, job = #{job}, dept_id = #{dept_id}, entry_time = #{entry_time}, create_time = #{create_time}, update_time = #{update_time} "+
            "WHERE id = #{id}")
    void updateById(Emp emp);

    @Delete("DELETE FROM emp " +
            "WHERE dept_id = #{dept_id}")
    void deleteByDeptId(Integer dept_id);

    /*
    @Select("SELECT COUNT(*) FROM emp")
    public Long count();

    @Select("SELECT * FROM emp LIMIT #{start}, #{pageSize}")
    public List<Emp> page(Integer start, Integer pageSize);
     */
}
