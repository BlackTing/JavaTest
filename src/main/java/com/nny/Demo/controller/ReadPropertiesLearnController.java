package com.nny.Demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * date 2019.3.19
 * writer liting
 * content 使用@Value注解读取属性文件中的list结构的常量值
 */
@RestController
@PropertySource("classpath:content.properties") //添加属性文件的路径，才能找到属性
public class ReadPropertiesLearnController {

    @Value(value = "${data.relations}")
    private List<String> relations;

    @GetMapping("/test")
    public void test(){
        System.out.print(relations);
    }
}
