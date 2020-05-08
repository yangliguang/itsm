package org.mof.cc.itsm.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Job示例
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月7日 下午4:54:06
 */
public class NewJob implements BaseJob {
	private static Logger log = LoggerFactory.getLogger(NewJob.class);  
    
    public NewJob() {  
          
    }  
     
    public void execute(JobExecutionContext context)  
        throws JobExecutionException {  
        log.error("New Job执行时间: " + new Date());  
          
    }  
}
