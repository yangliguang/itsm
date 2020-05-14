package org.mof.cc.itsm.service;

import org.mof.cc.itsm.domain.SMSCount;
import org.mof.cc.itsm.domain.SMSMessage;

/**
 * <p>
 * 短信服务接口
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月12日 上午9:39:38
 */
public interface SMSMessageService {

	/**
	 * 接收一条短信请求
	 * 
	 * @param message
	 * @return
	 */
	public String postSMS(SMSMessage message);

	/**
	 * 从队列获取一条短信，发送给网关
	 * 
	 * @return
	 */
	public String sendSMS();

	/**
		 * 查看队列深度情况
		 */
		public SMSCount getSMSCount();
}
