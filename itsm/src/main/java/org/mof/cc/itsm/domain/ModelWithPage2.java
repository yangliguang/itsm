package org.mof.cc.itsm.domain;

import java.io.Serializable;
import java.util.List;

import org.mof.cc.itsm.constant.Constants;

import lombok.Data;


/**
 * <p>
 * 封装Entity
 * 装入分页信息和一组记录
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月14日 下午6:31:52
 */
@Data
public class ModelWithPage2 <T> implements Serializable{

	private static final long serialVersionUID = 2056034794885154149L;
	
    private Integer pageNum = Constants.DEFAULT_PAGE_NUM;
    
    /** 每页显示记录数 */
    private Integer pageSize = Constants.DEFAULT_PAGE_SIZE;
    
    /** 排序列 */
    private String orderByColumn;
    
    /** 排序的方向 "desc" 或者 "asc". */
    private String isAsc;

	/** Entity记录  **/
    private T record;

}
