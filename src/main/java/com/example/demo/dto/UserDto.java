package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class UserDto {

    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String sex;

    @Tolerate
    public UserDto(){ }
}
