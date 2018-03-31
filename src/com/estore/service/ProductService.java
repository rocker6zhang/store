package com.estore.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.estore.domain.Product;

public interface ProductService {

	String addProduct(Product p);

	List<Product> getProducts(Integer num);

	Product getProduct(int id);

	String removeProduct(Integer id, HttpServletRequest request);

	String updateProduct(Product p);

	List<Product> getProductByKey(String key);

}