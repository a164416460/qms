package com.jrs_qms_llh.yueqian.dao;

import java.util.List;

import com.jrs_qms_llh.commons.dao.BaseDao;
import com.jrs_qms_llh.commons.utils.Page;
import com.jrs_qms_llh.yueqian.domain.BusinessWindow;

public interface IBusinessWindowDao extends BaseDao<BusinessWindow> {
	/**
	 * 方法功能说明： 根据条件获得分页的总记录数
	 * 
	 * @param condition
	 *            条件
	 * @author 丨Madman_
	 * @return 得到相应条件的总记录数，查询不到则返回-1
	 * @since OA 1.0
	 */
	public int getCount(String condition);
	/**
	 * 方法功能说明： 得到一页中的详细记录信息
	 * @param condition
	 *            条件
	 * @param currentPage
	 *            当前页码
	 * @param pageSize
	 *            每页数
	 * @author 丨Madman_
	 * @return 成功返回一页中的详细记录信息，否则返回长度为0的集合
	 * @since OA 1.0
	 */
	public List<BusinessWindow> getOnePageInfo(String condition, int currentPage, int pageSize) ;
	/**方法功能说明：
	 * 			获得页面的全部信息
	 * @return :成功则返回全部详细记录信息，否则返回长度为0的集合
	 * @author 丨Madman_
	 */
	public Page<BusinessWindow> getPage(String condition, int currentPage, int pageSize);
}
