package org.mof.cc.itsm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import org.mof.cc.itsm.dao.impl.DemoUserDaoImpl;
import org.mof.cc.itsm.entity.Event;
import org.mof.cc.itsm.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * Spirng boot集成logback
 * </p>
 *
 * @author yangliguang
 * @since 2020-04-26
 */
@Controller
@RequestMapping("/event")
public class EventController {
	private static final Logger log = LoggerFactory.getLogger(EventController.class);

	@Autowired
	private EventService eventService;

	// 增一条
	@PostMapping(value = "/add")
	@ResponseBody
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
	@PostMapping(value = "/addMany")
	@ResponseBody
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
	@GetMapping(value = "/list")
	@ResponseBody
	public Object getUserByName() {
		log.info("处理查询所有事件请求");
		return eventService.findAll();
	}
}
