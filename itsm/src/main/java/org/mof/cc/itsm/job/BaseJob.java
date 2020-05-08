package org.mof.cc.itsm.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * <p>
 * 任务接口
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月7日 下午4:48:26
 */
public interface BaseJob extends Job{
	public void execute(JobExecutionContext context) throws JobExecutionException;
}
