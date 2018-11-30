package com.jrs_qms_llh.yueqian.domain;

import java.util.Date;

public class TicketToday {
    private Integer ticket_id;
    private Integer ticket_no;
    private String ticket_business_code;
    private String ticket_business_name;
    private Date ticket_take_time;
    private String ticket_take_ip;
    private Integer ticket_wait_count;
    private Date ticket_call_time;
    private Integer ticket_call_count;
    private String ticket_call_ip;
    private Integer ticket_call_window;
    private String ticket_caller_workno;
    private Boolean ticket_is_success;
    private String ticket_desc;

    public Integer getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Integer ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Integer getTicket_no() {
        return ticket_no;
    }

    public void setTicket_no(Integer ticket_no) {
        this.ticket_no = ticket_no;
    }

    public String getTicket_business_code() {
        return ticket_business_code;
    }

    public void setTicket_business_code(String ticket_business_code) {
        this.ticket_business_code = ticket_business_code;
    }

    public String getTicket_business_name() {
        return ticket_business_name;
    }

    public void setTicket_business_name(String ticket_business_name) {
        this.ticket_business_name = ticket_business_name;
    }

    public Date getTicket_take_time() {
        return ticket_take_time;
    }

    public void setTicket_take_time(Date ticket_take_time) {
        this.ticket_take_time = ticket_take_time;
    }

    public String getTicket_take_ip() {
        return ticket_take_ip;
    }

    public void setTicket_take_ip(String ticket_take_ip) {
        this.ticket_take_ip = ticket_take_ip;
    }

    public Integer getTicket_wait_count() {
        return ticket_wait_count;
    }

    public void setTicket_wait_count(Integer ticket_wait_count) {
        this.ticket_wait_count = ticket_wait_count;
    }

    public Date getTicket_call_time() {
        return ticket_call_time;
    }

    public void setTicket_call_time(Date ticket_call_time) {
        this.ticket_call_time = ticket_call_time;
    }

    public Integer getTicket_call_count() {
        return ticket_call_count;
    }

    public void setTicket_call_count(Integer ticket_call_count) {
        this.ticket_call_count = ticket_call_count;
    }

    public String getTicket_call_ip() {
        return ticket_call_ip;
    }

    public void setTicket_call_ip(String ticket_call_ip) {
        this.ticket_call_ip = ticket_call_ip;
    }

    public Integer getTicket_call_window() {
        return ticket_call_window;
    }

    public void setTicket_call_window(Integer ticket_call_window) {
        this.ticket_call_window = ticket_call_window;
    }

    public String getTicket_caller_workno() {
        return ticket_caller_workno;
    }

    public void setTicket_caller_workno(String ticket_caller_workno) {
        this.ticket_caller_workno = ticket_caller_workno;
    }

    public Boolean getTicket_is_success() {
        return ticket_is_success;
    }

    public void setTicket_is_success(Boolean ticket_is_success) {
        this.ticket_is_success = ticket_is_success;
    }

    public String getTicket_desc() {
        return ticket_desc;
    }

    public void setTicket_desc(String ticket_desc) {
        this.ticket_desc = ticket_desc;
    }

    @Override
    public String toString() {
        return "TicketToday{" +
                "ticket_id=" + ticket_id +
                ", ticket_no=" + ticket_no +
                ", ticket_business_code='" + ticket_business_code + '\'' +
                ", ticket_business_name='" + ticket_business_name + '\'' +
                ", ticket_take_time=" + ticket_take_time +
                ", ticket_take_ip='" + ticket_take_ip + '\'' +
                ", ticket_wait_count=" + ticket_wait_count +
                ", ticket_call_time=" + ticket_call_time +
                ", ticket_call_count=" + ticket_call_count +
                ", ticket_call_ip='" + ticket_call_ip + '\'' +
                ", ticket_call_window=" + ticket_call_window +
                ", ticket_caller_workno='" + ticket_caller_workno + '\'' +
                ", ticket_is_success=" + ticket_is_success +
                ", ticket_desc='" + ticket_desc + '\'' +
                '}';
    }
}
