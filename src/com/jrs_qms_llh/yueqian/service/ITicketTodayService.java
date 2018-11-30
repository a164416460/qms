package com.jrs_qms_llh.yueqian.service;

import com.jrs_qms_llh.commons.utils.Page;
import com.jrs_qms_llh.yueqian.domain.TicketToday;

import java.util.Date;
import java.util.Map;

public interface ITicketTodayService {
    Page<TicketToday> getPageByDate(Date beginDate, Date endDate, int currentPage, int pageSize);


    /**
     * 得到某个时间段的办理业务Map表
     * key:业务名称 value:办理数量
     *
     * @return
     */
    public Map<String, Integer> getBtNameAndCountByDate(Date start, Date end);

    public Map<String, Integer> getBtNameAndCountByWindow(Date start, Date end);
}
