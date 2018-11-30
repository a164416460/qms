package com.jrs_qms_llh.yueqian.dao.impl;

import com.jrs_qms_llh.commons.utils.JdbcUtil;
import com.jrs_qms_llh.commons.utils.Page;
import com.jrs_qms_llh.yueqian.dao.ICallerDao;
import com.jrs_qms_llh.yueqian.domain.Caller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class CallerDaoImpl implements ICallerDao {
    @Override
    public int getCount(String cond) {
        int count = 0;
        Connection conn = JdbcUtil.getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select count(1) from t_caller where C_NAME like concat('%',?,'%') " +
                    " or C_WORKNO like concat('%',?,'%') or C_PASSWORD like concat('%',?,'%') " +
                    " or C_LAST_LOGIN_IP like concat('%',?,'%') or C_REMARK like concat('%',?,'%') or C_SEX like concat('%',?,'%') ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, cond);
            pstmt.setObject(2, cond);
            pstmt.setObject(3, cond);
            pstmt.setObject(4, cond);
            pstmt.setObject(5, cond);
            pstmt.setObject(6, cond);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeConn(rs, pstmt, conn);
        }
        return count;
    }

    @Override
    public List<Caller> getOnePageInfo(String cond, int currentPage, int pageSize) {
        String sql = "select * from t_caller where C_NAME like concat('%',?,'%') " +
                " or C_WORKNO like concat('%',?,'%') or C_PASSWORD like concat('%',?,'%') " +
                " or C_LAST_LOGIN_IP like concat('%',?,'%') or C_REMARK like concat('%',?,'%') or C_SEX like concat('%',?,'%') limit ?,?";
        Object[] params = {
                cond, cond, cond, cond, cond, cond, (currentPage - 1) * pageSize, pageSize
        };
        return JdbcUtil.executeQuery(sql, Caller.class, params);
    }

    @Override
    public Page<Caller> getOnePage(String cond, int currentPage, int PageSize) {
        int count = this.getCount(cond);
        List<Caller> onePageInfo = this.getOnePageInfo(cond, currentPage, PageSize);
        //public Page(int currentPage, int count, int pageSize, List<T> list)
        Page<Caller> onePage = new Page<>(currentPage, count, PageSize, onePageInfo);
        return onePage;
    }

    @Override
    public List<Caller> listAll() {
        String sql = "select * from t_caller";
        return JdbcUtil.executeQuery(sql, Caller.class);
    }

    @Override
    public Caller getById(int id) {
        String sql = "select * from t_caller where c_id= ?";
        List<Caller> callers = JdbcUtil.executeQuery(sql, Caller.class, id);
        return callers.size() > 0 ? callers.get(0) : null;
    }

    @Override
    public int update(Caller caller) {
        String sql = "update t_caller set C_WORKNO=?,C_PASSWORD=?,C_NAME=?,C_SEX=?,C_HIREDATE=?," +
                " C_BIRTH=?,C_REMARK=?,C_LAST_LOGIN_TIME=?,C_LAST_LOGIN_IP=? where C_ID=?";
        Object[] params = {
                caller.getC_workno(), caller.getC_password(), caller.getC_name(),
                caller.getC_sex(), caller.getC_hiredate(), caller.getC_birth(),
                caller.getC_remark(), caller.getC_last_login_time(), caller.getC_last_login_ip()
                , caller.getC_id()
        };
        return JdbcUtil.executeUpdate(sql, params);
    }

    @Override
    public int deleteById(int id) {
        String sql = "delete from t_caller where c_id=?";
        return JdbcUtil.executeUpdate(sql, id);
    }

    @Override
    public int save(Caller caller) {
        String sql = "insert into t_caller (c_workno,c_password,c_name,c_sex,c_hiredate,c_birth,c_remark,c_last_login_time,c_last_login_ip) " +
                " values(?,?,?,?,?,?,?,?,?)";
        Object[] params = {
                caller.getC_workno(), caller.getC_password(), caller.getC_name(),
                caller.getC_sex(), caller.getC_hiredate(), caller.getC_birth(),
                caller.getC_remark(), caller.getC_last_login_time(), caller.getC_last_login_ip()
        };
        return JdbcUtil.executeUpdate(sql, params);
    }
}
