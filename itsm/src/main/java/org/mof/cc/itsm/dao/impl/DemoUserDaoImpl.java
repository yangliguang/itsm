package org.mof.cc.itsm.dao.impl;

import java.util.List;

import org.mof.cc.itsm.dao.DemoUserDao;
import org.mof.cc.itsm.entity.DemoUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DemoUserDaoImpl implements DemoUserDao {

	private static final Logger log = LoggerFactory.getLogger(DemoUserDaoImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	/*
	 * JdbcTemplate.queryForList不支持自定义的泛型T queryForObject同样，只支持Integer.class
	 * String.class 这种单数据类型的
	 * 
	 */
	@Override
	public List<DemoUser> findAll() {
		String sql = "select id,username,fullname,password,create_time,modify_time from t_user";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<DemoUser>(DemoUser.class));
	}

	@Override
	public DemoUser findbyUsername(DemoUser u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(DemoUser u) {
		log.info("===插入用户开始===");
		log.info(u.toString());
		String sql = "insert into t_user(username, fullname, password, isused, create_time, modify_time) values(?,?,?,1,now(),now())";
		int res = jdbcTemplate.update(sql, u.getUsername(), u.getFullname(), u.getPassword());
		if (res == 1)
			log.info("===插入用户成功！===");
		else
			log.warn("===插入用户失败！===");
		return res;
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
