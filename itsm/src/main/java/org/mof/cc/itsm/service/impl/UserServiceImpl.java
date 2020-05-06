package org.mof.cc.itsm.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.mof.cc.itsm.entity.User;
import org.mof.cc.itsm.mapper.UserMapper;
import org.mof.cc.itsm.service.UserService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangliguang
 * @since 2020-04-24
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	// 增
	@Override
	public int insertUser(User user) {
		// 自动赋值
		user.setIsused(true);
		return baseMapper.insert(user);
	}

	// 批量增加
	@Override
	public int insertUsers(List<User> users) {
		int res = 0;
		for (User user : users) {
			user.setIsused(true);
			baseMapper.insert(user);
			res++;
		}
		return res;
	}

	// 改
	@Override
	public int updateUser(User user) {
		return baseMapper.updateById(user);
	}

	// 删
	@Override
	public int deleteByUsername(User user) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();

		return baseMapper.delete(queryWrapper.eq("username", user.getUsername()));

	}

	// 查
	@Override
	public User findUserById(int id) {
		return baseMapper.selectById(id);
	}

	/*
	 * 根据用户名查询多条
	 */
	@Override
	public List<User> findUsersByUsername(String username) {
		QueryWrapper<User> qw = new QueryWrapper<User>();
		return baseMapper.selectList(qw.eq("username", username));
	}

	@Override
	public IPage getUserPage(Page page, User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
