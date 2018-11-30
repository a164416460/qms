package com.jrs_qms_llh.yueqian.domain;


public class BusinessType {
		

	private Integer btId;
	private String btCode;
	private String btName;
	private Integer btLimitCount;
	private String btDesc;
	
	public BusinessType() {
		super();
	}
	public BusinessType(Integer btId, String btCode, String btName,
			Integer btLimitCount, String btDesc) {
		super();
		this.btId = btId;
		this.btCode = btCode;
		this.btName = btName;
		this.btLimitCount = btLimitCount;
		this.btDesc = btDesc;
	}
	public Integer getBtId() {
		return btId;
	}
	public void setBtId(Integer btId) {
		this.btId = btId;
	}
	public String getBtCode() {
		return btCode;
	}
	public void setBtCode(String btCode) {
		this.btCode = btCode;
	}
	public String getBtName() {
		return btName;
	}
	public void setBtName(String btName) {
		this.btName = btName;
	}
	public Integer getBtLimitCount() {
		return btLimitCount;
	}
	public void setBtLimitCount(Integer btLimitCount) {
		this.btLimitCount = btLimitCount;
	}
	public String getBtDesc() {
		return btDesc;
	}
	public void setBtDesc(String btDesc) {
		this.btDesc = btDesc;
	}
	@Override
	public String toString() {
		return "BusinessType [btId=" + btId + ", btCode=" + btCode
				+ ", btName=" + btName + ", btLimitCount=" + btLimitCount
				+ ", btDesc=" + btDesc + "]";
	}
	
	
	
	
}
