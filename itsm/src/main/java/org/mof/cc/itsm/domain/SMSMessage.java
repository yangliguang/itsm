package org.mof.cc.itsm.domain;

import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 撮合交易，交易信息实体类
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月8日 下午3:15:06
 */
@Data
public class SMSMessage {

	private static final long serialVersionUID = 1L;
	
	public SMSMessage() {
		this.createTime = new Date();
	}
	
	/**
	 * 发送优先级（0-9），优先级依次升高
	 */
	private Integer priority;
	
	/**
     * 收件人
     */
    private String receiverPhone;

    /**
     * 短信内容
     */
    private String content;

    /**
     *  请求时间
     */
    private Date createTime;
	
	
}
