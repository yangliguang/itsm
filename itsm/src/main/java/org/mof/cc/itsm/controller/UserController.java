package org.mof.cc.itsm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import org.mof.cc.itsm.entity.User;
import org.mof.cc.itsm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * Spring boot集成mybatis-plus
 * </p>
 *
 * @author yangliguang
 * @since 2020-04-24
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	// 增一条
	@PostMapping(value = "/add")
	@ResponseBody
	public Object insert(@RequestBody User user) {
		return userService.insertUser(user);
	}

	// 增多条
	@PostMapping(value = "/addMany")
	@ResponseBody
	public Object insertUsers(@RequestBody List<User> users) {
		return userService.insertUsers(users);
	}

	// 改
	@PostMapping(value = "/update")
	@ResponseBody
	public Object update(@RequestBody User user) {
		return userService.updateUser(user);
	}

	// 删
	@PostMapping(value = "/deleteByUsername")
	@ResponseBody
	public Object delete(@RequestBody User user) {
		return userService.deleteByUsername(user);
	}

	// 查
	@GetMapping(value = "/listById")
	@ResponseBody
	public Object getUserByName(@RequestParam int id) {
		return userService.findUserById(id);
	}

	/*
	 * 查询多条
	 */
	@GetMapping(value = "/findUsersByUsername")
	@ResponseBody
	public Object getUserByName(@RequestParam String username) {
		return userService.findUsersByUsername(username);
	}
}
