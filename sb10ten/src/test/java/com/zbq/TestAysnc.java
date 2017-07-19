package com.zbq;

import com.zbq.async.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by zhangboqing on 2017/7/19.
 * 注意：异步的注解不能用在同一个类中。
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAysnc {

    @Autowired
    private Task task;


    @Test
    public void run() throws Exception {

        task.run();
    }

}
