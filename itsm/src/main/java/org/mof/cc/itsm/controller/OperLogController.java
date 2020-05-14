package org.mof.cc.itsm.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.mof.cc.itsm.entity.OperLog;
import org.mof.cc.itsm.service.OperLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * <p>
 *  集成Elasticsearch
 *  最佳实践
 *  RESTful API
 * </p>
 *
 * @author yangliguang
 * @since 2020-04-30
 */
@RestController
@Api(value = "/operlog", tags = "操作日志模块")
@RequestMapping("/api/operlog")
public class OperLogController {
private static final Logger log = LoggerFactory.getLogger(OperLogController.class);
	
	@Autowired
	private OperLogService operLogService;
		
	//增单条
	@ApiOperation(value = "保存一条操作日志")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> saveOperLog(@RequestBody OperLog operLog){
		try {
			operLogService.saveOperLog(operLog);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch(Exception e) {
			e.printStackTrace();
			log.error("插入出错：{}", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	//增多条
	
	//删单条
	/*
	 * 删除
	 * @param id
	 * @return 
	 */
	@ApiOperation(value = "删除一条操作日志")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteOperLog(@PathVariable("id") int id){
		System.out.println("删除ID：" + id);
		try {
			if(id == 0) {
				//请求参数有误
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			operLogService.deleteOperLog(id);
		} catch(Exception e){
			e.printStackTrace();
			log.error("删除出错：{}", e);
		}
		//500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	//删多条
	
	
	//改单条
	/*
	 * 更新操作日志
	 * @param operLog
	 * @Return
	 */
	@ApiOperation(value = "更新一条操作日志")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> updateOperLog(@RequestBody OperLog operLog){
		try {
			operLogService.updateOperLog(operLog);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch(Exception e) {
			e.printStackTrace();
			log.error("更新出错：{}", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	
	//查单条
	/*
	 * 根据操作日志ID查询操作日志
	 * @param id
	 * @return OperLog
	 */
	@ApiOperation(value = "根据操作日志ID查询一条操作日志")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<OperLog> getOperLogById(@PathVariable("id") int id) {
		try {
			OperLog operLog = operLogService.getById(id);
			if(null == operLog){
				//资源不存在，响应404
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			//200
			return ResponseEntity.ok(operLog);
		} catch(Exception e) {
			e.printStackTrace();
			log.error("查询出错：{}", e);
		}
		//500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	@GetMapping(value = "/operlogs")
	@ApiOperation(value = "查询所有日志记录")
	public Object findAll() {
		log.info("处理查询所有日志请求");
		return operLogService.findAll();
	}
}

