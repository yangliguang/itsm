package org.mof.cc.itsm.controller;

import org.mof.cc.itsm.entity.DemoUser;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//SpringBoot项目的Bean装配默认规则是根据Application类所在的包位置从上往下扫描！
//显式声明要扫描的包，将HiConfig类中的bean注入
@ComponentScan(basePackages = "org.mof.cc.itsm.config")
@ComponentScan(basePackages = "org.mof.cc.itsm.dao")
public class SampleController {
	/*
	 * 方式一，EnableAutoConfiguration Spring boot的核心功能，自动配置（根据当前引入的Jar包）
	 * 比如引入了jackson的Jar包，就会自动配置json转换，就可以使用@ResponseBody
	 */
	@RequestMapping("/hello")
	@ResponseBody
	String sayHello() {
		return "hello yanglg";
	}

	/*
	 * 方式二，@EnableAutoConfiguration只是实现自动配置一个功能
	 * SpringBootApplication则是一个组合注解，包括@EnableAutoConfiguration及其它多个注解， 是一个项目的启动注解
	 */
	// 注入bean hi
	@Autowired
	String hi;

	@RequestMapping("/hi")
	@ResponseBody
	String sayHi() {
		return hi;
	}

	/*
	 * Post方式 传统REST用法
	 ** 
	 */
	// 注入bean hello
	@Autowired
	String hello;

	// 配置方法的post请求url
	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	// 将返回值转换成特定格式，默认是json,必须有，否则报错
	@ResponseBody
	public String sayHelloByPost() {
		return hello + " by post method";
	}

	/*
	 * Post方式 Spring的新注解，简化上面的方法
	 */
	// 直接指定Post请求，同样也有@GetMapping
	@PostMapping("/hi")
	@ResponseBody
	public String sayHiByPost() {
		return hi + " by post method";
	}

	/*
	 * Post方式，接收json参数 postman请求参数: { "id":1, "username":"yanglg", "fullname":"杨立广",
	 * "password":"12345678" }
	 */
	@PostMapping("/hi2")
	@ResponseBody
	public String sayHiPost(@RequestBody DemoUser user) {
		return hi + " by post method" + " with request parameters" + user.getId() + user.getUsername()
				+ user.getFullname() + user.getPassword();
	}

//	@Autowired
//	int insert;
//	@GetMapping("/itsm/add")
//	@ResponseBody
//	public int add() {
//		return 
//	}

}
