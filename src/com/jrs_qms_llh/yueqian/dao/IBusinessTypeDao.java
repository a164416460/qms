package com.jrs_qms_llh.yueqian.dao;

import com.jrs_qms_llh.commons.dao.BaseDao;
import com.jrs_qms_llh.commons.utils.Page;
import com.jrs_qms_llh.yueqian.domain.BusinessType;

import java.util.List;

public interface IBusinessTypeDao extends BaseDao<BusinessType> {

    /**
     * 获得按某个条件查询的总记录数
     *
     * @param cond 模糊查询的条件
     */
    int getCount(String cond);

    /**
     * 获得一页的详细记录信息
     *
     * @param cond        模糊查询的条件
     * @param currentPage 当前页面
     * @param PageSize    一页的大小
     * @return
     */
    List<BusinessType> getOnePageInfo(String cond, int currentPage, int pageSize);

    /**
     * 获得一页
     */
    Page<BusinessType> getOnePage(String cond, int currentPage, int pageSize);
}
