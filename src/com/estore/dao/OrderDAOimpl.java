	

package com.estore.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.estore.domain.Order;
import com.estore.domain.OrderItem;
import com.estore.domain.Product;
import com.estore.domain.User;
import com.estore.utils.DataSourceUtils;

/**
 * 
 * @ClassName: OrderDAO 
 * @Description: TODO 订单相关数据库操作的封装类, 对应order_表和orderItem和cart表.
 * @author: zw
 * @date: 2018年3月26日 下午2:02:46
 */
public class OrderDAOimpl extends DAO implements OrderDAO {

	/* (non Javadoc) 
	 * @Title: addOrder
	 * @Description: TODO
	 * @param o
	 * @throws SQLException 
	 * @see com.estore.dao.OrderDAO1#addOrder(com.estore.domain.Order) 
	 */ 
	@Override
	public void addOrder(Order o) throws SQLException {
		//o 里面已经封装了用户id了

		o.setCreateDate(new Date(System.currentTimeMillis()));

		String sql = "insert into order_ values(?,0,?,0,?,null,?)";
		Connection conn = DataSourceUtils.getConnection();
		try(
				//订单表的id是自动增长的,掺入数据后要取得id. 所以在取得PreparedStatement对象时要指定RETURN_GENERATED_KEYS,以表示生成的键应该可用于获取。
				PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){

			ps.setInt(1,o.getUser_id());
			ps.setDate(2, o.getCreateDate());
			ps.setString(3, o.getAddress());
			ps.setDouble(4, o.getPrice());
			ps.execute();

			ResultSet rs = ps.getGeneratedKeys();

			if(rs.next()) {
				o.setId(rs.getInt(1));
			}

			rs.close();

		}catch(SQLException e) {
			throw e;
		}

	}

	/* (non Javadoc) 
	 * @Title: addOrderItem
	 * @Description: TODO
	 * @param oi
	 * @throws SQLException 
	 * @see com.estore.dao.OrderDAO1#addOrderItem(com.estore.domain.OrderItem) 
	 */ 
	@Override
	public void addOrderItem(OrderItem oi) throws SQLException {
		// TODO Auto-generated method stub

		String sql = "insert into orderitem values(?,?,?);";

		Connection conn = DataSourceUtils.getConnection();
		try(PreparedStatement stat = conn.prepareStatement(sql);){

			stat.setInt(1,oi.getOrder().getId());
			stat.setInt(2,oi.getProduct().getId());
			stat.setInt(3,oi.getNum());

			stat.execute();

			//			if(true) {
			//				throw new SQLException("事务测试");
			//			}

		}catch(SQLException e) {
			throw e;
		}

	}



	/* (non Javadoc) 
	 * @Title: getOrderByUser
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws SQLException 
	 * @see com.estore.dao.OrderDAO1#getOrderByUser(int) 
	 */ 
	@Override
	public List<Order> getOrderByUser(int id) throws SQLException {
		// TODO Auto-generated method stub

		String sql = "select * from order_ where user_id=? order by createDate desc";
		List<Order> order = new ArrayList<Order>();
		Order o = null;
		Connection conn = DataSourceUtils.getConnection();
		try(
				//订单表的id是自动增长的,掺入数据后要取得id. 所以在取得PreparedStatement对象时要指定RETURN_GENERATED_KEYS,以表示生成的键应该可用于获取。
				PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){

			ps.setInt(1,id);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				o = new Order();
				o.setUser_id(id);
				o.setId(rs.getInt("id"));
				o.setAddress(rs.getString("address"));
				o.setCreateDate(rs.getDate("createDate"));
				o.setPay(rs.getInt("pay"));
				o.setPrice(rs.getDouble("price"));
				o.setReceipt(rs.getInt("receipt"));

				order.add(o);

			}

			rs.close();

		}catch(SQLException e) {
			throw e;
		}

		return order;
	}

	/* (non Javadoc) 
	 * @Title: getOrderItems
	 * @Description: TODO
	 * @param o
	 * @return
	 * @throws SQLException 
	 * @see com.estore.dao.OrderDAO1#getOrderItems(com.estore.domain.Order) 
	 */ 
	@Override
	public List<OrderItem> getOrderItems(Order o) throws SQLException {
		// TODO Auto-generated method stub
		List<OrderItem> ois = new ArrayList<OrderItem>();
		OrderItem oi = null;
		Product p = null;

		String sql = "select num, name, price, category, pnum, imgurl, description, id " + 
				"from orderitem AS o inner join products as P on(o.product_id = p.id) where order_id = ? ";
		Connection conn = DataSourceUtils.getConnection();
		try(
				//订单表的id是自动增长的,掺入数据后要取得id. 所以在取得PreparedStatement对象时要指定RETURN_GENERATED_KEYS,以表示生成的键应该可用于获取。
				PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){

			ps.setInt(1,o.getId());

			ResultSet resu = ps.executeQuery();

			while(resu.next()) {
				p = new Product();
				p.setId(resu.getInt("id"));
				p.setName(resu.getString("name"));
				p.setPrice(resu.getDouble("price"));
				p.setCategory(resu.getString("category"));
				p.setPnum(resu.getInt("pnum"));
				p.setImgurl(resu.getString("imgurl"));
				p.setDescription(resu.getString("description"));

				oi = new OrderItem(o,p,resu.getInt("num"));

				ois.add(oi);
			}

			resu.close();

		}catch(SQLException e) {
			throw e;
		}



		return ois;
	}

	/* (non Javadoc) 
	 * @Title: receipt
	 * @Description: TODO
	 * @param order_id
	 * @throws SQLException 
	 * @see com.estore.dao.OrderDAO1#receipt(int) 
	 */ 
	@Override
	public void receipt(int order_id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update order_ set receipt=1 where id=?;";
		Connection conn = DataSourceUtils.getConnection();
		try(
				//订单表的id是自动增长的,掺入数据后要取得id. 所以在取得PreparedStatement对象时要指定RETURN_GENERATED_KEYS,以表示生成的键应该可用于获取。
				PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){

			ps.setInt(1,order_id);

			int lina = ps.executeUpdate();



		}catch(SQLException e) {
			throw e;
		}

	}

	/* (non Javadoc) 
	 * @Title: addCartItem
	 * @Description: TODO
	 * @param p
	 * @param num
	 * @param uid
	 * @throws SQLException 
	 * @see com.estore.dao.OrderDAO1#addCartItem(com.estore.domain.Product, java.lang.Integer, java.lang.Integer) 
	 */ 
	@Override
	public void addCartItem(Product p, Integer num, Integer uid) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into cart values(?,?,?)";
		Connection conn = DataSourceUtils.getConnection();
		try(
				PreparedStatement ps = conn.prepareStatement(sql);){

			ps.setInt(1,uid);
			ps.setInt(2,p.getId());
			ps.setInt(3,num);

			int lina = ps.executeUpdate();
			if(lina == 0)
				throw new SQLException("写入cart item 失败!!");


		}catch(SQLException e) {
			throw e;
		}
	}

	/* (non Javadoc) 
	 * @Title: updateCartItem
	 * @Description: TODO
	 * @param p
	 * @param num
	 * @param uid
	 * @throws SQLException 
	 * @see com.estore.dao.OrderDAO1#updateCartItem(com.estore.domain.Product, java.lang.Integer, java.lang.Integer) 
	 */ 
	@Override
	public void updateCartItem(Product p, Integer num, Integer uid) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update cart set num=num+? where user_id=? and product_id=?";
		Connection conn = DataSourceUtils.getConnection();
		try(
				PreparedStatement ps = conn.prepareStatement(sql);){

			ps.setInt(1,num);
			ps.setInt(2,uid);
			ps.setInt(3,p.getId());

			int lina = ps.executeUpdate();
			if(lina == 0)
				throw new SQLException("更新cart item 失败!!");


		}catch(SQLException e) {
			throw e;
		}
	}
	/* (non Javadoc) 
	 * @Title: removeCartItem
	 * @Description: TODO
	 * @param user_id
	 * @param p
	 * @throws SQLException 
	 * @see com.estore.dao.OrderDAO1#removeCartItem(int, com.estore.domain.Product) 
	 */ 
	@Override
	public void removeCartItem(int user_id, Product p) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM cart WHERE  user_id=? and product_id=?";

		Connection conn = DataSourceUtils.getConnection();
		try(PreparedStatement stat = conn.prepareStatement(sql);){

			stat.setInt(1, user_id);
			stat.setInt(2, p.getId());

			int lina = stat.executeUpdate();
			if(lina == 0)
				throw new SQLException("delete cart item 失败!!");

		}catch(SQLException e) {
			throw e;
		}

	}

	/* (non Javadoc) 
	 * @Title: getCart
	 * @Description: TODO
	 * @param u
	 * @return
	 * @throws SQLException 
	 * @see com.estore.dao.OrderDAO1#getCart(com.estore.domain.User) 
	 */ 
	@Override
	public Map<Integer,Integer> getCart(User u) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select product_id,num from cart where user_id=?";
		Map<Integer,Integer> cart = new HashMap<Integer,Integer>();

		Connection conn = DataSourceUtils.getConnection();
		try(PreparedStatement stat = conn.prepareStatement(sql);){

			stat.setInt(1, u.getId());

			ResultSet resu = stat.executeQuery();

			while(resu.next()) {
				cart.put(resu.getInt(1),resu.getInt(2));

			}
		}catch(SQLException e) {
			throw e;
		}

		return cart;
	}

}
