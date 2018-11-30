package com.jrs_qms_llh.yueqian.service.impl;

import com.jrs_qms_llh.commons.utils.Page;
import com.jrs_qms_llh.yueqian.dao.ITicketTodayDao;
import com.jrs_qms_llh.yueqian.dao.impl.TicketTodayDaoImpl;
import com.jrs_qms_llh.yueqian.domain.TicketToday;
import com.jrs_qms_llh.yueqian.service.ITicketTodayService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TicketTodayServiceImpl implements ITicketTodayService {
    ITicketTodayDao ticketTodayDao = new TicketTodayDaoImpl();

    /**
     * 查询某个时间段到某个时间段的一页
     *
     * @param beginDate   开始时间
     * @param endDate     结束时间
     * @param currentPage 当前页
     * @param pageSize    一页多少条记录
     * @return
     * @author 黄浩文
     */
    @Override
    public Page<TicketToday> getPageByDate(Date beginDate, Date endDate, int currentPage, int pageSize) {
        return ticketTodayDao.getOnePage(beginDate, endDate, currentPage, pageSize);
    }

    /**
     * 得到某个时间段的办理业务Map表
     * key:业务名称 value:办理数量
     *
     * @return
     */
    @Override
    public Map<String, Integer> getBtNameAndCountByDate(Date start, Date end) {
        return ticketTodayDao.getBtNameAndCountByDate(start, end);
    }

    /**
     * 通过窗口号分组查询某个时间段的办理总数
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 一个Map集合 key：窗口好 value: 业务办理数量
     */
    @Override
    public Map<String, Integer> getBtNameAndCountByWindow(Date start, Date end) {
        return ticketTodayDao.getBtNameAndCountByWindow(start, end);
    }

}
