package org.mof.cc.itsm;

import org.mof.cc.itsm.config.RedisConfig;
import org.mof.cc.itsm.config.RocketMQConfig;
import org.mof.cc.itsm.config.SchedulerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * <p>
 * 启动程序
 * </p>
 *
 * @author yangliguang
 * @since 2020-04-24
 */

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {RocketMQConfig.class,
RedisConfig.class, SchedulerConfig.class}))
public class ItsmAppliction {
	public static void main(String[] args) {
		SpringApplication.run(ItsmAppliction.class, args);
	}
}
