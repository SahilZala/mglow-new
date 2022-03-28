package com.mark2.mglow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userdata")
public class UserData {

	@Id
	String userid;
	String username;
	@Column(unique = true)
	String email;
	@Column(unique = true)
	String mobileno;
	@Column(unique = true)
	String password;
	
	public UserData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserData(String userid, String username, String email, String mobileno, String password) {
		super();
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.mobileno = mobileno;
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserData [userid=" + userid + ", username=" + username + ", email=" + email + ", mobileno=" + mobileno
				+ ", password=" + password + "]";
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
