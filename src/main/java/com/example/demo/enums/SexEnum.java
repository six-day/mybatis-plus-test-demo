package com.example.demo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.example.demo.utils.EnumUtil;
import lombok.Getter;

@Getter
public enum SexEnum implements CodeEnum<String> {
    male("1","男"),
    female("2","女"),
    ;

    @EnumValue
    private String code;
    private String message;

    SexEnum(String code,String message){
        this.code = code;
        this.message = message;
    }

    public static SexEnum getEnumByCode(String code){
        return EnumUtil.getEnumByCode(code,SexEnum.class);
    }
}
