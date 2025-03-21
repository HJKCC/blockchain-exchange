package com.cc.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/12/4 15:19
 * @Description UserControllerTest
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
@WebAppConfiguration
public class UserControllerTest {
	private MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext applicationContext;

	@Before()  //这个方法在每个方法执行之前都会执行一遍
	public void setup()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();  //初始化MockMvc对象
	}

	@Test
	public void list() throws Exception
	{
		String responseString = mockMvc.perform
				(
						get("/user/list.action")          //请求的url,请求的方法是get
//								.contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
//								.param("id","1")   //添加参数(可以添加多个)
				)
				.andExpect(status().isOk())    //返回的状态是200
				.andDo(print())         //打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
		System.out.println("-----返回的json = " + responseString);
	}
}
