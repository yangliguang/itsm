package org.mof.cc.itsm.service.impl;

import java.util.List;

import org.mof.cc.itsm.entity.Event;
import org.mof.cc.itsm.mapper.EventMapper;
import org.mof.cc.itsm.service.EventService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
