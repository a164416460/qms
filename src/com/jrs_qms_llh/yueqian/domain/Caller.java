package com.jrs_qms_llh.yueqian.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Caller implements Serializable {

    /**
     * 主键
     */
    private Integer c_id;

    /**
     * 工号
     */
    private String c_workno;

    /**
     * 密码
     */
    private String c_password;

    /**
     * 姓名
     */
    private String c_name;

    /**
     * 性别
     */
    private String c_sex;

    /**
     * 入职时间
     */
    private Date c_hiredate;

    /**
     * 出生日期
     */
    private Date c_birth;

    /**
     * 备注
     */
    private String c_remark;

    /**
     * 最后登录时间
     */
    private Timestamp c_last_login_time;

    /**
     * 最后登录IP
     */
    private String c_last_login_ip;

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public String getC_workno() {
        return c_workno;
    }

    public void setC_workno(String c_workno) {
        this.c_workno = c_workno;
    }

    public String getC_password() {
        return c_password;
    }

    public void setC_password(String c_password) {
        this.c_password = c_password;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_sex() {
        return c_sex;
    }

    public void setC_sex(String c_sex) {
        this.c_sex = c_sex;
    }

    public Date getC_hiredate() {
        return c_hiredate;
    }

    public void setC_hiredate(Date c_hiredate) {
        this.c_hiredate = c_hiredate;
    }

    public Date getC_birth() {
        return c_birth;
    }

    public void setC_birth(Date c_birth) {
        this.c_birth = c_birth;
    }

    public String getC_remark() {
        return c_remark;
    }

    public void setC_remark(String c_remark) {
        this.c_remark = c_remark;
    }

    public Timestamp getC_last_login_time() {
        return c_last_login_time;
    }

    public void setC_last_login_time(Timestamp c_last_login_time) {
        this.c_last_login_time = c_last_login_time;
    }

    public String getC_last_login_ip() {
        return c_last_login_ip;
    }

    public void setC_last_login_ip(String c_last_login_ip) {
        this.c_last_login_ip = c_last_login_ip;
    }

    @Override
    public String toString() {
        return "Caller{" +
                "c_id=" + c_id +
                ", c_workno='" + c_workno + '\'' +
                ", c_password='" + c_password + '\'' +
                ", c_name='" + c_name + '\'' +
                ", c_sex='" + c_sex + '\'' +
                ", c_hiredate=" + c_hiredate +
                ", c_birth=" + c_birth +
                ", c_remark='" + c_remark + '\'' +
                ", c_last_login_time=" + c_last_login_time +
                ", c_last_login_ip='" + c_last_login_ip + '\'' +
                '}';
    }
}
