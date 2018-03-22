/*
 * 订单
 * 
 * 功能:
 * 		订单实体类,对应cart数据库中的cartOrder表
 * 注意:
 * 		日期使用sql包下面的Date类
 * 
 * */

package com.estore.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Order implements Serializable {
	private int id;
	private int user_id;
	private int pay;
	private int receipt;
	private Date createDate;
	private String address;
	private double price;
	
	//不录入数据库,,  jsp显示需要
	private List<OrderItem> ois;
	private int size;//
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<OrderItem> getOis() {
		return ois;
	}
	public void setOis(List<OrderItem> ois) {
		this.ois = ois;
		setSize(ois.size());
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	public int getReceipt() {
		return receipt;
	}
	public void setReceipt(int receipt) {
		this.receipt = receipt;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
