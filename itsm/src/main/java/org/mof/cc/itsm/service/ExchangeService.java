package org.mof.cc.itsm.service;

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
	public boolean postMessage(ExchangeMessage em);
	
	/**
	 * 消费者示例
	 * @return
	 */
	public boolean getMessage(String messageId);
	
	/**
	 * 撮合交易（每隔一段时间自动执行）
	 */
	public boolean dealMessage();
}
