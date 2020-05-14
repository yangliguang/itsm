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
public class ExchangeMessage {

	private static final long serialVersionUID=1L;
	
	public ExchangeMessage() {
		this.createTime = new Date();
	}
	
	/**
     * 业务类型（0其它，1出让，2回购）
     */
    private Integer exchangeType;

    /**
     * 委托人
     */
    private String exchanger;

    /**
     * 标的数量
     */
    private Integer exchangeNumber;
 

    /**
     * 标的单价
     */
    private Integer exchangePrice;
    
    /**
     *  委托时间
     */
    private Date createTime;
	
	
}
