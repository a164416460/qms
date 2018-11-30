package com.jrs_qms_llh.yueqian.domain;

public class BusinessWindow {
	private Integer bw_id;
	private String bw_no;//窗口号
	private String bw_type_code;//业务代号
	private String  bw_type_name;//业务类型名称
	public BusinessWindow() {
		super();
	}
	public BusinessWindow(Integer bw_id, String bw_no, String bw_type_code, String bw_type_name) {
		super();
		this.bw_id = bw_id;
		this.bw_no = bw_no;
		this.bw_type_code = bw_type_code;
		this.bw_type_name = bw_type_name;
	}
	public Integer getBw_id() {
		return bw_id;
	}
	public void setBw_id(Integer bw_id) {
		this.bw_id = bw_id;
	}
	public String getBw_no() {
		return bw_no;
	}
	public void setBw_no(String bw_no) {
		this.bw_no = bw_no;
	}
	public String getBw_type_code() {
		return bw_type_code;
	}
	public void setBw_type_code(String bw_type_code) {
		this.bw_type_code = bw_type_code;
	}
	public String getBw_type_name() {
		return bw_type_name;
	}
	public void setBw_type_name(String bw_type_name) {
		this.bw_type_name = bw_type_name;
	}
	@Override
	public String toString() {
		return "BusinessWindow [bw_id=" + bw_id + ", bw_no=" + bw_no + ", bw_type_code=" + bw_type_code
				+ ", bw_type_name=" + bw_type_name + "]";
	}
	
}
