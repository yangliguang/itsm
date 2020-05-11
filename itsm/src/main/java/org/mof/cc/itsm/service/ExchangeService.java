package org.mof.cc.itsm.service;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.mof.cc.itsm.entity.ExchangeMessage;

/**
 * <p>
 * 撮合交易接口类
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月8日 下午3:23:20
 */
public interface ExchangeService {

	/**
	 * 委托挂单
	 */
	public String postMessage(ExchangeMessage em);
	
	
	/**
	 * 撮合交易（手工触发）
	 * @throws MQClientException 
	 */
	public void dealMessage() throws MQClientException;
	
	/**
	 * 撮合交易（自动触发）
	 * @throws MQClientException 
	 */
	public void dealMessageAuto() throws MQClientException;
}
