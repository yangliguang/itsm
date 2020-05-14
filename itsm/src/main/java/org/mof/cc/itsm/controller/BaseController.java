package org.mof.cc.itsm.controller;

import java.util.List;

import org.mof.cc.itsm.page.PageDomain;
import org.mof.cc.itsm.page.TableDataInfo;
import org.mof.cc.itsm.page.TableSupport;
import org.mof.cc.itsm.util.SqlUtil;
import org.mof.cc.itsm.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/**
 * <p>
 * web层通用处理
 * 1、分页
 * 2、权限校验（TODO）
 * 3、数据绑定（TODO）
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月14日 上午11:04:34
 */
public class BaseController {

	protected final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	/**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 设置请求排序数据
     */
    protected void startOrderBy()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtils.isNotEmpty(pageDomain.getOrderBy()))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.orderBy(orderBy);
        }
    }
    
    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }
}
