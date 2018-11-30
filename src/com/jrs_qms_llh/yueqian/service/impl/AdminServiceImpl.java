package com.jrs_qms_llh.yueqian.service.impl;

import com.jrs_qms_llh.yueqian.dao.IAdminDao;
import com.jrs_qms_llh.yueqian.dao.impl.AdminDaoImpl;
import com.jrs_qms_llh.yueqian.domain.Admin;
import com.jrs_qms_llh.yueqian.service.IAdminService;

/**
 * @author 黄浩文
 */
public class AdminServiceImpl implements IAdminService {
    private IAdminDao adminDao = new AdminDaoImpl();

    @Override
    public boolean Login(Admin admin) {
        return adminDao.findAdminByUsernameAndPassword(admin) == null ? false : true;
    }
}
