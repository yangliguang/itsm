package org.mof.cc.itsm.service;

import org.mof.cc.itsm.entity.JobAndTrigger;

import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 批处理调度服务接口
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月7日 下午4:38:37
 */
public interface JobAndTriggerService {
	public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);
}
