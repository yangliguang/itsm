package org.mof.cc.itsm.controller;

import org.mof.cc.itsm.domain.ExchangeMessage;
import org.mof.cc.itsm.service.ExchangeService;
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
 * 撮合交易接口 集成RocketMQ RESTful接口
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月8日 下午3:12:38
 */
@RestController
@Api(value = "/messages", tags = "撮合交易模块")
@RequestMapping("/api/message")
public class ExchangeController {

	private static final Logger log = LoggerFactory.getLogger(ExchangeController.class);

	@Autowired
	ExchangeService exchangeService;

	// 挂单
	@ApiOperation(value = "委托挂单")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> postMessage(@RequestBody ExchangeMessage em) {
		try {
			exchangeService.postMessage(em);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("委托挂单失败：{}", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("success");
	}
	
	// 手工触发一次撮合
		@ApiOperation(value = "手工撮合")
		@RequestMapping(value = "/hand", method = RequestMethod.GET)
		public ResponseEntity<String> dealMessage() {
			log.info("===开始撮合交易====");
			try {
				exchangeService.dealMessage();
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("手工撮合失败：{}", e);
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("success");
		}
}
