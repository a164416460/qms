package com.jrs_qms_llh.yueqian.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.jrs_qms_llh.commons.utils.JdbcUtil;
import com.jrs_qms_llh.commons.utils.Page;
import com.jrs_qms_llh.yueqian.dao.IBusinessWindowDao;
import com.jrs_qms_llh.yueqian.domain.BusinessWindow;
/**
 * BusinessWindowDao的实现类，与数据库直接操作
 * @author 丨Madman_
 *
 */
public class BusinessWindowDaoImpl implements IBusinessWindowDao{

	/**
	 * 方法功能说明：
	 * 			查询全部t_business_window表中的全部信息
	 * @return :成功则返回包含该表信息的BusinessWindow实体类集合
	 * @author 丨Madman_
	 * 
	 */
	@Override
	public List<BusinessWindow> listAll() {
		String sql = "SELECT * FROM t_business_window";
		return JdbcUtil.executeQuery(sql, BusinessWindow.class);
	}
	/**
	 * 方法功能说明：
	 * 			通过id查找t_business_window表中的一行信息
	 * @return :成功则返回包含该表信息的BusinessWindow实体类
	 * @author 丨Madman_
	 */
	@Override
	public BusinessWindow getById(int id) {
		String sql = "SELECT * FROM t_business_window WHERE bw_no = ?";
		List<BusinessWindow> list = JdbcUtil.executeQuery(sql, BusinessWindow.class,id);
		if(list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
		
	}
	/**
	 * 方法功能说明：
	 * 			修改传入的BusinessWindow的信息
	 * @return :成功则返回受影响行数
	 * @author 丨Madman_
	 */
	@Override
	public int update(BusinessWindow t) {
		String sql = "UPDATE t_business_window SET bw_no=?,bw_type_code=?,bw_type_name=? WHERE bw_id = ?";
		Object[] object ={t.getBw_no(),t.getBw_type_code(),t.getBw_type_name(),t.getBw_id()};
		return JdbcUtil.executeUpdate(sql, object);
	}
	/**方法功能说明：
	 *  			通过id删除指定的BusinessWindow的信息
	 * @return :成功则返回受影响行数
	 * @author 丨Madman_
	 */
	@Override
	public int deleteById(int id) {
		String sql = "delete  from t_business_window where bw_no=?";
		return JdbcUtil.executeUpdate(sql, id);
	}
	/**
	 *  方法功能说明：
	 *  			添加一个传进的BusinessWindow
	 * @return :成功则返回受影响行数
	 * @author 丨Madman_
	 */
	@Override
	public int save(BusinessWindow t) {
		String sql = "INSERT INTO t_business_window (bw_no,bw_type_code,bw_type_name) VALUES (?,?,?)";
		Object[] object ={t.getBw_no(),t.getBw_type_code(),t.getBw_type_name()};
		return JdbcUtil.executeUpdate(sql, object);
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
		int count = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(1) FROM t_business_window WHERE bw_type_code LIKE ? OR  bw_type_name LIKE ?";
		try {
			conn = JdbcUtil.getConn();
			ps = conn.prepareStatement(sql);
			ps.setObject(1, "%" + condition + "%");
			ps.setObject(2, "%" + condition + "%");
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeConn(rs, ps, conn);
		}
		return count;
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
		String sql = "SELECT * FROM t_business_window WHERE bw_no LIKE ? OR bw_type_code LIKE ? OR  bw_type_name LIKE ? LIMIT ?,?";
		Object[] object = {"%" +condition+"%" ,"%" +condition+"%" ,"%" +condition+"%" ,(currentPage - 1) * pageSize,pageSize}; 
		return JdbcUtil.executeQuery(sql, BusinessWindow.class, object);
	}
	/**方法功能说明：
	 * 			获得页面的全部信息
	 * @return :成功则返回全部详细记录信息，否则返回长度为0的集合
	 * @author 丨Madman_
	 */
	@Override
	public Page<BusinessWindow> getPage(String condition, int currentPage, int pageSize) {
		int count = this.getCount(condition);
		List<BusinessWindow> lists = this.getOnePageInfo(condition, currentPage, pageSize);
		return new Page<BusinessWindow>(currentPage, count, pageSize, lists);
	}

	
}
