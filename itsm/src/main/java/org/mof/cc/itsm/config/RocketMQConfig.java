package org.mof.cc.itsm.config;

import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * RocketMQ全局配置
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月8日 下午2:59:56
 */
public class RocketMQConfig {

	/**
     * Name Server 地址，因为是集群部署 所以有多个用 分号 隔开
     */
    public static final String NAME_SERVER = "127.0.0.1:9876";
    /**
     * 主题名称 主题一般是服务器设置好 而不能在代码里去新建topic（ 如果没有创建好，生产者往该主题发送消息 会报找不到topic错误）
     */
    public static final String TOPIC_ORDER = "topic_itsm_order";
    
    /**
     * 订单模块生产者组
     */
	public static final String PRODUCER_GROUP_ORDER = "producer_group_order";
	
	/**
     * 撮合交易模块消费者组
     */
	public static final String CONSUMER_GROUP_ORDER = "consumer_group_order";
	
	/**
	 * OMS URL
	 */
	public static final String OMS_URL = "oms:rocketmq://127.0.0.1:9876/default:default";
	
}
