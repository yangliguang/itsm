package org.mof.cc.itsm.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.mof.cc.itsm.entity.OperLog;
import org.mof.cc.itsm.service.EsService;
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
@Api(value = "/es", tags = "elasticsearch")
@RequestMapping("/api/es")
public class EsController {
private static final Logger log = LoggerFactory.getLogger(EsController.class);
	
	@Autowired
	private EsService esService; 
	
	//添加索引
	@ApiOperation(value = "添加一条索引")
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public ResponseEntity<Void> createIndex(@RequestBody OperLog operLog){
		try {
			if(!esService.isIndexExists(operLog.getTitle()));
				esService.createIndex(operLog.getTitle());
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch(Exception e) {
			e.printStackTrace();
			log.error("添加索引出错：{}", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	//添加记录
	
	//获取记录
	//更新记录
	//删除记录
	//搜索
	
		
	
}

