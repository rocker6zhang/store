package com.estore.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.estore.domain.Product;

public interface ProductDAO {

	/**
	 * 
	* @Title: addProduct  
	* @Description: TODO 将商品存入数据库 
	* @param @param p
	* @param @throws SQLException    设定文件  
	* @return void    返回类型  
	* @throws
	 */
	void addProduct(Product p) throws SQLException;

	/**
	 * 
	* @Title: getProducts  
	* @Description: TODO 获取num个商品,从0开始,  开始下标因该作为参数传入 ?
	* @param @param num
	* @param @return
	* @param @throws SQLException    设定文件  
	* @return List<Product>    返回类型  
	* @throws
	 */
	List<Product> getProducts(Integer num) throws SQLException;

	
	/**
	 * 
	* @Title: getProduct  
	* @Description: TODO 获取单个商品 
	* @param @param id  商品id
	* @param @return
	* @param @throws SQLException    设定文件  
	* @return Product    返回类型  
	* @throws
	 */
	Product getProduct(int id) throws SQLException;


	/**
	 * 
	* @Title: setPnum  
	* @Description: TODO 设置商品库存 
	* @param @param p
	* @param @param i
	* @param @throws SQLException    设定文件  
	* @return void    返回类型  
	* @throws
	 */
	void setPnum(Product p, int i) throws SQLException;

	/**
	 * 
	* @Title: removeProduct  
	* @Description: TODO 删除商品
	* @param @param id
	* @param @throws SQLException    设定文件  
	* @return void    返回类型  
	* @throws
	 */
	void removeProduct(Integer id) throws SQLException;

	/**
	 *
	* @Title: updateProduct  
	* @Description: TODO 更新商品
	* @param @param p
	* @param @throws SQLException    设定文件  
	* @return void    返回类型  
	* @throws
	 */
	void updateProduct(Product p) throws SQLException;

	/**
	 * 
	* @Title: getProductByKey  
	* @Description: TODO 模糊查询,  
	* @param @param key 模糊查询的关键字
	* @param @return
	* @param @throws SQLException    设定文件  
	* @return List<Product>    返回类型  
	* @throws
	 */
	List<Product> getProductByKey(String key) throws SQLException;

	/**
	 * 
	* @Title: getProductsbyIdSet  
	* @Description: TODO 获取商品id集合中的商品 
	* @param @param idSet id 集合
	* @param @return
	* @param @throws SQLException    设定文件  
	* @return List<Product>    返回类型  
	* @throws
	 */
	List<Product> getProductsbyIdSet(Set<Integer> idSet) throws SQLException;

}