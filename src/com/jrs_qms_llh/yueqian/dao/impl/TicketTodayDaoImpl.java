package com.jrs_qms_llh.yueqian.dao.impl;

import com.jrs_qms_llh.commons.utils.JdbcUtil;
import com.jrs_qms_llh.commons.utils.Page;
import com.jrs_qms_llh.yueqian.dao.ITicketTodayDao;
import com.jrs_qms_llh.yueqian.domain.TicketToday;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 黄浩文
 */
public class TicketTodayDaoImpl implements ITicketTodayDao {

    /**
     * 列出所有 TicketToday
     *
     * @return
     */
    @Override
    public List<TicketToday> listAll() {
        String sql = "select * from t_ticket";
        return JdbcUtil.executeQuery(sql, TicketToday.class);
    }

    /**
     * 通过id 查找TicketToday
     *
     * @param id TicketToday 的id
     * @return 返回一个TicketToday对象，没有查询到返回null
     */
    @Override
    public TicketToday getById(int id) {
        String sql = "select * from t_ticket where ticket_id=?";
        List<TicketToday> ticketTodays = JdbcUtil.executeQuery(sql, TicketToday.class, id);
        return ticketTodays.size() > 0 ? ticketTodays.get(0) : null;
    }

    /**
     * 更新操作（通过id修改）
     *
     * @param ticketToday
     * @return
     */
    @Override
    public int update(TicketToday ticketToday) {
        String sql = "update t_ticket set TICKET_NO=?,TICKET_BUSINESS_CODE=?,TICKET_BUSINESS_NAME=?,TICKET_TAKE_TIME=?,TICKET_TAKE_IP=?,TICKET_WAIT_COUNT=?,TICKET_CALL_TIME=?,TICKET_CALL_COUNT=?,TICKET_CALL_IP=?,TICKET_CALL_WINDOW=?,TICKET_CALLER_WORKNO=?,TICKET_IS_SUCCESS=?,TICKET_DESC=? where TICKET_ID=?;";
        Object[] params = {
                ticketToday.getTicket_no(), ticketToday.getTicket_business_code(),
                ticketToday.getTicket_business_name(), ticketToday.getTicket_take_time(),
                ticketToday.getTicket_take_ip(), ticketToday.getTicket_wait_count(),
                ticketToday.getTicket_call_time(), ticketToday.getTicket_call_count(),
                ticketToday.getTicket_call_ip(), ticketToday.getTicket_call_window(),
                ticketToday.getTicket_caller_workno(), ticketToday.getTicket_is_success(),
                ticketToday.getTicket_desc(), ticketToday.getTicket_id()
        };
        return JdbcUtil.executeUpdate(sql, params);
    }

    @Override
    public int deleteById(int id) {
        String sql = "delete from t_ticket where TICKET_ID = ?";
        return JdbcUtil.executeUpdate(sql, id);
    }

    /**
     * 向TicketToday表中插入数据
     *
     * @param ticketToday
     * @return
     * @author 黄浩文
     */
    @Override
    public int save(TicketToday ticketToday) {
        String sql = "insert into t_ticket (TICKET_NO, TICKET_BUSINESS_CODE, TICKET_BUSINESS_NAME, TICKET_TAKE_TIME, TICKET_TAKE_IP, TICKET_WAIT_COUNT, TICKET_CALL_TIME, TICKET_CALL_COUNT, TICKET_CALL_IP, TICKET_CALL_WINDOW, TICKET_CALLER_WORKNO, TICKET_IS_SUCCESS, TICKET_DESC) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {
                ticketToday.getTicket_no(), ticketToday.getTicket_business_code(),
                ticketToday.getTicket_business_name(), ticketToday.getTicket_take_time(),
                ticketToday.getTicket_take_ip(), ticketToday.getTicket_wait_count(),
                ticketToday.getTicket_call_time(), ticketToday.getTicket_call_count(),
                ticketToday.getTicket_call_ip(), ticketToday.getTicket_call_window(),
                ticketToday.getTicket_caller_workno(), ticketToday.getTicket_is_success(),
                ticketToday.getTicket_desc()
        };
        return JdbcUtil.executeUpdate(sql, params);
    }

    /**
     * 找到某个时间段的总记录数
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return 返回某个时间段的总记录数
     * @author 黄浩文
     */
    @Override
    public int getCountByDate(Date beginDate, Date endDate) {
        int count = 0;
        String sql = "select count(1) from t_ticket where TICKET_CALL_TIME between ? and ?";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, beginDate);
            pstmt.setObject(2, endDate);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeConn(rs, pstmt, conn);
        }
        return count;
    }

    /**
     * 获得一页的信息
     *
     * @param beginDate   开始时间
     * @param endDate     结束时间
     * @param currentPage 当前页码
     * @param pageSize    每页显示多少条
     * @return 一页的详细信息
     * @author 黄浩文
     */
    @Override
    public List<TicketToday> getOnePageInfoByDate(Date beginDate, Date endDate, int currentPage, int pageSize) {
        String sql = "select * from t_ticket where TICKET_CALL_TIME between ? and ? limit ?,?";
        Object[] params = {
                beginDate, endDate, (currentPage - 1) * pageSize, pageSize
        };
        return JdbcUtil.executeQuery(sql, TicketToday.class, params);
    }

    /**
     * 得到按时间段查询的一页
     *
     * @param beginDate   开始时间
     * @param endDate     结束时间
     * @param currentPage 当前页
     * @param pageSize    一页多少条记录
     * @return 返回一页
     */
    @Override
    public Page<TicketToday> getOnePage(Date beginDate, Date endDate, int currentPage, int pageSize) {
        //获得总记录数
        int count = getCountByDate(beginDate, endDate);
        //获得一页详细记录
        List<TicketToday> list = getOnePageInfoByDate(beginDate, endDate, currentPage, pageSize);
        //int currentPage, int count, int pageSize, List<T> list
        Page<TicketToday> page = new Page<TicketToday>(currentPage, count, pageSize, list);
        return page;
    }

    /**
     * 分组查询某个时间段的办理总数，返回一个Map集合
     *
     * @param start
     * @param end
     */
    @Override
    public Map<String, Integer> getBtNameAndCountByDate(Date start, Date end) {
        Map<String, Integer> map = new HashMap<>();
        /*
         *
         *
         * select
         *      count(TICKET_BUSINESS_NAME), TICKET_BUSINESS_NAME
         *                  from t_ticket
         *  where TICKET_CALL_TIME between '2018-1-1' and '2018-8-31'
         *     group by TICKET_BUSINESS_NAME;
         * */
        String sql = "select TICKET_BUSINESS_NAME, count(TICKET_BUSINESS_NAME) from t_ticket where TICKET_CALL_TIME between ? and ? group by TICKET_BUSINESS_NAME";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, start);
            pstmt.setObject(2, end);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                map.put(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeConn(rs, pstmt, conn);
        }
        return map;
    }

    /**
     * 分组查询出某个时间到某个时间 按业务类型分组
     *
     * @param start
     * @param end
     */
    @Override
    public Map<String, Integer> getBtNameAndCountByWindow(Date start, Date end) {
        Map<String, Integer> map = new HashMap<>();
        String sql = "select TICKET_CALL_WINDOW, count(1) from t_ticket where TICKET_CALL_TIME between ? and ? group by TICKET_CALL_WINDOW";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, start);
            pstmt.setObject(2, end);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                map.put(String.valueOf(rs.getInt(1)), rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeConn(rs, pstmt, conn);
        }
        return map;
    }

}
