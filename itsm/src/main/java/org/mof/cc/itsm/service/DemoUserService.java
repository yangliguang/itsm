package org.mof.cc.itsm.service;

import java.util.List;

import org.mof.cc.itsm.entity.DemoUser;
import org.springframework.web.bind.annotation.RequestBody;

public interface DemoUserService {

	List<DemoUser> findAll();

	DemoUser findbyUsername(DemoUser u);

	int add(DemoUser u);

	int delete(DemoUser u);

	int update(DemoUser u);
	
}
