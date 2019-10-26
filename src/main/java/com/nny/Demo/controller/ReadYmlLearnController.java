package com.nny.Demo.controller;

import com.nny.Demo.service.ReadPropertiesLearnConfigure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * date 2019.3.19
 * writer liting
 * content 读取yml文件中的常量
 */
@RestController
public class ReadYmlLearnController {

    @Autowired
    private ReadPropertiesLearnConfigure readPropertiesLearnConfigure;

    @GetMapping("/test1")
    public void test(){
        System.out.print(readPropertiesLearnConfigure.getGrades());
    }
}
