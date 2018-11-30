package com.jrs_qms_llh.yueqian.domain;

public class Admin {
    private Integer adm_id;
    private String adm_username;
    private String adm_password;

    public Integer getAdm_id() {
        return adm_id;
    }

    public void setAdm_id(Integer adm_id) {
        this.adm_id = adm_id;
    }

    public String getAdm_username() {
        return adm_username;
    }

    public void setAdm_username(String adm_username) {
        this.adm_username = adm_username;
    }

    public String getAdm_password() {
        return adm_password;
    }

    public void setAdm_password(String adm_password) {
        this.adm_password = adm_password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adm_id=" + adm_id +
                ", adm_username='" + adm_username + '\'' +
                ", adm_password='" + adm_password + '\'' +
                '}';
    }
}
