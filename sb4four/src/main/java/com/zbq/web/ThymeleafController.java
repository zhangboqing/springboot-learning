package com.zbq.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhangboqing on 2017/7/13.
 */
@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {


    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/")
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("name", "ROBINBALL");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }

}

