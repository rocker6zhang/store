package com.estore.domain;


/*
 * 用户
 * 
 * 功能:
 * 		对应商品表
 * 
 * */

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable {
	private int id; 
	private String username;
	private String password;
	private String email;
	private String role; // 角色 默认是user
	private int state; // 是否激活 0 未激活
	private String activecode; // 激活码 UUID获取
	private Timestamp updatetime; // 更新时间
	
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getActivecode() {
		return activecode;
	}
	public void setActivecode(String activecode) {
		this.activecode = activecode;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + username + ", password=" + password + ", email=" + email + ", role=" + role
				+ ", state=" + state + ", activecode=" + activecode + ", updatetime=" + updatetime + "]";
	}
	
	public String check() {
		// TODO Auto-generated method stub
		String wrongMsg = null;
		if(username == null || password == null || email == null) {
			wrongMsg = "用户名或密码有误";
		}
		//校验...
		
		
		return wrongMsg;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
