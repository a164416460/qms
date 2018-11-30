package com.jrs_qms_llh.yueqian.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jrs_qms_llh.commons.utils.JdbcUtil;
import com.jrs_qms_llh.commons.utils.Page;
import com.jrs_qms_llh.yueqian.dao.IBusinessTypeDao;
import com.jrs_qms_llh.yueqian.domain.BusinessType;


public class BusinessTypeDaoImpl implements IBusinessTypeDao {
	/**
	 * 查询所有业务类型信息
	 *  @author lihongjin
	 */
	@Override
	public List<BusinessType> listAll() {
		List<BusinessType> bts=new ArrayList<BusinessType>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection conn=JdbcUtil.getConn();
		//SELECT * FROM t_business_type WHERE BT_ID=1
		String sql=" SELECT * FROM t_business_type WHERE BT_ID";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while (rs.next()) {
				BusinessType bt=new BusinessType();
				bt.setBtId(rs.getInt("BT_ID"));
				bt.setBtCode(rs.getString("BT_CODE"));
				bt.setBtName(rs.getString("BT_NAME"));
				bt.setBtLimitCount(rs.getInt("BT_LIMIT_COUNT"));
				bt.setBtDesc(rs.getString("BT_DESC"));
				bts.add(bt);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.closeConn(rs, ps, conn);
		}
		return bts;
	}

	
	/**
	 * 根据ID得到业务类型信息
	 *  @author lihongjin
	 */
	@Override
	public BusinessType getById(int id) {
		BusinessType t=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection conn=JdbcUtil.getConn();
		//SELECT * FROM t_business_type WHERE BT_ID=1
		String sql="SELECT * FROM t_business_type WHERE BT_ID=?";
	
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				t=new BusinessType();
				t.setBtId(rs.getInt("BT_ID"));
				t.setBtCode(rs.getString("BT_CODE"));
				t.setBtName(rs.getString("BT_NAME"));
				t.setBtLimitCount(rs.getInt("BT_LIMIT_COUNT"));
				t.setBtDesc(rs.getString("BT_DESC"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			JdbcUtil.closeConn(rs, ps, conn);
		}
		return t;
	
	}
	
	
	/**
	 * 修改业务类型
	 *  @author lihongjin
	 */
	@Override
	public int update(BusinessType t) {
		//UPDATE  t_business_type SET BT_CODE='A', BT_NAME='存钱', BT_LIMIT_COUNT='100' ,BT_DESC='' WHERE BT_ID= 1
		String sql="UPDATE  t_business_type SET BT_CODE=?, BT_NAME=?, BT_LIMIT_COUNT=?,BT_DESC=? WHERE BT_ID=?";
		Object[] params={t.getBtCode(),t.getBtName(),t.getBtLimitCount(),t.getBtDesc(),t.getBtId()};
		int rows=JdbcUtil.executeUpdate(sql, params);
		return rows;
	}
	
	
	/**
	 * 根据Id删除业务类型
	 *  @author lihongjin
	 */
	@Override
	public int deleteById(int id) {
		//DELETE  FROM t_business_type WHERE BT_ID=2
		String sql="DELETE FROM t_business_type WHERE BT_ID=?";
		Object[] params={id};
		int rows=JdbcUtil.executeUpdate(sql, params);
		return rows;
		
	}
	
	
	/**
	 * 添加业务类型
	 *  @author lihongjin
	 */
	@Override
	public int save(BusinessType t) {
		
		//INSERT INTO t_business_type VALUES (4,'B','挂失',50,'无')
		String sql="INSERT INTO t_business_type VALUES (?,?,?,?,?)";
		Object[] params={t.getBtId(),t.getBtCode(),t.getBtName(),t.getBtLimitCount(),t.getBtDesc()};
		int rows=JdbcUtil.executeUpdate(sql, params);
	    return rows;
	}
	
	
	/**
	 * 方法功能说明：根据条件得到分页的总记录数
	 * @param cond 条件
	 * @return 得到相应条件的总记录数，如果查询不到数据，返回-1
	 * @author lihongjin
	 */
	@Override
	public int getCount(String cond) {
		int count =-1;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			 conn=JdbcUtil.getConn();
			 //SELECT COUNT(1) AS cou FROM t_business_type WHERE BT_CODE LIKE '%a%' OR BT_NAME LIKE '%a%'
			String countSql="SELECT COUNT(1) AS cou FROM t_business_type WHERE BT_CODE LIKE ? OR BT_NAME LIKE ?";
			 ps=conn.prepareStatement(countSql);
			ps.setObject(1, "%"+cond+"%");
			ps.setObject(2, "%"+cond+"%");
			 rs=ps.executeQuery();
			if (rs.next()) {
				count =rs.getInt("cou");
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			JdbcUtil.closeConn(rs, ps, conn);
		}
		return count;
	}

	
	
	
	
	/**
	 * 方法功能说明：得到一页中的详细记录信息
	 * @param cond 条件
	 * @param currentPage当前页码
	 * @param pageSize 每页大小
	 * @return 查询成功返回一页中的详细记录信息，否则返回一个长度为0的集合（xxx.size==0）
	 */
	@Override
	public List<BusinessType> getOnePageInfo(String cond, int currentPage, int pageSize) {
		List<BusinessType> bts=new ArrayList<BusinessType>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			 conn=JdbcUtil.getConn();
			// SELECT  BT_ID,BT_CODE,BT_NAME,BT_LIMIT_COUNT,BT_DESC FROMt_business_type WHERE BT_CODE LIKE '%a%' OR BT_NAME LIKE '%a%' LIMIT 0,1
			
			String pageSql="SELECT  BT_ID,BT_CODE,BT_NAME,BT_LIMIT_COUNT,BT_DESC FROM t_business_type WHERE BT_CODE LIKE ? OR BT_NAME LIKE ? LIMIT ?,?";
			 ps=conn.prepareStatement(pageSql);
			ps.setObject(1, "%"+cond+"%");
			ps.setObject(2, "%"+cond+"%");
			ps.setObject(3, (currentPage-1)*pageSize);
			ps.setObject(4, pageSize);
			rs=ps.executeQuery();
			 while (rs.next()) {
				BusinessType bt=new BusinessType();
				bt.setBtId(rs.getInt("BT_ID"));
				bt.setBtCode(rs.getString("BT_CODE"));
				bt.setBtName(rs.getString("BT_NAME"));
				bt.setBtLimitCount(rs.getInt("BT_LIMIT_COUNT"));
				bt.setBtDesc(rs.getString("BT_DESC"));
				bts.add(bt);
				
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			JdbcUtil.closeConn(rs, ps, conn);
		}
		return bts;
		
	}
		


	
	/**
	 * 方法功能说明：得到一页中的全部信息
	 * @param cond 条件
	 * @param currentPage 当前页码
	 * @param pageSize 每页大小
	 * @return 返回一页中的全部信息
	 *  @author lihongjin
	 */
	
	@Override
	public Page<BusinessType> getOnePage(String cond, int currentPage, int pageSize) {
		int count=this.getCount(cond);
		List<BusinessType> lists=this.getOnePageInfo(cond, currentPage, pageSize);
		return new Page<BusinessType>(currentPage, count, pageSize, lists);
	}
}
