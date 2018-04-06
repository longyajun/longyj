package com.yajun.longyj;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.yajun.longyj.entity.User;
import com.yajun.longyj.model.PageInfo;
import com.yajun.longyj.modules.account.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PageTest {

	private Logger logger = LoggerFactory.getLogger(PageTest.class);
	
	@Autowired
	private IUserService userService;
	
//	@Test
//	public void testFindAll() {
//		List<User> list = userService.list();
//		Assert.assertNotNull(list);
//		System.out.println(JSON.toJSONString(list));
//		logger.info(JSON.toJSONString(list));
//	}
	
	@Test
	public void testFindByPage() {
		Page<User> persons = userService.findByPage(1, 2);
		// 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
		PageInfo<User> pageInfo = new PageInfo<>(persons);
		Assert.assertNotNull(persons);
		logger.info(pageInfo.toString());
		logger.info(JSON.toJSONString(pageInfo));
	}
	
}
