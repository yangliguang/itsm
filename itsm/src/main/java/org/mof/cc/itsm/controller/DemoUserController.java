package org.mof.cc.itsm.controller;

import java.util.List;

import org.mof.cc.itsm.entity.DemoUser;
import org.mof.cc.itsm.service.DemoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * Spring boot 通过jdbc template集成druid
 * </p>
 *
 * @author yangliguang
 * @since 2020-04-23
 */

@Controller
@ComponentScan(basePackages = "org.mof.cc.itsm")
public class DemoUserController {
	@Autowired
	private DemoUserService userService;

	@PostMapping("/demouser/add")
	@ResponseBody
	public int add(@RequestBody DemoUser u) {
		return userService.add(u);
	}

	@GetMapping("/demouser/list")
	@ResponseBody
	public List<DemoUser> list() {
		return userService.findAll();
	}
	
}
