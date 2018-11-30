package com.jrs_qms_llh.yueqian.service;

import com.jrs_qms_llh.commons.utils.Page;
import com.jrs_qms_llh.yueqian.domain.Caller;

public interface ICallerService {
    /**
     * 获得一页
     *
     * @param cond
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Caller> getPage(String cond, int currentPage, int pageSize);

    /**
     * 添加柜员
     *
     * @param caller
     * @return
     */
    int addCaller(Caller caller);

    /**
     * 跟新柜员
     *
     * @param caller
     * @return
     */
    int updateCaller(Caller caller);

    /**
     * 删除选中的所有id
     *
     * @param id
     * @return
     */
    int deleteSelectAll(int[] id);

    /**
     * 通过ID查询柜员
     *
     * @param id
     * @return
     */
    Caller findByICallerId(int id);
}

