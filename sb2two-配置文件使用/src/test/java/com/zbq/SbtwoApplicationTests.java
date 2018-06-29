package com.zbq;

import com.zbq.properties.SystemConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SbtwoApplicationTests {

	@Autowired
	private SystemConfig systemConfig;

	@Test
	public void contextLoads() {
		System.out.println( systemConfig.getName()+":"+systemConfig.getDirector()+":"+systemConfig.getRandomStr());
		//断言
//		Assert.assertEquals(systemConfig.getDirector(),"b");
	}

}
