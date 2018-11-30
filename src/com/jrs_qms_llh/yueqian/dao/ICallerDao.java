package com.jrs_qms_llh.yueqian.dao;

import java.util.List;

import com.jrs_qms_llh.commons.dao.BaseDao;
import com.jrs_qms_llh.commons.utils.Page;
import com.jrs_qms_llh.yueqian.domain.Caller;

public interface ICallerDao extends BaseDao<Caller> {
    int getCount(String cond);

    List<Caller> getOnePageInfo(String cond, int currentPage, int pageSize);

    Page<Caller> getOnePage(String cond, int currentPage, int PageSize);
}
