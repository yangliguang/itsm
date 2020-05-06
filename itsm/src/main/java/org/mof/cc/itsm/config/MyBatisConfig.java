package org.mof.cc.itsm.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.mof.cc.itsm.mapper")
public class MyBatisConfig {

}
