package org.mof.cc.itsm.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

@Configuration
@MapperScan("org.mof.cc.itsm.mapper")
public class MyBatisPlusConfig {

	/**
	 * 分页拦截器
	 * @return
	 */
	@Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
