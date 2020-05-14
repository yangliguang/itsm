package org.mof.cc.itsm.service.impl;

import java.util.List;

import org.mof.cc.itsm.dao.DemoUserDao;
import org.mof.cc.itsm.entity.DemoUser;
import org.mof.cc.itsm.entity.User;
import org.mof.cc.itsm.mapper.UserMapper;
import org.mof.cc.itsm.service.DemoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@ComponentScan(basePackages = "org.mof.cc.itsm")
public class DemoUserServiceImpl implements DemoUserService {
	@Autowired
	private DemoUserDao userDao;

	@Override
	public List<DemoUser> findAll() {
		// TODO Auto-generated method stub
		/*
		 * Page<User> page = PageHelper.startPage(1, 10); userDao.findAll();
		 * page.getTotal(); page.size();
		 */
		return userDao.findAll();
	}

	@Override
	public DemoUser findbyUsername(DemoUser u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(DemoUser u) {
		return userDao.insert(u);
	}

	@Override
	public int delete(DemoUser u) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(DemoUser u) {
		// TODO Auto-generated method stub
		return 0;
	}


}
