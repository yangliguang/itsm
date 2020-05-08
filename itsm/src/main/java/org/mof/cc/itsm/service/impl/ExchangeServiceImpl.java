package org.mof.cc.itsm.service.impl;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.mof.cc.itsm.config.RocketMQConfig;
import org.mof.cc.itsm.entity.ExchangeMessage;
import org.mof.cc.itsm.service.ExchangeService;
import org.mof.cc.itsm.service.VerificationCodeService;
import org.mof.cc.itsm.util.MQConsumerUtil;
import org.mof.cc.itsm.util.MQProducerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

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
	private MQProducerUtil producer;
	private MQConsumerUtil consumer;
	
	
	/*
	 * 委托挂单
	 */
	@Override
	public boolean postMessage(ExchangeMessage em) {
		try {
			//创建生产信息
            Message message = new Message(RocketMQConfig.TOPIC, "testtag", JSON.toJSONBytes(em));
            //发送
            SendResult sendResult = producer.getProducer().send(message);
            log.info("输出生产者信息={}",sendResult);
            return true;
		} catch(Exception e) {
			log.error("挂单异常：{}", e);
			return false;
		}
	}

	/*
	 * 撮合交易（每隔一段时间自动执行）
	 */
	@Override
	public boolean dealMessage() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 消费者示例
	 * @return
	 */
	@Override
	public boolean getMessage(String messageId) {
		try {
//			consumer.getConsumer()
		} catch(Exception e) {
			
		}
		return false;
	}

}
