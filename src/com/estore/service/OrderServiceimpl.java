package com.estore.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.estore.dao.DAO;
import com.estore.dao.OrderDAOimpl;
import com.estore.dao.ProductDAO;
import com.estore.dao.OrderDAO;
import com.estore.dao.ProductDAOimpl;
import com.estore.domain.Order;
import com.estore.domain.OrderItem;
import com.estore.domain.Product;
import com.estore.domain.User;
import com.estore.utils.DataSourceUtils;

/**
 * 
 * 在本层要处理 事务 获取和释放数据库资源
 * 
 * */

/**
 * 
 * @ClassName: OrderService 
 * @Description: TODO 用业务逻辑封装的订单及购物车操作类
 * @author: zw
 * @date: 2018年3月26日 下午1:59:51
 */
public class OrderServiceimpl implements OrderService {
	private OrderDAO odao = new OrderDAOimpl();
	private ProductDAO pdao = new ProductDAOimpl();




	/* (non Javadoc) 
	 * @Title: addOrder
	 * @Description: TODO
	 * @param orderItems
	 * @return 
	 * @see com.estore.service.OrderService#addOrder(java.util.List) 
	 */ 

	@Override
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




	/* (non Javadoc) 
	 * @Title: getOrder
	 * @Description: TODO
	 * @param user
	 * @return 
	 * @see com.estore.service.OrderService#getOrder(com.estore.domain.User) 
	 */ 
	@Override
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


	/* (non Javadoc) 
	 * @Title: getOrderItems
	 * @Description: TODO
	 * @param o
	 * @return 
	 * @see com.estore.service.OrderService#getOrderItems(com.estore.domain.Order) 
	 */ 
	@Override
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


	/* (non Javadoc) 
	 * @Title: receipt
	 * @Description: TODO
	 * @param order_id 
	 * @see com.estore.service.OrderService#receipt(int) 
	 */ 
	@Override
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


	/* (non Javadoc) 
	 * @Title: updateCartItem
	 * @Description: TODO
	 * @param p
	 * @param num
	 * @param uid 
	 * @see com.estore.service.OrderService#updateCartItem(com.estore.domain.Product, java.lang.Integer, java.lang.Integer) 
	 */ 
	@Override
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

	/* (non Javadoc) 
	 * @Title: addCartItem
	 * @Description: TODO
	 * @param p
	 * @param num
	 * @param uid 
	 * @see com.estore.service.OrderService#addCartItem(com.estore.domain.Product, java.lang.Integer, java.lang.Integer) 
	 */ 
	@Override
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

	/* (non Javadoc) 
	 * @Title: removeCartItem
	 * @Description: TODO
	 * @param user_id
	 * @param p 
	 * @see com.estore.service.OrderService#removeCartItem(int, com.estore.domain.Product) 
	 */ 
	@Override
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




	/* (non Javadoc) 
	 * @Title: getCart
	 * @Description: TODO
	 * @param u
	 * @return 
	 * @see com.estore.service.OrderService#getCart(com.estore.domain.User) 
	 */ 
	@Override
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
			
			ProductService ps = new ProductServiceimpl();
			sesCart = new	HashMap<Product,Integer>();
			/*效率太差,合并成一条查询*/
			/*Product p = null;
			for(Integer pid : cart.keySet()) {
				p = ps.getProduct(pid);
				if(p != null) {
					sesCart.put(p, cart.get(pid));
				}
			}*/
			
			List<Product> products = pdao.getProductsbyIdSet(cart.keySet());

		
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
