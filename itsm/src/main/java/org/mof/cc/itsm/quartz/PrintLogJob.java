package org.mof.cc.itsm.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * <p>
 * 定时打印日志
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月12日 下午12:00:59
 */
public class PrintLogJob extends QuartzJobBean{

	@Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("----定时调度任务PrintLogJob----" + sdf.format(new Date()));
    }
}
