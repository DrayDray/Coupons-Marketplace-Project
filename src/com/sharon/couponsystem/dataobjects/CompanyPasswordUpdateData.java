package com.sharon.couponsystem.dataobjects;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CompanyPasswordUpdateData {
	
	private long companyId;
	private String password;
	
	public CompanyPasswordUpdateData() {
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
