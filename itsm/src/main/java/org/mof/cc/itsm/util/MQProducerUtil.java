package org.mof.cc.itsm.util;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.mof.cc.itsm.config.RocketMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <p>
 * RocketMQ生产者工具类
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月8日 下午3:05:17
 */
@Service
public class MQProducerUtil {
	
	private static final Logger log = LoggerFactory.getLogger(MQProducerUtil.class);


    private DefaultMQProducer producer;
    
    public MQProducerUtil(){
        
    }
    /**
     * 对象在使用之前必须要调用一次，只能初始化一次
     */
    public void start(){
        try {
            this.producer.start();
        } catch (MQClientException e) {
        	log.error("生产者启动失败：{}", e);
        }
    }
  
    public MQProducer getProducer(String producerGroup){
    	//示例生产者
        producer = new DefaultMQProducer(producerGroup);
        //不开启vip通道 开通口端口会减2
        producer.setVipChannelEnabled(false);
        //绑定name server
        producer.setNamesrvAddr(RocketMQConfig.NAME_SERVER);
        return this.producer;
    }
    
    /**
     * 一般在应用上下文，使用上下文监听器，进行关闭
     */
    public void shutdown(){
        this.producer.shutdown();
    }
}
