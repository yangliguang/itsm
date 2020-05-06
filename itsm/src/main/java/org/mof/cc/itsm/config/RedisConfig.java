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

