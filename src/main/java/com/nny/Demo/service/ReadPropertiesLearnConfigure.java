package com.nny.Demo.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * date 2019.3.19
 * writer liting
 * content 读取yml文件中的list结构的常量
 * warn spring boot默认加载application.yml文件，其他名称的yml需要特殊配置后才会加载
 * @PropertySource注解只可以加载proprties文件,无法加载yaml文件
 */
/**
 * You can easily generate your own configuration meta-data file from items annotated
 * with @ConfigurationProperties
 * by using the spring-boot-configuration-processor jar.
 */

@Component
@ConfigurationProperties(prefix = "data")

public class ReadPropertiesLearnConfigure {

    private List<String> grades;

    public List<String> getGrades() {
        return grades;
    }

    public void setGrades(List<String> grades) { //主要是通过set方法输入值
        this.grades = grades;
    }
}
