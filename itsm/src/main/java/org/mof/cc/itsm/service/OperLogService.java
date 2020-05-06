package org.mof.cc.itsm.service;

import org.mof.cc.itsm.entity.OperLog;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangliguang
 * @since 2020-04-30
 */
public interface OperLogService extends IService<OperLog> {
	
	public OperLog queryOperLogById(int id);
	
	public void updateOperLog(OperLog operLog);
	
	public void saveOperLog(OperLog operLog);
	
	public void deleteOperLog(int id);
}
