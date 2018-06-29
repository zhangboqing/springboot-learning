package com.zbq2.autoconfig;

import lombok.Data;

/**
 * @author zhangboqing
 * @date 2018/6/29
 */
@Data
public class HelloService {

    private String msg;

    public String sayHello() {
        return new StringBuilder("Hello").append(msg).toString();
    }
}
