package com.zbq.sbone.test;

import com.zbq.sbone.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by zhangboqing on 2017/7/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {

    @Autowired
    private HelloController helloController;

    @Test
    public void run() {

        System.out.println(helloController.helloWorld());
    }

}
