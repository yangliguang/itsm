package org.mof.cc.itsm.page;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * <p>
 * 表格分页数据对象
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月14日 下午12:05:58
 */
@Data
public class TableDataInfo implements Serializable {

	private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private long total;

    /** 列表数据 */
    private List<?> rows;

    /** 消息状态码 */
    private int code;

    /** 消息内容 */
    private int msg;
}
