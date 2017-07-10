package com.zbq.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangboqing on 2017/7/7.
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String helloWorld() {
         return "Hello World";
    }
}
