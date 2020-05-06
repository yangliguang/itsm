package org.mof.cc.itsm.entity;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
//@Data 注解是 Lombok 项目的注解,不用手动的去添加setter，getter
public class DemoUser {
	public DemoUser() {

	}

	public DemoUser(String username, String fullname, String password) {
		this.username = username;
		this.fullname = fullname;
		this.password = password;
	}

	private int id;
	private String username;
	private String fullname;
	private String password;
	private Timestamp createTime;
	private Timestamp modifyTime;

}
