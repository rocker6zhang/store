package com.estore.domain;

import java.io.Serializable;

/*
 *订单项(订单明细里的一条记录)
 *
 *功能:
 *		对应cart数据库中的OrderItem表
 *
 *注意:
 *		订单与订单项是一对多关系
 * 
 * */




public class OrderItem implements Serializable {
	
	private Order order;
	private Product product;
	private int num;
	
	
	public OrderItem() {
		super();
	}
	public OrderItem(Order order, Product product, int num) {
		super();
		this.order = order;
		this.product = product;
		this.num = num;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
