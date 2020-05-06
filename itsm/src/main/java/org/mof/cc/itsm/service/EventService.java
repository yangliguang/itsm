package org.mof.cc.itsm.service;

import java.util.List;

import org.mof.cc.itsm.entity.Event;
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
}
