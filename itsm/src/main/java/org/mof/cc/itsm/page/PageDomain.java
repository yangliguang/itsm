package org.mof.cc.itsm.page;


import org.mof.cc.itsm.util.StringUtils;


import lombok.Data;

/**
 * <p>
 * 分页数据
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月14日 上午11:14:18
 */
@Data
public class PageDomain {

	/** 当前记录起始索引 */
    private Integer pageNum;
    
    /** 每页显示记录数 */
    
    private Integer pageSize;
    
    /** 排序列 */
    private String orderByColumn;
    
    /** 排序的方向 "desc" 或者 "asc". */
    private String isAsc;
    
    public String getOrderBy()
    {
        if (StringUtils.isEmpty(orderByColumn))
        {
            return "";
        }
        return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
    }

	
}
