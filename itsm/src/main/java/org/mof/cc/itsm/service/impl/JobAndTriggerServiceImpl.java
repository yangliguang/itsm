package org.mof.cc.itsm.service.impl;

import java.util.List;

import org.mof.cc.itsm.entity.JobAndTrigger;
import org.mof.cc.itsm.mapper.JobAndTriggerMapper;
import org.mof.cc.itsm.service.JobAndTriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 批处理调度服务实现类
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月7日 下午4:39:16
 */
@Service
public class JobAndTriggerServiceImpl implements JobAndTriggerService {
	@Autowired
	private JobAndTriggerMapper jobAndTriggerMapper;
	
	public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<JobAndTrigger> list = jobAndTriggerMapper.getJobAndTriggerDetails();
		PageInfo<JobAndTrigger> page = new PageInfo<JobAndTrigger>(list);
		return page;
	}
}
