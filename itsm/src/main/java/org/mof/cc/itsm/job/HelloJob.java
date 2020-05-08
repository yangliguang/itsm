package org.mof.cc.itsm.job;

import java.util.Date;

import org.mof.cc.itsm.controller.EsController;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月7日 下午4:49:02
 */
public class HelloJob implements BaseJob {
	private static final Logger log = LoggerFactory.getLogger(EsController.class);

	public HelloJob() {

	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.error("Hello Job执行时间: " + new Date());

	}
}
