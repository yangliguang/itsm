package org.mof.cc.itsm.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Entity基类
 * 1、分页
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月14日 下午2:21:41
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -8406790357885764524L;
	
	/** 请求参数 */
    private Map<String, Object> params;
    
    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }
}
