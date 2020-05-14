package org.mof.cc.itsm.service.impl;

import java.util.List;

import org.mof.cc.itsm.entity.Event;
import org.mof.cc.itsm.mapper.EventMapper;
import org.mof.cc.itsm.service.EventService;
import org.mof.cc.itsm.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangliguang
 * @since 2020-04-26
 */
@Service
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements EventService {

	@Autowired
	EventMapper eventMapper;
	
	// 增
	@Override
	public boolean insertEvent(Event event) {
		return super.save(event);
	}

	// 批量增加
	@Override
	public boolean insertEvents(List<Event> events) {
		return super.saveBatch(events);
	}

	@Override
	public List<Event> findAll() {
		return list();
	}

	/**
	 * 根据分页条件查询事件列表(自定义分页插件实现)
	 */
	
	/**
	 * 分页查询（通过自定义分页插件实现）
	 * 无法实现，Event必须继承BaseEntity
	 * 当前已继承Model<T>
	 * 可惜Java不支持多继承
	 */
	@Override
	public List<Event> findWithMyPage(Event event) {
		return eventMapper.selectList(new QueryWrapper<Event>());
	}
	
	/**
	 * 分页查询（mybatis-plus分页插件实现）
	 */
	
	@Override
	public List<Event> findWithDefaultPage(IPage<Event> page, Event event) {
		QueryWrapper<Event> queryWrapper = new QueryWrapper<>();
		queryWrapper.like("event", event.getEvent()).eq(StringUtils.toUnderScoreCase("callerName"), event.getCallerName());
		IPage<Event> iPage = eventMapper.selectPage(page, queryWrapper);
		System.out.println("总页数:" + iPage.getPages());
        System.out.println("总记录数:" + iPage.getTotal());
        List<Event> events = iPage.getRecords();
		return events;
	}
	

}
