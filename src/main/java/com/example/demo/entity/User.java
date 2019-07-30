package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder(toBuilder = true)
public class User {
    @TableId(value = "id",type = IdType.INPUT)
    private Long id;
//    @TableField(condition=SqlCondition.LIKE)
    private String name;
    private Integer age;
    private String email;

    @Tolerate
    public User(){ }
}
