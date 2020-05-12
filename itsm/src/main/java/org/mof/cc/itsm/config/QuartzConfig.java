package org.mof.cc.itsm.config;

import org.mof.cc.itsm.quartz.PrintLogJob;
import org.mof.cc.itsm.service.SMSMessageService;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.alibaba.fastjson.JSON;

/**
 * <p>
 * 简单的quartz调度配置
 * 非持久化
 * 持久化参考SchedulerConfig
 * 示例两种创建方式: 一是为每个job创建一个job类（定时打印时间PrintLogJob），二是使用内部类getSMSQueueJob
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月12日 上午11:56:47
 */
@Configuration
public class QuartzConfig {
	
	@Autowired
	private SMSMessageService smsMessageServie;

	/**
	 * 关联定时打印日志Job，PrintLogJob
	 */
	@Bean
    public JobDetail printLogJobDetail(){
        return JobBuilder.newJob(PrintLogJob.class).storeDurably().build();
    }
	
	/**
	 * PrintLogJob触发器
	 * @return
	 */
	@Bean
    public Trigger printLogJobTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10) //每10秒执行一次
                .repeatForever(); //永久重复，一直执行下去
        return TriggerBuilder.newTrigger()
                .forJob(printLogJobDetail())
                .withSchedule(scheduleBuilder)
                .build();
    }
	
	/**
	 * 方式二创建定时调度任务，
	 */
	@Bean
	public JobDetail smsJobDetail(){
	    QuartzJobBean smsJob = new QuartzJobBean() {
	        @Override
	        protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
	            System.out.println("----定时调度任务smsJob----" + JSON.toJSONString(smsMessageServie.getSMSCount()));
	            
	        }
	    };
	    return JobBuilder.newJob(smsJob.getClass()).storeDurably().build();
	}

	@Bean
	public Trigger smsJobtrigger(){
	    //JobDetail的bean注入不能省略
	    SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
	            .withIntervalInSeconds(10) //每2秒执行一次
	            .repeatForever(); //永久重复，一直执行下去
	    return TriggerBuilder.newTrigger()
	            .forJob(smsJobDetail())
	            .withSchedule(scheduleBuilder).build();
	}
}
