package com.estore.service;

import java.util.List;
import java.util.Map;

import com.estore.domain.Order;
import com.estore.domain.OrderItem;
import com.estore.domain.Product;
import com.estore.domain.User;

public interface OrderService {

	/**
	 * 将订单和订单项写到数据库, 先减库存,再写订单.  库存的正确性由dao维护,  对订单的操作涉及到多张表操作,需要事务管理
	 * 
	 * */

	String addOrder(List<OrderItem> orderItems);

	List<Order> getOrder(User user);

	List<OrderItem> getOrderItems(Order o);

	void receipt(int order_id);

	/**
	 * 更新用户cart内容,  如果用户登录,用户cart需写入数据库
	 * */
	void updateCartItem(Product p, Integer num, Integer uid);

	/**
	 * add用户cart内容,  如果用户登录,用户cart需写入数据库
	 * */
	void addCartItem(Product p, Integer num, Integer uid);

	/**
	 * 删除购物车项目,
	 * */
	void removeCartItem(int user_id, Product p);

	Map<Product, Integer> getCart(User u);

}