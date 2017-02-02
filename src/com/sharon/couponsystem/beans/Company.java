package com.sharon.couponsystem.beans;

import java.util.Collection;

//JAX-RS (Jackson) supports the use of JAXB (Java API for XML Binding) to bind a JavaBean to XML or JSON and vise versa. The JavaBean must be annotated with @XmlRootElement. 
//see:
//https://www.ibm.com/developerworks/library/wa-aj-tomcat/
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Company {

	private long id;
	private String compName;
	private String password;
	private String email;
	
	public Company(){
	}
	
	//There is no need for a constructor without ID, as each new company inputs their Chet Pei for ID
	public Company(long id, String compName, String password, String email) {
		this.id = id;
		this.compName = compName;
		this.password = password;
		this.email = email;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email + "]";
	}
	
}
