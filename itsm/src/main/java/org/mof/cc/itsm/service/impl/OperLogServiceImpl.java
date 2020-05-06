package org.mof.cc.itsm.service.impl;

import org.mof.cc.itsm.entity.OperLog;
import org.mof.cc.itsm.entity.OperLog;
import org.mof.cc.itsm.mapper.OperLogMapper;
import org.mof.cc.itsm.service.OperLogService;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangliguang
 * @since 2020-04-30
 */
@Service
public class OperLogServiceImpl extends ServiceImpl<OperLogMapper, OperLog> implements OperLogService {
	public OperLog queryOperLogById(int id) {
		return super.getById(id);
	}
	
	public void updateOperLog(OperLog operLog) {
		UpdateWrapper<OperLog> operLogUpdateWrapper = new UpdateWrapper<>();
		System.out.println(operLog);
		operLogUpdateWrapper.eq("id", operLog.getId());
		System.out.println(operLogUpdateWrapper);
		super.update(operLog, operLogUpdateWrapper);
		return;
	}
	
	public void saveOperLog(OperLog operLog) {
		super.save(operLog);
	}
	
	public void deleteOperLog(int id) {
		super.removeById(id);
	}
}
