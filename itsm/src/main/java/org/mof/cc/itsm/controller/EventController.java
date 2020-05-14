package org.mof.cc.itsm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mof.cc.itsm.domain.ModelWithPage2;
import org.mof.cc.itsm.entity.Event;
import org.mof.cc.itsm.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * Spirng boot集成logback
 * </p>
 *
 * @author yangliguang
 * @since 2020-04-26
 */
@RestController
@RequestMapping("/api/event")
@Api(value = "/event", tags = "事件记录模块")
public class EventController {
	private static final Logger log = LoggerFactory.getLogger(EventController.class);

	@Autowired
	private EventService eventService;

	// 增一条
	@PostMapping(value = "/event")
	@ApiOperation(value = "插入一条事件记录")
	public Object insert(@RequestBody Event event) {
		log.info("处理单个事件插入请求，待插入数据：{}", event);
		boolean res = eventService.insertEvent(event);
		if (res) {
			log.info("成功插入一条记录：{}", event);
			return "success";
		} else {
			log.error("插入事件失败，事件：{}", event);
			return "fail";
		}
	}

	// 增多条
	@PostMapping(value = "/events")
	@ApiOperation(value = "批量插入事件记录")
	public Object insertEvents(@RequestBody List<Event> events) {
		log.info("处理事件批量插入请求，待插入{}条数据：{}", events.size(), events);
		boolean res = eventService.insertEvents(events);
		if (res) {
			log.info("成功插入{}条事件", events.size());
			return "success";
		} else {
			log.error("插入事件失败，事件：{}", events);
			return "fail";
		}
	}

	// 查所有
	@GetMapping(value = "/events")
	@ApiOperation(value = "查询所有事件列表")
	public Object findAll() {
		log.info("处理查询所有事件请求");
		return eventService.findAll();
	}

	// 分页查询
//	@PostMapping(value = "/events/page1")
//	@ApiOperation(value = "分页查询所有事件列表") 
//	public Object findWithSelfPage(@RequestBody Event event) {
//		log.info("处理分页查询事件请求"); 
//		startPage(); 
//		List<Event> events = eventService.findWithMyPage(event); 
//		return getDataTable(events); 
//	}
	
	// 分页查询
//		@PostMapping(value = "/events/page2")		
//		@ApiOperation(value = "分页查询所有事件列表") 
//		public Object findWithDefaultPage(@RequestBody Event event) {
//			log.info("处理分页查询事件列表请求(MybatisPlus自带分页插件)"); 
//            IPage<Event> page = new Page<>(event.getPageNum(), event.getPageSize());
//			List<Event> events = eventService.findWithDefaultPage(page, event); 
//			return events;
//		}
		
		/**
		 * 分页查询，方式三
		 */
		@PostMapping(value = "/events/page3")		
		@ApiOperation(value = "分页查询所有事件列表") 
		public Object findWithPage(@RequestBody ModelWithPage2<Event> eventWithPage) {
			log.info("处理分页查询事件列表请求(MybatisPlus自带分页插件，自封装Model)"); 
            IPage<Event> page = new Page<>(eventWithPage.getPageNum(), eventWithPage.getPageSize());
			Event event = eventWithPage.getRecord();
            List<Event> events = eventService.findWithDefaultPage(page, event); 
			return events;
		}
}
