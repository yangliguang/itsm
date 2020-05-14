package org.mof.cc.itsm.page;

import org.mof.cc.itsm.constant.Constants;
import org.mof.cc.itsm.util.ServletUtils;

/**
 * <p>
 * 表格数据处理
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月14日 上午11:31:59
 */
public class TableSupport {

	/**
     * 封装分页对象
     */
    public static PageDomain getPageDomain()
    {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(Constants.PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest()
    {
        return getPageDomain();
    }
}
