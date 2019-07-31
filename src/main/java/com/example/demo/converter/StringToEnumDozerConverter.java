package com.example.demo.converter;

import com.github.dozermapper.core.CustomConverter;
import com.github.dozermapper.core.MappingException;

import java.lang.reflect.Method;

public class StringToEnumDozerConverter implements CustomConverter {

    @Override
    public Object convert(Object destination, Object source, Class<?> destinationClass,    Class<?> sourceClass) {
        if(source == null)
            return null;
        if(destinationClass != null){
            if(destinationClass.getSimpleName().equalsIgnoreCase("String")){
                return this.getString(source);
            }else if( destinationClass.isEnum()){
                return this.getEnum(destinationClass, source);
            }else{
                throw new MappingException(new StringBuilder("Converter ").append(this.getClass().getSimpleName())
                        .append(" was used incorrectly. Arguments were: ")
                        .append(destinationClass.getClass().getName())
                        .append(" and ")
                        .append(source).toString());
            }
        }
        return null;
    }

    private Object getString(Object object){
        return object.toString();
    }

    private Object getEnum(Class<?> destinationClass, Object source){
        Method [] ms = destinationClass.getMethods();
        try {
            //有定义getEnumByCode则执行getEnumByCode方法
            for(Method m : ms){
                if(m.getName().equalsIgnoreCase("getEnumByCode")){
                    return m.invoke( destinationClass.getClass(), String.valueOf(source));
                }
            }
            //未定义getEnumByCode则执行枚举的valueOf方法
            Method m = destinationClass.getDeclaredMethod("valueOf", String.class);
            return m.invoke(destinationClass.getClass(), String.valueOf(source));
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

}
