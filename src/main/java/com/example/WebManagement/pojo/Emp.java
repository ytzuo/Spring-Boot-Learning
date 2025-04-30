package com.example.WebManagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String gender;
    private String img;
    private String job;
    private Integer dept_id;
    private Date entry_time;
    private Timestamp create_time;
    private Timestamp update_time;
}
