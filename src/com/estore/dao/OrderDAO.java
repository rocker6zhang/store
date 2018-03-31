package com.estore.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.estore.domain.Order;
import com.estore.domain.OrderItem;
import com.estore.domain.Product;
import com.estore.domain.User;

public interface OrderDAO {

	void addOrder(Order o) throws SQLException;

	void addOrderItem(OrderItem oi) throws SQLException;

	List<Order> getOrderByUser(int id) throws SQLException;

	List<OrderItem> getOrderItems(Order o) throws SQLException;

	void receipt(int order_id) throws SQLException;

	void addCartItem(Product p, Integer num, Integer uid) throws SQLException;

	void updateCartItem(Product p, Integer num, Integer uid) throws SQLException;

	void removeCartItem(int user_id, Product p) throws SQLException;

	Map<Integer, Integer> getCart(User u) throws SQLException;

}