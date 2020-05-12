package org.mof.cc.itsm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.mof.cc.itsm.config.RedisConfig;
import org.mof.cc.itsm.entity.SMSCount;
import org.mof.cc.itsm.entity.SMSMessage;
import org.mof.cc.itsm.service.SMSMessageService;
import org.mof.cc.itsm.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>
 * 短信服务，通过Redis队列实现不同优先级短信的依次发送 初始化10条队列，按照优先级依次发送
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月12日 上午9:39:38
 */
@Service
public class SMSMessageServiceImpl implements SMSMessageService {

	private static final Logger log = LoggerFactory.getLogger(SMSMessageServiceImpl.class);

	private RedisUtil redisUtil;
	

	/**
	 * 默认构造函数，定时调度使用时无法注入RedisUtil
	 */
	public SMSMessageServiceImpl() {
		
	}
	
	/**
	 * 单例bean，初始化时新建10条短信队列
	 */
	// 构造函数执行顺序早于自动注入的依赖
	// Spring给出的建议是：使用构造函数时注入依赖，@Autowired置于构造函数上,构造函数中注入bean作为参数
	@Autowired
	public SMSMessageServiceImpl(RedisUtil redisUtil) {
		this.redisUtil = redisUtil;
		System.out.println(redisUtil);
		redisUtil.set(RedisConfig.SMS_QUEUE_PRIORITY_0, "");
		redisUtil.set(RedisConfig.SMS_QUEUE_PRIORITY_1, "");
		redisUtil.set(RedisConfig.SMS_QUEUE_PRIORITY_2, "");
		redisUtil.set(RedisConfig.SMS_QUEUE_PRIORITY_3, "");
		redisUtil.set(RedisConfig.SMS_QUEUE_PRIORITY_4, "");
		redisUtil.set(RedisConfig.SMS_QUEUE_PRIORITY_5, "");
		redisUtil.set(RedisConfig.SMS_QUEUE_PRIORITY_6, "");
		redisUtil.set(RedisConfig.SMS_QUEUE_PRIORITY_7, "");
		redisUtil.set(RedisConfig.SMS_QUEUE_PRIORITY_8, "");
		redisUtil.set(RedisConfig.SMS_QUEUE_PRIORITY_9, "");
	}

	/**
	 * ArryayList格式的队列转为json String
	 * 
	 * @param List
	 * @return
	 */
	public String list2Json(List<SMSMessage> list) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonstr = null;
		try {
			jsonstr = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonstr;
	}

	/**
	 * ArraList json转化为 Arraylist 对象
	 * 
	 * @param json
	 * @return
	 */
	public List<SMSMessage> json2List(String jsonStr) {
		if (null == jsonStr || "".equals(jsonStr)) {
			return new ArrayList<SMSMessage>();
		} else {
			ArrayList<SMSMessage> obj = (ArrayList<SMSMessage>) JSONObject.parseArray(jsonStr, SMSMessage.class);
			return obj;
		}
	}

	/**
	 * 接收一条短信请求
	 */
	@Override
	public String postSMS(SMSMessage message) {
		String queueName;
		switch (message.getPriority()) {
		case 0:
			queueName = RedisConfig.SMS_QUEUE_PRIORITY_0;
			break;
		case 1:
			queueName = RedisConfig.SMS_QUEUE_PRIORITY_1;
			break;
		case 2:
			queueName = RedisConfig.SMS_QUEUE_PRIORITY_2;
			break;
		case 3:
			queueName = RedisConfig.SMS_QUEUE_PRIORITY_3;
			break;
		case 4:
			queueName = RedisConfig.SMS_QUEUE_PRIORITY_4;
			break;
		case 5:
			queueName = RedisConfig.SMS_QUEUE_PRIORITY_5;
			break;
		case 6:
			queueName = RedisConfig.SMS_QUEUE_PRIORITY_6;
			break;
		case 7:
			queueName = RedisConfig.SMS_QUEUE_PRIORITY_7;
			break;
		case 8:
			queueName = RedisConfig.SMS_QUEUE_PRIORITY_8;
			break;
		case 9:
			queueName = RedisConfig.SMS_QUEUE_PRIORITY_9;
			break;
		default:
			return "短信优先级错误！";
		}
		String jsonStr = redisUtil.get(queueName);
		log.info("从队列获取消息成功={}", jsonStr);
		List<SMSMessage> messages = json2List(jsonStr);
		messages.add(message);
		jsonStr = list2Json(messages);
		redisUtil.set(queueName, jsonStr);
		log.info("设置队列成功={}", jsonStr);
		return "短信存入队列成功！";
	}

	/**
	 * 从队列获取一条短信
	 * 
	 * @return
	 */
	@Override
	public String sendSMS() {
		List<SMSMessage> messages;
		SMSMessage message;
		// 优先级依次降低9->0
		// 9
		messages = json2List(redisUtil.get(RedisConfig.SMS_QUEUE_PRIORITY_9));
		if (messages.size() > 0) {
			message = messages.get(0);
			log.info("发送一条短信成功={}", message);
			// 此处连接短信发送网关，处理后从队列中删除
			messages.remove(0);
			redisUtil.set(RedisConfig.SMS_QUEUE_PRIORITY_9, list2Json(messages));
			return "发送一条短信成功！";
		}
		// 8
		messages = json2List(redisUtil.get(RedisConfig.SMS_QUEUE_PRIORITY_8));
		if (messages.size() > 0) {
			message = messages.get(0);
			log.info("发送一条短信成功={}", message);
			// 此处连接短信发送网关，处理后从队列中删除
			messages.remove(0);
			redisUtil.set(RedisConfig.SMS_QUEUE_PRIORITY_8, list2Json(messages));
			return "发送一条短信成功！";
		}
		// 7
		messages = json2List(redisUtil.get(RedisConfig.SMS_QUEUE_PRIORITY_7));
		if (messages.size() > 0) {
			message = messages.get(0);
			log.info("发送一条短信成功={}", message);
			// 此处连接短信发送网关，处理后从队列中删除
			messages.remove(0);
			redisUtil.set(RedisConfig.SMS_QUEUE_PRIORITY_7, list2Json(messages));
			return "发送一条短信成功！";
		}
		// 6
		messages = json2List(redisUtil.get(RedisConfig.SMS_QUEUE_PRIORITY_6));
		if (messages.size() > 0) {
			message = messages.get(0);
			log.info("发送一条短信成功={}", message);
			// 此处连接短信发送网关，处理后从队列中删除
			messages.remove(0);
			redisUtil.set(RedisConfig.SMS_QUEUE_PRIORITY_6, list2Json(messages));
			return "发送一条短信成功！";
		}
		// 5
		messages = json2List(redisUtil.get(RedisConfig.SMS_QUEUE_PRIORITY_5));
		if (messages.size() > 0) {
			message = messages.get(0);
			log.info("发送一条短信成功={}", message);
			// 此处连接短信发送网关，处理后从队列中删除
			messages.remove(0);
			redisUtil.set(RedisConfig.SMS_QUEUE_PRIORITY_5, list2Json(messages));
			return "发送一条短信成功！";
		}
		// 4
		messages = json2List(redisUtil.get(RedisConfig.SMS_QUEUE_PRIORITY_4));
		if (messages.size() > 0) {
			message = messages.get(0);
			log.info("发送一条短信成功={}", message);
			// 此处连接短信发送网关，处理后从队列中删除
			messages.remove(0);
			redisUtil.set(RedisConfig.SMS_QUEUE_PRIORITY_4, list2Json(messages));
			return "发送一条短信成功！";
		}
		// 3
		messages = json2List(redisUtil.get(RedisConfig.SMS_QUEUE_PRIORITY_3));
		if (messages.size() > 0) {
			message = messages.get(0);
			log.info("发送一条短信成功={}", message);
			// 此处连接短信发送网关，处理后从队列中删除
			messages.remove(0);
			redisUtil.set(RedisConfig.SMS_QUEUE_PRIORITY_3, list2Json(messages));
			return "发送一条短信成功！";
		}
		// 2
		messages = json2List(redisUtil.get(RedisConfig.SMS_QUEUE_PRIORITY_2));
		if (messages.size() > 0) {
			message = messages.get(0);
			log.info("发送一条短信成功={}", message);
			// 此处连接短信发送网关，处理后从队列中删除
			messages.remove(0);
			redisUtil.set(RedisConfig.SMS_QUEUE_PRIORITY_2, list2Json(messages));
			return "发送一条短信成功！";
		}
		// 2
		messages = json2List(redisUtil.get(RedisConfig.SMS_QUEUE_PRIORITY_1));
		if (messages.size() > 0) {
			message = messages.get(0);
			log.info("发送一条短信成功={}", message);
			// 此处连接短信发送网关，处理后从队列中删除
			messages.remove(0);
			redisUtil.set(RedisConfig.SMS_QUEUE_PRIORITY_2, list2Json(messages));
			return "发送一条短信成功！";
		}
		// 1
		messages = json2List(redisUtil.get(RedisConfig.SMS_QUEUE_PRIORITY_1));
		if (messages.size() > 0) {
			message = messages.get(0);
			log.info("发送一条短信成功={}", message);
			// 此处连接短信发送网关，处理后从队列中删除
			messages.remove(0);
			redisUtil.set(RedisConfig.SMS_QUEUE_PRIORITY_1, list2Json(messages));
			return "发送一条短信成功！";
		}
		// 0
		messages = json2List(redisUtil.get(RedisConfig.SMS_QUEUE_PRIORITY_0));
		if (messages.size() > 0) {
			message = messages.get(0);
			log.info("发送一条短信成功={}", message);
			// 此处连接短信发送网关，处理后从队列中删除
			messages.remove(0);
			redisUtil.set(RedisConfig.SMS_QUEUE_PRIORITY_0, list2Json(messages));
			return "发送一条短信成功！";
		}
		log.info("所有队列为空！");
		return "所有队列为空！";
	}
	
	/**
	 * 查看队列深度情况
	 */
	@Override
	public SMSCount getSMSCount() {
		SMSCount smsCount = new SMSCount();
		smsCount.setQueue0Count(json2List(redisUtil.get(RedisConfig.SMS_QUEUE_PRIORITY_0)).size());
		smsCount.setQueue1Count(json2List(redisUtil.get(RedisConfig.SMS_QUEUE_PRIORITY_1)).size());
		smsCount.setQueue2Count(json2List(redisUtil.get(RedisConfig.SMS_QUEUE_PRIORITY_2)).size());
		smsCount.setQueue3Count(json2List(redisUtil.get(RedisConfig.SMS_QUEUE_PRIORITY_3)).size());
		smsCount.setQueue4Count(json2List(redisUtil.get(RedisConfig.SMS_QUEUE_PRIORITY_4)).size());
		smsCount.setQueue5Count(json2List(redisUtil.get(RedisConfig.SMS_QUEUE_PRIORITY_5)).size());
		smsCount.setQueue6Count(json2List(redisUtil.get(RedisConfig.SMS_QUEUE_PRIORITY_6)).size());
		smsCount.setQueue7Count(json2List(redisUtil.get(RedisConfig.SMS_QUEUE_PRIORITY_7)).size());
		smsCount.setQueue8Count(json2List(redisUtil.get(RedisConfig.SMS_QUEUE_PRIORITY_8)).size());
		smsCount.setQueue9Count(json2List(redisUtil.get(RedisConfig.SMS_QUEUE_PRIORITY_9)).size());
		return smsCount;
	}

}
