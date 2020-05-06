package org.mof.cc.itsm.dao;

import java.util.ArrayList;
import java.util.List;

import org.mof.cc.itsm.entity.DemoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SampleDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

//	@Bean
	public int insert() {
		String sql = "insert into t_user('username','fullname','password','isused','create_time','modify_time') values(?,?,?,1,now(),now())";
		DemoUser user = new DemoUser("yanglg", "杨立广", "12345678");
		return jdbcTemplate.update(sql, user);
	}

	/*
	 * public List<User> getForList() { String sql =
	 * "select id, username, fullname, create_time, modidy_time from t_user"; //
	 * List<User> users = new ArrayList<User>();
	 * 
	 * return jdbcTemplate.queryForList(sql); }
	 */

}
