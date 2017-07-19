package com.zbq;

import com.zbq.domain.User;
import com.zbq.domain.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Sb8eightApplicationTests {


	@Autowired
	private UserMapper userMapper;


	@Test
	@Rollback
	public void findByName() throws Exception {
//		userMapper.insert("AAA", 20);
//		User u = userMapper.findByName("AAA");

		User u = userMapper.findOne(1);

		System.out.println(u.getName());
		Assert.assertEquals(20, u.getAge().intValue());
	}

}
