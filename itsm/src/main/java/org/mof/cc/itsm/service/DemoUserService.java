package org.mof.cc.itsm.service;

import java.util.List;

import org.mof.cc.itsm.entity.DemoUser;

public interface DemoUserService {

	public List<DemoUser> findAll();

	public DemoUser findbyUsername(DemoUser u);

	public int add(DemoUser u);

	public int delete(DemoUser u);

	public int update(DemoUser u);
}
