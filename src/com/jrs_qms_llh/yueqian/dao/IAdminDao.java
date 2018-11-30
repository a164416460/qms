package com.jrs_qms_llh.yueqian.dao;

import com.jrs_qms_llh.commons.dao.BaseDao;
import com.jrs_qms_llh.yueqian.domain.Admin;

/**
 * @author 黄浩文
 */
public interface IAdminDao extends BaseDao<Admin> {
    Admin findAdminByUsernameAndPassword(Admin admin);
}
