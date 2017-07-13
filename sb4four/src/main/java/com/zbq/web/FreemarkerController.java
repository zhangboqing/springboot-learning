package com.zbq.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhangboqing on 2017/7/13.
 */
@Controller
@RequestMapping("/freemarker")
public class FreemarkerController {


    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("name", "ROBINBALL");
        return "index2";
    }

}

