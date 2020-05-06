package org.mof.cc.itsm.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.mof.cc.itsm.entity.Question;
import org.mof.cc.itsm.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  RESTful API最佳实践
 *  实现CRUD基本功能
 * </p>
 *
 * @author yangliguang
 * @since 2020-04-29
 */
@RestController
@Api(value = "/question", tags = "问题模块")
@RequestMapping("/api/question")
public class QuestionController {
	
	private static final Logger log = LoggerFactory.getLogger(QuestionController.class);
	
	@Autowired
	private QuestionService questionService;
		
	//增单条
	@ApiOperation(value = "保存一条问题")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> saveQuestion(@RequestBody Question question){
		try {
			questionService.saveQuestion(question);
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
	 * 删除用户资源
	 * @param id
	 * @return 
	 */
	@ApiOperation(value = "删除一条问题")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteQuestion(@PathVariable("id") int id){
		System.out.println("删除ID：" + id);
		try {
			if(id == 0) {
				//请求参数有误
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			questionService.deleteQuestion(id);
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
	 * 更新问题
	 * @param question
	 * @Return
	 */
	@ApiOperation(value = "更新一条问题")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> updateQuestion(@RequestBody Question question){
		try {
			questionService.updateQuestion(question);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch(Exception e) {
			e.printStackTrace();
			log.error("更新出错：{}", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	
	//查单条
	/*
	 * 根据问题ID查询问题
	 * @param id
	 * @return Question
	 */
	@ApiOperation(value = "根据问题ID查询一条问题")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Question> getQuestionById(@PathVariable("id") int id) {
		try {
			Question question = questionService.getById(id);
			if(null == question){
				//资源不存在，响应404
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			//200
			return ResponseEntity.ok(question);
		} catch(Exception e) {
			e.printStackTrace();
			log.error("查询出错：{}", e);
		}
		//500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	//查所有
	
}

