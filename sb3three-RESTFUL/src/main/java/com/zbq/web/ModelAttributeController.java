package com.zbq.web;

import com.zbq.domain.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangboqing on 2017/7/11.
 * 注解@ModelAttribut的使用
 */
@RestController
@RequestMapping("/modelAttribute")
public class ModelAttributeController {
    //访问控制器方法时，会优先调用被@ModelAttribute修饰的方法,但是加了@RequestMapping的方法不会被调用

    //1.@ModelAttribute注释void返回值的方法
//    @ModelAttribute
    public void oneOperate(Model model) {
        System.out.println("1");

        User user = new User();
        user.setName("haha");

        model.addAttribute("user",user);
    }

    @ModelAttribute
    @RequestMapping("/aaa")
    public void twoOperate(Model model) {
        //不会打印
        System.out.println("2");
    }

    //2.@ModelAttribute注释返回具体类的方法 会将返回的结果放入model域中
    //controller方法接收参数的对象和该对象是同一个对象，具有传递性
    @ModelAttribute
    public User threeOperate() {
        User user = new User();
        user.setName("333");
        user.setAge(333);
        user.setId(3l);
        return user;
    }

    @RequestMapping("/get/user")
    public User getUser(User user) {
        return user;
    }

}
