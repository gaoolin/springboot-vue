package com.gl.springbootend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user")
@Data
public class User {
    //数据库表的主键定义为自增主键的注解，value值为主键字段名
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String nickName;
    private Integer age;
    private String sex;
    private String address;

    public User() {
    }

    public User(Integer id, String username, String password, String nickName, Integer age, String sex, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickName = nickName;
        this.age = age;
        this.sex = sex;
        this.address = address;
    }
}
