package com.java.converter;


import org.springframework.core.convert.converter.Converter;

/**
 * 转换器（自定义）
 */
public class MyConverter implements Converter<String,Object> {


    @Override
    public Object convert(String s) {
        return null;
    }
}
