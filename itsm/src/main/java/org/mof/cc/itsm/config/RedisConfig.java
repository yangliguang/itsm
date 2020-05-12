package org.mof.cc.itsm.config;

import java.io.Serializable;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * <p>
 * Redis配置
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月1日 下午6:48:35
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig {
	
	/**
     * 短信发送优先级（0-9），优先级依次升高，新建9条队列
     */
    public static final String SMS_QUEUE_PRIORITY_0 = "itsm_sms_queue_00";
    public static final String SMS_QUEUE_PRIORITY_1 = "itsm_sms_queue_01";
    public static final String SMS_QUEUE_PRIORITY_2 = "itsm_sms_queue_02";
    public static final String SMS_QUEUE_PRIORITY_3 = "itsm_sms_queue_03";
    public static final String SMS_QUEUE_PRIORITY_4 = "itsm_sms_queue_04";
    public static final String SMS_QUEUE_PRIORITY_5 = "itsm_sms_queue_05";
    public static final String SMS_QUEUE_PRIORITY_6 = "itsm_sms_queue_06";
    public static final String SMS_QUEUE_PRIORITY_7 = "itsm_sms_queue_07";
    public static final String SMS_QUEUE_PRIORITY_8 = "itsm_sms_queue_08";
    public static final String SMS_QUEUE_PRIORITY_9 = "itsm_sms_queue_09";
    
	
	 @Bean
	    public RedisTemplate<String, Serializable> redisCacheTemplate(LettuceConnectionFactory factory) {
	        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
	        template.setKeySerializer(new StringRedisSerializer());
	        template.setHashKeySerializer(new StringRedisSerializer());
	        template.setHashValueSerializer(new StringRedisSerializer());
	        template.setValueSerializer(new StringRedisSerializer());
	        template.setConnectionFactory(factory);
	        return template;
	    }
	 
	    @Bean
	    public HashOperations<String, String, String> hashOperations(RedisTemplate<String, String> redisTemplate) {
	        return redisTemplate.opsForHash();
	    }
	 
	    @Bean
	    public ValueOperations<String, String> valueOperations(RedisTemplate<String, String> redisTemplate) {
	        return redisTemplate.opsForValue();
	    }
	 
	    @Bean
	    public SetOperations<String, String> setOperations(RedisTemplate<String, String> redisTemplate) {
	        return redisTemplate.opsForSet();
	    }
	 
	    @Bean
	    public ListOperations<String, String> listOperations(RedisTemplate<String, String> redisTemplate) {
	        return redisTemplate.opsForList();
	    }

}

