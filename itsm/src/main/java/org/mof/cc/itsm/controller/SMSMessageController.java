package org.mof.cc.itsm.controller;

import org.mof.cc.itsm.domain.SMSCount;
import org.mof.cc.itsm.domain.SMSMessage;
import org.mof.cc.itsm.service.SMSMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 短信发发服务RESTful接口
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月12日 上午9:43:00
 */
@RestController
@Api(value = "/sms", tags = "短信服务模块")
@RequestMapping("/api/sms")
public class SMSMessageController {
	private static final Logger log = LoggerFactory.getLogger(SMSMessageController.class);

	@Autowired
	private SMSMessageService smsMessageService;

	/**
	 * 接收短信请求
	 * 
	 * @param message
	 * @return
	 */
	@ApiOperation(value = "接收短信请求")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> postSMS(@RequestBody SMSMessage message) {
		try {
			String result = smsMessageService.postSMS(message);
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("接收请求失败：{}", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("接收失败！");
	}

	/**
	 * 处理短信，发往短信网关
	 * 
	 * @return
	 */
	@ApiOperation(value = "处理一条短信请求，发往短信网关")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> sendSMS() {
		try {
			String result = smsMessageService.sendSMS();
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("处理出错：{}", e);
		}
		// 500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("处理失败！");
	}

	/**
	 * 查看短信积压情况
	 */
	@ApiOperation(value = "查看短信积压情况")
	@RequestMapping(value = "/number", method = RequestMethod.GET)
	public ResponseEntity<SMSCount> getSMSCount() {
		try {
			SMSCount smsCount = smsMessageService.getSMSCount();
			return ResponseEntity.status(HttpStatus.CREATED).body(smsCount);
		} catch (Exception e) {
			log.error("获取短信积压情况出错:", e);
		}
		// 500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

}
