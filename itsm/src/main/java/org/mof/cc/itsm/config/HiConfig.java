package org.mof.cc.itsm.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/*
 * @SpringBootConfiguration表明当前类为配置类，就像xml配置文件一样
 * 现在多用java配置文件，效果一样
 * 此配置中的bean声明，类似xml中的    <bean id="hello" class="String"></bean>  
 * 同样可以被@ComponentScan扫描到
 * 
 */

@SpringBootConfiguration
public class HiConfig {
	@Bean
	public String hi() {
		return "Hi, yanglg!";
	}

	@Bean
	public String hello() {
		return "hello, yanglg!";
	}
}
