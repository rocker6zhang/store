package com.estore.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
	LinkedList<Connection> list = null;
	LinkedList<Connection> list2 = null;
	int i = 0;


	public ConnectionPool()  {
		this(5);
	}
	//初始化 connection pool 的大小,  
	public ConnectionPool(int size) {
		//connection pool init
		Connections c = new Connections();
		list = new LinkedList<Connection>();

		try {
			for(int i = 0; i < size; i++){
				list.add(c.getConnection());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		list2 = new LinkedList<Connection>(list);
	}

	//同步方式获取连接
	public synchronized Connection getConnection(){
		while(list.isEmpty()){
			//			String name = Thread.currentThread().getName();
			//			System.out.println(name+":-wait-*******");
			try {
				this.wait();//线程停在这里, 当其被唤醒时,仍需拿到所才能执行,而只有一个线程能拿到锁
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//这里是线程安全的吗????   ->  是安全的,  因为整个方法是 synchronized 修饰的,  
		return list.poll();

	}

	public synchronized void close(Connection c){
		if(c != null) {
		list.add(c);
		this.notifyAll();
		}
	}

	public void closeAll(){
		//close connection pool.
		for(Connection c : list2) {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}


class Connections { 
	// JDBC 驱动器名称 和数据库地址
	static  String JDBC_DRIVER ;  
	//数据库的名称为 EXAMPLE
	static  String DB_URL;

	//  数据库用户和密码
	static  String USER ;

	static  String PASS ;


	public Connections() {
		ResourceBundle r = ResourceBundle.getBundle("my");
		JDBC_DRIVER = r.getString("JDBC_DRIVER");
		DB_URL = r.getString("DB_URL");
		USER = r.getString("USER");
		PASS = r.getString("PASS");
		try {
			
			//加载驱动
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//获得与数据库的连接
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL,USER,PASS);
	}
}

