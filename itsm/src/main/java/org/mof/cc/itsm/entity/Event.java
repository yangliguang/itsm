package org.mof.cc.itsm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;

import org.mof.cc.itsm.domain.ModelWithPage;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangliguang
 * @since 2020-04-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_event")
//public class Event extends ModelWithPage<Event> {
public class Event {

	private static final long serialVersionUID = 0;

	/**
	 * ID，自增主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 事件名称
	 */
	private String event;

	/**
	 * 客户名称
	 */
	private String callerName;

	/**
	 * 客户机构
	 */
	private String callerDepartment;

	/**
	 * 客户电话
	 */
	private String callerPhone;

	/**
	 * 客户期望回复方式
	 */
	private String callerContact;

	/**
	 * 接线员ID
	 */
	private Integer operatorId;

	/**
	 * 接线员电话
	 */
	private String operatorPhone;

	/**
	 * 接线员名称
	 */
	private String operatorName;

	/**
	 * 来电时间
	 */
	private Timestamp eventTime;

	/**
	 * 事件处置过程
	 */
	private String eventProcess;

	/**
	 * 事件状态，0处置中，1结束，2联系不上客户
	 */
	private Integer eventState;

	/**
	 * 完成时间
	 */
	private Timestamp finishTime;

	/**
	 * 客户评价，0~10分
	 */
	private Integer customerEvaluation;

	/**
	 * 评价时间
	 */
	private Timestamp evaluateTime;

	/**
	 * 扩展字段1
	 */
	private String bak1;

	/**
	 * 扩展字段2
	 */
	private String bak2;

	/**
	 * 扩展字段3
	 */
	private String bak3;


}
