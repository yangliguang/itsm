package org.mof.cc.itsm.service;

import java.util.List;

import org.mof.cc.itsm.entity.User;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yangliguang
 * @since 2020-04-24
 */
public interface UserService extends IService<User> {
	int insertUser(User user);

	int insertUsers(List<User> users);

	int updateUser(User user);

	int deleteByUsername(User user);

	User findUserById(int id);

	List<User> findUsersByUsername(String username);

	IPage getUserPage(Page page, User user);
}
