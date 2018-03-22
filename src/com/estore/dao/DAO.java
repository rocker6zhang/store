package com.estore.dao;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.estore.utils.ConnectionPool;
/**
 * cart数据库操作
 * 
 * 功能:
 * 		操作数据库的工具类,提供连接数据库功能, 提供getConnection()方法
 * 		提供数据库连接池
 * 		提供对事务操作的方法
 * 
 * 注意:
 * 		关于资源释放
			Statement 和 ResultSet 要在使用完成后关闭, 不然可能会占用过多内存, 造成内存溢出
 * 			Connection 可以复用, 在应用结束前关闭.
 * 
 * 事务
 * 	  Connection接口自带的事务机制需要保证多步SQL操作使用相同的连接对象，这样才能保证事务的执行环境
 * 
 * */


public class DAO { 
	//不使用继承方式了,  使用c3p0
}
