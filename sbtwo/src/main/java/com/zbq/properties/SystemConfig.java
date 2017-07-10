package com.zbq.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by zhangboqing on 2017/7/10.
 */
@Component
public class SystemConfig {

    @Value("${movie.name}")
    private  String name;
    @Value("${movie.director}")
    private  String director;

    //随机数
    @Value("${random.string}")
    private  String randomStr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRandomStr() {
        return randomStr;
    }

    public void setRandomStr(String randomStr) {
        this.randomStr = randomStr;
    }
}
