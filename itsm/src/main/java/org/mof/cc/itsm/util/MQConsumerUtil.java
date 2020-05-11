package org.mof.cc.itsm.util;

import java.io.UnsupportedEncodingException;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.mof.cc.itsm.config.RocketMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.OMS;
import io.openmessaging.OMSBuiltinKeys;
import io.openmessaging.consumer.PullConsumer;
import io.openmessaging.producer.Producer;

/**
 * <p>
 * RocketMQ消费者工具类
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月8日 下午3:07:16
 */
@Service
public class MQConsumerUtil {

	private static final Logger log = LoggerFactory.getLogger(MQConsumerUtil.class);

	/**
	 * 消费者实体对象
	 */
	private DefaultMQPushConsumer consumer;
	
	/**
	 * 
	 */
	private MessagingAccessPoint messagingAccessPoint;

	/**
	 * 构造函数无逻辑，消费者初始化放入getConsumer，处理逻辑放入业务中
	 */
	public MQConsumerUtil() throws MQClientException {

	}

	public DefaultMQPushConsumer getConsumer(String consumerGroup) {
		consumer = new DefaultMQPushConsumer(consumerGroup);
		consumer.setNamesrvAddr(RocketMQConfig.NAME_SERVER);
		// 消费模式:一个新的订阅组第一次启动从队列的最后位置开始消费 后续再启动接着上次消费的进度开始消费
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
		return this.consumer;
	}
	

}
