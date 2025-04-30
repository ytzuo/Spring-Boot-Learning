package com.example.WebManagement.pojo;

import lombok.*;

import java.sql.Timestamp;
//import java.time.LocalDateTime;

/*
@Getter
@Setter
@ToString
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {
    private Integer id;
    private String name;
    private Timestamp create_time;
    private Timestamp update_time;

/*
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
*/
}
