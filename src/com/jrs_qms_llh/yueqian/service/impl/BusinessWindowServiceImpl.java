package com.jrs_qms_llh.yueqian.service.impl;

import java.util.List;

import com.jrs_qms_llh.commons.utils.Page;
import com.jrs_qms_llh.yueqian.dao.IBusinessWindowDao;
import com.jrs_qms_llh.yueqian.dao.impl.BusinessWindowDaoImpl;
import com.jrs_qms_llh.yueqian.domain.BusinessWindow;
import com.jrs_qms_llh.yueqian.service.IBusinessWindowService;

public class BusinessWindowServiceImpl implements IBusinessWindowService{
	
	IBusinessWindowDao businessWindowDao = new BusinessWindowDaoImpl();
	/**
	 * 方法功能说明：
	 * 			查询全部t_business_window表中的全部信息
	 * @return :成功则返回包含该表信息的BusinessWindow实体类集合
	 * @author 丨Madman_
	 * 
	 */
	@Override
	public List<BusinessWindow> listAll() {
		return businessWindowDao.listAll();
	}
	/**
	 * 方法功能说明：
	 * 			通过id查找t_business_window表中的一行信息
	 * @return :成功则返回包含该表信息的BusinessWindow实体类
	 * @author 丨Madman_
	 */
	@Override
	public BusinessWindow getById(int id) {
		return businessWindowDao.getById(id);
	}
	/**
	 * 方法功能说明：
	 * 			修改传入的BusinessWindow的信息
	 * @return :成功则返回受影响行数
	 * @author 丨Madman_
	 */
	@Override
	public int update(BusinessWindow t) {
		return businessWindowDao.update(t);
	}
	/**方法功能说明：
	 *  			通过id删除指定的BusinessWindow的信息
	 * @return :成功则返回受影响行数
	 * @author 丨Madman_
	 */
	@Override
	public int deleteById(int id) {
		return businessWindowDao.deleteById(id);
	}
	/**
	 *  方法功能说明：
	 *  			添加一个传进的BusinessWindow
	 * @return :成功则返回受影响行数
	 * @author 丨Madman_
	 */
	@Override
	public int save(BusinessWindow t) {
		return businessWindowDao.save(t);
	}
	/**
	 * 方法功能说明： 根据条件获得分页的总记录数
	 * 
	 * @param condition
	 *            条件
	 * @author 丨Madman_
	 * @return 得到相应条件的总记录数，查询不到则返回-1
	 * @since OA 1.0
	 */
	@Override
	public int getCount(String condition) {
		return businessWindowDao.getCount(condition);
	}
	
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
	@Override
	public List<BusinessWindow> getOnePageInfo(String condition, int currentPage, int pageSize) {
		return businessWindowDao.getOnePageInfo(condition, currentPage, pageSize);
	}
	
	/**方法功能说明：
	 * 			获得页面的全部信息
	 * @return :成功则返回全部详细记录信息，否则返回长度为0的集合
	 * @author 丨Madman_
	 */
	@Override
	public Page<BusinessWindow> getPage(String condition, int currentPage, int pageSize) {
		return businessWindowDao.getPage(condition, currentPage, pageSize);
	}

	/**
	 * 根据ids批量删除
	 * 
	 * @return
	 */
	public boolean deleteByIds(int... ids) {

		try {
			for (int i = 0; i < ids.length; i++) {
				businessWindowDao.deleteById(ids[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
