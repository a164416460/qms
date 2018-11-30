package com.jrs_qms_llh.yueqian.dao.impl;

import com.jrs_qms_llh.commons.utils.JdbcUtil;
import com.jrs_qms_llh.yueqian.dao.IAdminDao;
import com.jrs_qms_llh.yueqian.domain.Admin;

import java.util.List;

public class AdminDaoImpl implements IAdminDao {

    @Override
    public List<Admin> listAll() {
        String sql = "select * from t_admin";
        return JdbcUtil.executeQuery(sql, Admin.class);
    }

    @Override
    public Admin getById(int id) {
        String sql = "select * from t_admin where adm_id = ?";
        List<Admin> admins = JdbcUtil.executeQuery(sql, Admin.class, id);
        return admins.size() > 0 ? admins.get(0) : null;
    }

    @Override
    public int update(Admin t) {
        String sql = "update t_admin set adm_username=?,adm_password=? where adm_id=?";
        Object[] params = {
                t.getAdm_username(), t.getAdm_password(), t.getAdm_id()
        };
        return JdbcUtil.executeUpdate(sql, params);
    }

    @Override
    public int deleteById(int id) {
        String sql = "delete from t_admin where adm_id=?";
        return JdbcUtil.executeUpdate(sql, id);
    }

    @Override
    public int save(Admin t) {
        String sql = "insert into t_admin (adm_username,adm_password) values(?,?)";
        Object[] params = {
                t.getAdm_username(), t.getAdm_password()
        };
        return JdbcUtil.executeUpdate(sql, params);
    }

    @Override
    public Admin findAdminByUsernameAndPassword(Admin admin) {
        String sql = "select * from t_admin where adm_username=? and adm_password=?";
        Object[] params = {
                admin.getAdm_username(), admin.getAdm_password()
        };
        List<Admin> list = JdbcUtil.executeQuery(sql, Admin.class, params);
        return list.size() > 0 ? list.get(0) : null;
    }
}
