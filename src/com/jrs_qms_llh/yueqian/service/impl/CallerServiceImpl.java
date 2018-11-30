package com.jrs_qms_llh.yueqian.service.impl;

import com.jrs_qms_llh.commons.utils.Page;
import com.jrs_qms_llh.yueqian.dao.ICallerDao;
import com.jrs_qms_llh.yueqian.dao.impl.CallerDaoImpl;
import com.jrs_qms_llh.yueqian.domain.Caller;
import com.jrs_qms_llh.yueqian.service.ICallerService;

public class CallerServiceImpl implements ICallerService {
    private ICallerDao callerDao = new CallerDaoImpl();

    @Override
    public Page<Caller> getPage(String cond, int currentPage, int pageSize) {
        return callerDao.getOnePage(cond, currentPage, pageSize);
    }

    @Override
    public int addCaller(Caller caller) {
        return callerDao.save(caller);
    }

    @Override
    public int updateCaller(Caller caller) {
        return callerDao.update(caller);
    }

    @Override
    public int deleteSelectAll(int[] id) {
        int count = 0;
        for (int i : id) {
            count += callerDao.deleteById(i);
        }
        return count;
    }

    @Override
    public Caller findByICallerId(int id) {
        return callerDao.getById(id);
    }
}
