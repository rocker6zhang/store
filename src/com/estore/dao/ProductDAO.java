/*
 * 操作商品表
 * 
 * 功能:
 * 		提供 product的 增删改查方法
 * 
 * 注意:
 * 		订单表的id是自动增长的,插入数据后要取得id. 
 * 		
 * */

package com.estore.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.estore.domain.Product;
import com.estore.domain.User;
import com.estore.utils.DataSourceUtils;

//Connection
public class ProductDAO extends DAO{

	/**
	 * 将product存入数据库
	 * */

	public void addProduct(Product p) throws SQLException {

		/*
				private String id; // 商品编号
				private String name; // 名称
				private double price; // 价格
				private String category; // 分类
				private int pnum; // 数量
				private String imgurl; // 图片路径
				private String description; // 描述
		 * */
		String sql = "insert into products values(?,?,?,?,?,?,null);";

		Connection conn = DataSourceUtils.getConnection();
		try(
				PreparedStatement stat = conn.prepareStatement(sql);){

			stat.setString(1,p.getName());
			stat.setDouble(2,p.getPrice());
			stat.setString(3,p.getCategory());
			stat.setInt(4,p.getPnum());
			stat.setString(5,p.getImgurl());
			stat.setString(6,p.getDescription());
			stat.execute();

		}catch(SQLException e) {
			throw e;
		}


	}


	public List<Product> getProducts(Integer num)throws SQLException{
		String selectAllSql = "select * from products limit 0,?;";
		List<Product> list = null;
		Connection conn = DataSourceUtils.getConnection();
		try(PreparedStatement stat = conn.prepareStatement(selectAllSql);){
			
			stat.setInt(1, num);
			ResultSet resu = stat.executeQuery();
			list = new ArrayList<Product> (100);
			//			System.out.println("resu.getFetchSize() = "+resu.getFetchSize());

			while(resu.next()){

				list.add(Assemble(resu));
			}

			resu.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return list;
	}

	public Product getProduct(int id) throws SQLException{
		String sql = "select * from products where id = ?";

		Product p = null;
		Connection conn = DataSourceUtils.getConnection();
		try(PreparedStatement stat = conn.prepareStatement(sql);){

			stat.setInt(1, id);
			ResultSet resu = stat.executeQuery();
			if(resu.next()) {
				p = Assemble(resu);
			}

			resu.close();

		}catch(SQLException e) {
			throw e;
		}
		return p;
	}


	//修改商品库存, 要注意并发问题
	/**
	 * 这里考虑并发问题,把对数据库的操作集中在一条语句中,(不用先查库存,在修改库存). 
	 * 在update中判断库存数量>=订单数量,  再用update的返回值判断是否更新成功(update的返回指示sql语句影响的行数),
	 * 如果更新失败就抛异常
	 * @throws SQLException 
	 * */
	public void setPnum(Product p, int i) throws SQLException {
		// TODO Auto-generated method stub

		//i 为正数表示增加,  负数表示减少
		//i要参加比较库存,  这里对其符号取反

		i = i * -1;

		String sql = "update products set pnum = pnum-? Where id=? && pnum>=?";
		//这里的 connection在try资源释放里,  执行结束会释放,  如果要加事务,就不能释放了
		Connection conn = DataSourceUtils.getConnection();
		try(PreparedStatement stat = conn.prepareStatement(sql);){

			stat.setInt(1,i);
			stat.setInt(2,p.getId());
			stat.setInt(3,i);
			if(stat.executeUpdate() == 0){
				throw new SQLException("update 跟新失败");
			}

		}catch(SQLException e) {
			throw e;
		}
	}

	//查看商品库存, 可能会得到过期数据
	public int getPnum(int id) {
		String sql = "select pnum from products where id=?";
		return 0;
	}


	public void removeProduct(Integer id) throws SQLException {
		String sql = "delete from products where id=?";
		//这里的 connection在try资源释放里,  执行结束会释放,  如果要加事务,就不能释放了
		Connection conn = DataSourceUtils.getConnection();
		try(PreparedStatement stat = conn.prepareStatement(sql);){

			stat.setInt(1,id);
			if(stat.executeUpdate() == 0){
				throw new SQLException("删除失败");
			}

		}catch(SQLException e) {
			throw e;
		}
	}


	public void updateProduct(Product p) throws SQLException {
		String sql = "update products set name=?, price=?, pnum=?,description=? where id=?";

		//这里的 connection在try资源释放里,  执行结束会释放,  如果要加事务,就不能释放了
		Connection conn = DataSourceUtils.getConnection();
		try(PreparedStatement stat = conn.prepareStatement(sql);){

			stat.setString(1,p.getName());
			stat.setDouble(2,p.getPrice());
			stat.setInt(3,p.getPnum());
			stat.setString(4,p.getDescription());
			stat.setInt(5,p.getId());
			if(( stat.executeUpdate())== 0){
				throw new SQLException("update 跟新失败");
			}
			
			

		}catch(SQLException e) {
			throw e;
		}
	}


	public List<Product> getProductByKey(String key) throws SQLException {
		// TODO Auto-generated method stub

		String sql = "select * from products where name like ? limit 0,25";
		List<Product> list = new ArrayList<Product>(10);
		
		//这里的 connection在try资源释放里,  执行结束会释放,  如果要加事务,就不能释放了
		Connection conn = DataSourceUtils.getConnection();
		ResultSet resu = null;
		try(PreparedStatement stat = conn.prepareStatement(sql);){

			//System.out.println(key);
			stat.setString(1,key);
			resu = stat.executeQuery();

			while(resu.next()){
				list.add(Assemble(resu));
			}

		}catch(SQLException e) {
			throw e;
		}finally {
			if(resu != null)
				resu.close();
		}

		return list;
	}


	private Product Assemble(ResultSet resu) throws SQLException {
		// TODO Auto-generated method stub
		Product p = new Product();
		/*
		private String id; // 商品编号
		private String name; // 名称
		private double price; // 价格
		private String category; // 分类
		private int pnum; // 数量
		private String imgurl; // 图片路径
		private String description; // 描述
		 * */
		p.setId(resu.getInt("id"));
		p.setName(resu.getString("name"));
		p.setPrice(resu.getDouble("price"));
		p.setCategory(resu.getString("category"));
		p.setPnum(resu.getInt("pnum"));
		p.setImgurl(resu.getString("imgurl"));
		p.setDescription(resu.getString("description"));

		return p;
	}


	public List<Product> getProductsbyKeySet(Set<Integer> keySet) throws SQLException {
		StringBuilder sb = new StringBuilder("select * from products where id in(");
		//从1 开始,最后一个? 不加","
		for(int i = 0; i < keySet.size(); i++) {
			sb.append("?,");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(");");
		String Sql = sb.toString();
		List<Product> list = null;
		System.out.println(sb);
		Connection conn = DataSourceUtils.getConnection();
		try(PreparedStatement stat = conn.prepareStatement(Sql);){
			int i= 1;
			for(Integer id : keySet) {
				stat.setInt(i++, id);
			}
			
			ResultSet resu = stat.executeQuery();
			list = new ArrayList<Product> (20);
			//			System.out.println("resu.getFetchSize() = "+resu.getFetchSize());

			while(resu.next()){

				list.add(Assemble(resu));
			}

			resu.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return list;
	}



}
