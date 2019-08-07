package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.example.demo.enums.SexEnum;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder(toBuilder = true)
public class User {
    @TableId(value = "id",type = IdType.INPUT)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private SexEnum sex;
    @Version
    private Integer version;
    @TableLogic
    private String isDelete;

    @Tolerate
    public User(){ }
}
