package com.estore.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.estore.dao.DAO;
import com.estore.dao.OrderDAO;
import com.estore.dao.ProductDAO;
import com.estore.domain.Order;
import com.estore.domain.OrderItem;
import com.estore.domain.Product;
import com.estore.domain.User;
import com.estore.utils.DataSourceUtils;

/**
 * 订单业务逻辑映射类
 * 在本层要处理 事务 获取和释放数据库资源
 * 
 * */

public class OrderService {
	private OrderDAO odao = new OrderDAO();
	private ProductDAO pdao = new ProductDAO();




	/**
	 * 将订单和订单项写到数据库, 先减库存,再写订单.  库存的正确性由dao维护,  对订单的操作涉及到多张表操作,需要事务管理
	 * 
	 * */

	public String addOrder(List<OrderItem> orderItems) {
		// TODO Auto-generated method stub
		String message = null;
		//注意:订单对象只有一个,
		Order o = orderItems.get(0).getOrder();

		Connection conn = null;
		try {
			conn = DataSourceUtils.getConnection();
			//开启事务, 阻止自动提交
			conn.setAutoCommit(false);

			//减库存
			for(OrderItem oi : orderItems) {

				pdao.setPnum(oi.getProduct(),oi.getNum() * -1);
			}
			//写订单
			odao.addOrder(o);

			//写订单item
			for(OrderItem oi : orderItems) {
				odao.addOrderItem(oi);
			}

			//一组操作完成, 提交
			conn.commit();
			//关闭事务
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = "出错了";
			try {
				//出错回滚
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {

			closeConnection();
		}



		return message;

	}




	public List<Order> getOrder(User user) {
		// TODO Auto-generated method stub
		List<Order> order = null;
		try {
			order = odao.getOrderByUser(user.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			closeConnection();
		}

		return order;
	}


	public List<OrderItem> getOrderItems(Order o) {
		// TODO Auto-generated method stub
		List<OrderItem> orderItems = null;
		try {
			orderItems = odao.getOrderItems(o);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			closeConnection();
		}

		return orderItems;
	}


	public void receipt(int order_id) {
		// TODO Auto-generated method stub
		try {
			odao.receipt(order_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			closeConnection();
		}
	}


	/**
	 * 更新用户cart内容,  如果用户登录,用户cart需写入数据库
	 * */
	public void updateCartItem(Product p, Integer num, Integer uid) {
		try {
			odao.updateCartItem(p, num, uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			closeConnection();
		}
	}

	/**
	 * add用户cart内容,  如果用户登录,用户cart需写入数据库
	 * */
	public void addCartItem(Product p, Integer num, Integer uid) {
		try {
			odao.addCartItem(p, num, uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			closeConnection();
		}
	}

	/**
	 * 删除购物车项目,
	 * */
	public void removeCartItem(int user_id, Product p) {
		// TODO Auto-generated method stub
		try {
			odao.removeCartItem(user_id, p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			closeConnection();
		}
	}
	private void closeConnection() {
		// TODO Auto-generated method stub
		//释放连接
		try {

			DataSourceUtils.closeConnection(DataSourceUtils.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}




	public Map<Product,Integer> getCart(User u) {
		// TODO Auto-generated method stub
		// DB cart
		Map<Integer,Integer> cart = null;
		// return for session cart
		Map<Product,Integer> sesCart = null;
		try {
			cart = odao.getCart(u);
			if(cart == null || cart.isEmpty()) {
				return null;
			}
			
			ProductService ps = new ProductService();
			sesCart = new	HashMap<Product,Integer>();
			/*效率太差,合并成一条查询*/
			/*Product p = null;
			for(Integer pid : cart.keySet()) {
				p = ps.getProduct(pid);
				if(p != null) {
					sesCart.put(p, cart.get(pid));
				}
			}*/
			
			List<Product> products = pdao.getProductsbyKeySet(cart.keySet());

		
			for(Product p : products) {
				
				sesCart.put(p, cart.get(p.getId()));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			closeConnection();
		}
		return sesCart;
	}



}
