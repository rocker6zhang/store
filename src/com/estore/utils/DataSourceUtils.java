package com.estore.utils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
	//使用c3p0连接池
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	

	//把连接放在 ThreadLocal 里,  以供 service层和dao公用 连接
	private static final ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	static {
		ResourceBundle r = ResourceBundle.getBundle("my");
		try {   
			dataSource.setDriverClass(r.getString("JDBC_DRIVER"));
			dataSource.setJdbcUrl(r.getString("DB_URL"));
			dataSource.setUser(r.getString("USER"));
			dataSource.setPassword(r.getString("PASS"));
			
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static DataSource getDataSource() {
		return dataSource;
	}

	// 获取绑定到ThreadLocal中的Connection。
	
	public static Connection getConnectionByTransaction() throws SQLException {
		Connection con = tl.get();
		if (con == null) {
			con = dataSource.getConnection();
			tl.set(con);
		}

		return con;
	}

	// 开启事务
	public static void startTransaction(Connection con) throws SQLException {
		if (con != null)
			con.setAutoCommit(false);
	}

	// 关闭事务
	public static void closeTransaction(Connection con) throws SQLException {
		if (con != null)
			con.setAutoCommit(true);
	}

	// 事务回滚
	public static void rollback(Connection con) throws SQLException {
		if (con != null)
			con.rollback();
	}
	// 事务提交
	public static void commit(Connection con) throws SQLException {
		if (con != null)
			con.commit();// 事务提交
	}

	public static void closeConnection(Connection con) throws SQLException {
		if (con != null) {
			
			con.close();
			tl.remove();

		}
	}

	/**
	 * 当DBUtils需要手动控制事务时，调用该方法获得一个连接
	 * 这里获取的连接全部是线程共享的连接,  因为在service层要手动关闭连接,
	 * 2是保证dao层的统一性,都是以同样的方法获取连接,是否使用事务有service层决定
	 * @return
	 * @throws SQLException
	 */
	//这里可能有线程问题,  并没有,  因为没有共享变量,
	public static Connection getConnection() throws SQLException {
		Connection con = tl.get();
		if (con == null) {
			con = dataSource.getConnection();
			tl.set(con);
		}

		return con;
	}
}
