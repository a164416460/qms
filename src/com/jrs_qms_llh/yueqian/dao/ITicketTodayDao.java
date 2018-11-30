package com.jrs_qms_llh.yueqian.dao;

import com.jrs_qms_llh.commons.dao.BaseDao;
import com.jrs_qms_llh.commons.utils.Page;
import com.jrs_qms_llh.yueqian.domain.TicketToday;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ITicketTodayDao extends BaseDao<TicketToday> {
    /**
     * 得到按某个时间到某个时间查询的总记录数
     */
    int getCountByDate(Date beginDate, Date endDate);

    /**
     * 得到某个时间段的详细信息
     */
    List<TicketToday> getOnePageInfoByDate(Date beginDate, Date endDate, int currentPage, int pageSize);

    Page<TicketToday> getOnePage(Date beginDate, Date endDate, int currentPage, int pageSize);

    /**
     * 分组查询某个时间段的办理总数，返回一个Map集合
     */
    public Map<String, Integer> getBtNameAndCountByDate(Date start, Date end);

    /**
     * 分组查询出某个时间到某个时间 按业务类型分组
     */
    public Map<String, Integer> getBtNameAndCountByWindow(Date start, Date end);
}
