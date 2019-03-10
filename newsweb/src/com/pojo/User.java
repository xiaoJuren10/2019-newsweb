package com.pojo;

import java.util.Date;

public class User {
	private Integer uid;
	private String username;
	private String userpwd;
	private Date birthday;
	private String email;
	
	
	public User(){}
	public User(Integer uid, String username, String userpwd, Date birthday, String email){
		super();
		this.uid=uid;
		this.username=username;
		this.userpwd=userpwd;
		this.birthday=birthday;
		this.email=email;
		
	}
	public User(String username,String userpwd,Date birthday,String email){
		super();
		this.username=username;
		this.userpwd=userpwd;
		this.birthday=birthday;
		this.email=email;  
	}
	
	
	
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", userpwd="
				+ userpwd + ", birthday=" + birthday + ", email=" + email + "]";
	}
	
	
	
}
