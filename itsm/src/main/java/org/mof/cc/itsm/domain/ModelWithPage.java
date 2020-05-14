package org.mof.cc.itsm.domain;

import org.mof.cc.itsm.constant.Constants;
import org.mof.cc.itsm.util.StringUtils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 封装MP的Model
 * 装入分页信息
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月14日 下午5:31:04
 */

@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("rawtypes")
public class ModelWithPage<T extends ModelWithPage<?>> extends Model {

	private static final long serialVersionUID = -4361863258432618404L;

	/** 当前记录起始索引 */
	@TableField(exist=false)
    protected Integer pageNum = Constants.DEFAULT_PAGE_NUM;
    
    /** 每页显示记录数 */
	@TableField(exist=false)
    protected Integer pageSize = Constants.DEFAULT_PAGE_SIZE;
    
    /** 排序列 */
	@TableField(exist=false)
    protected String orderByColumn;
    
    /** 排序的方向 "desc" 或者 "asc". */
	@TableField(exist=false)
    protected String isAsc;
    
    public String getOrderBy()
    {
        if (StringUtils.isEmpty(orderByColumn))
        {
            return "";
        }
        return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
    }
	
}
