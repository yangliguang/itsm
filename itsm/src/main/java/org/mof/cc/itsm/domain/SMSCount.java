package org.mof.cc.itsm.domain;
/**
 * <p>
 * 短信积压情况实例
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月12日 上午10:41:21
 */

import lombok.Data;

@Data
public class SMSCount {
	/**
	 * 优先级为0的短信队列深度
	 */
	private Integer queue0Count;
	
	/**
	 * 优先级为1的短信队列深度
	 */
	
	private Integer queue1Count;
	private Integer queue2Count;
	private Integer queue3Count;
	private Integer queue4Count;
	private Integer queue5Count;
	private Integer queue6Count;
	private Integer queue7Count;
	private Integer queue8Count;
	private Integer queue9Count;
}
