package org.mof.cc.itsm.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQConsumer;
import org.apache.rocketmq.client.consumer.MQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.mof.cc.itsm.config.RocketMQConfig;
import org.mof.cc.itsm.domain.ExchangeMessage;
import org.mof.cc.itsm.service.ExchangeService;
import org.mof.cc.itsm.util.MQConsumerUtil;
import org.mof.cc.itsm.util.MQProducerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.OMS;
import io.openmessaging.OMSBuiltinKeys;
import io.openmessaging.consumer.PullConsumer;
import io.openmessaging.producer.Producer;

/**
 * <p>
 * 撮合交易服务实现类
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月8日 下午3:30:23
 */
@Service
public class ExchangeServiceImpl implements ExchangeService {
	
	private static final Logger log = LoggerFactory.getLogger(ExchangeServiceImpl.class);
	
	@Autowired
	private MQProducerUtil producerUtil;
	@Autowired
	private MQConsumerUtil consumerUtil;
	
	
	
	
	/*
	 * 委托挂单
	 */
	public String postMessage2(ExchangeMessage em) {
		MQProducer producer = producerUtil.getProducer(RocketMQConfig.PRODUCER_GROUP_ORDER);
		try {
			String tag;
			String key = em.getExchanger() + em.getCreateTime();
			if(em.getExchangeType() == 1) {
				tag = "tag_sell";
			} else if(em.getExchangeType() == 2) {
				tag = "tag_buy";
			} else
				return "挂单失败，原因：委托类型错误！";
			//创建卖单
            Message message = new Message(RocketMQConfig.TOPIC_ORDER, tag, key, JSON.toJSONBytes(em));
            //启动MQ生产者
            producer.start();
            //发送
            SendResult sendResult = producer.send(message);
            log.info("挂单成功={}",sendResult);
            return "挂单成功！";
		} catch(Exception e) {
			log.error("挂单异常：{}", e);
			return "挂单失败！";
		} finally{
			producer.shutdown();
		}
	}
	
	/*
	 * 委托挂单
	 */
	public String postMessage(ExchangeMessage em) {
		final MessagingAccessPoint messagingAccessPoint =
		           OMS.getMessagingAccessPoint(RocketMQConfig.OMS_URL);
		       messagingAccessPoint.startup();
		       final Producer producer = messagingAccessPoint.createProducer();
		       messagingAccessPoint.startup();
		       System.out.println("====messagingAccessPoint启动成功====");
		       System.out.printf("MessagingAccessPoint startup OK%n");
		       final String queueName = RocketMQConfig.TOPIC_ORDER;
		       producer.startup();
		       System.out.println("====producer启动成功====");
		       io.openmessaging.Message msg = producer.createBytesMessage(queueName, JSON.toJSONBytes(em));
		       io.openmessaging.producer.SendResult sendResult = producer.send(msg);
		       System.out.printf("Send Message OK. MsgId: %s%n", sendResult.messageId());
		       producer.shutdown();
		       System.out.println("====producer关闭成功====");
		       messagingAccessPoint.shutdown();
		       System.out.println("====messagingAccessPoint关闭成功====");
		       return "OpenMessaging挂单成功！";
	}
	

	/*
	 * 撮合交易（手工触发）
	 */
	@Override
	public void dealMessage() throws MQClientException {
		final MessagingAccessPoint messagingAccessPoint =
		           OMS.getMessagingAccessPoint(RocketMQConfig.OMS_URL);
		       messagingAccessPoint.startup();
		       final PullConsumer consumer = messagingAccessPoint.createPullConsumer(
		           OMS.newKeyValue().put(OMSBuiltinKeys.CONSUMER_ID, "OMS_CONSUMER"));
		       messagingAccessPoint.startup();
		       System.out.println("====messagingAccessPoint启动成功====");
		       System.out.printf("MessagingAccessPoint startup OK%n");
		       final String queueName = RocketMQConfig.TOPIC_ORDER;
		       consumer.attachQueue(queueName);
		       consumer.startup();
		       System.out.println("====consumer启动成功====");
		       System.out.printf("Consumer startup OK%n");
		       // 运行直到发现一个消息被发送了
		       boolean stop = false;
		       while (!stop) {
		    	   io.openmessaging.Message message = consumer.receive();
		           if (message != null) {
		        	   ExchangeMessage em = message.getBody(ExchangeMessage.class);
		               String msgId = message.sysHeaders().getString(io.openmessaging.Message.BuiltinKeys.MESSAGE_ID);
		               System.out.printf("Received one message: %s%n", em);
		               consumer.ack(msgId);
		           } else {
		               System.out.printf("Return without any message%n");
		               stop = true;
		           }
		       }
		       consumer.shutdown();
		       System.out.println("====consumer关闭成功====");
		       messagingAccessPoint.shutdown();
		       System.out.println("====messagingAccessPoint关闭成功====");
	}
	
	/*
	 * 撮合交易（自动触发）
	 */
	@Override
	public void dealMessageAuto() throws MQClientException {
		DefaultMQPushConsumer consumer = consumerUtil.getConsumer(RocketMQConfig.CONSUMER_GROUP_ORDER + new Date().hashCode());
        //订阅主题和 标签（ * 代表所有标签)下信息
        consumer.subscribe(RocketMQConfig.TOPIC_ORDER, "*");
        // //注册消费的监听 并在此监听中消费信息，并返回消费的状态信息
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            // msgs中只收集同一个topic，同一个tag，并且key相同的message
            // 会把不同的消息分别放置到不同的队列中
        	System.out.println("msgs大小：" + msgs.size());
            try {
                for (Message msg : msgs) {
                    //消费者获取消息
                    String body = new String(msg.getBody(), "utf-8");
                    ExchangeMessage em = (ExchangeMessage) JSON.parse(body);
                    System.out.println("吃单成功：" + em);
                    log.info("吃单成功={}", em);
                }
                
            } catch (UnsupportedEncodingException e) {
                log.error("消费失败：{}", e);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            } finally {
            	consumer.shutdown();
                System.out.println("=======消费者 关闭成功=======");
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();
        System.out.println("=======消费者 启动成功=======");
	}

	

}
