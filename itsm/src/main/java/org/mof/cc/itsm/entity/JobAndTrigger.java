package org.mof.cc.itsm.entity;

import java.math.BigInteger;

import lombok.Data;

/**
 * <p>
 * 批处理任务调度实体类（自定义）
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月7日 下午5:10:08
 */
@Data
public class JobAndTrigger {
	private String JOB_NAME;
	private String JOB_GROUP;
	private String JOB_CLASS_NAME;
	private String TRIGGER_NAME;
	private String TRIGGER_GROUP;
	private BigInteger REPEAT_INTERVAL;
	private BigInteger TIMES_TRIGGERED;
	private String CRON_EXPRESSION;
	private String TIME_ZONE_ID;
}
