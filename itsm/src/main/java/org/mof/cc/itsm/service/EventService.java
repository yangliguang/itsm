package org.mof.cc.itsm.service;

import java.util.List;

import org.mof.cc.itsm.entity.Event;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yangliguang
 * @since 2020-04-26
 */
public interface EventService extends IService<Event> {

	boolean insertEvent(Event event);

	boolean insertEvents(List<Event> event);

	List<Event> findAll();
	
	/**
	 * 分页查询(自定义分页插件)
	 */
	List<Event> findWithMyPage(Event event);
	
	/**
	 * 分页查询（使用mybatis-plus自带分页插件）
	 */
	List<Event> findWithDefaultPage(IPage<Event> page, Event event);
}
