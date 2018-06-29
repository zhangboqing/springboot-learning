package com.zbq.controller;

import com.zbq2.autoconfig.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangboqing
 * @date 2018/6/29
 */
@RestController
public class AutoConfigurationTestController {

    @Autowired
    private HelloService helloService;


    @GetMapping("/hello")
    public String hello() {

        return helloService.sayHello();
    }

}
