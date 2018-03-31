package com.estore.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.estore.dao.ProductDAO;
import com.estore.dao.ProductDAOimpl;
import com.estore.domain.Product;
import com.estore.utils.DataSourceUtils;

/**
 * 
 * @ClassName: ProductService 
 * @Description: TODO 用业务逻辑封装的商品操作类
 * @author: zw
 * @date: 2018年3月26日 下午1:58:39
 */
public class ProductServiceimpl implements ProductService {
	private ProductDAO pdao = new ProductDAOimpl();

	/* (non Javadoc) 
	 * @Title: addProduct
	 * @Description: TODO
	 * @param p
	 * @return 
	 * @see com.estore.service.ProductService#addProduct(com.estore.domain.Product) 
	 */ 
	@Override
	public String addProduct(Product p)  {
		// TODO Auto-generated method stub
		try {
			pdao.addProduct(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			closeConnection();
		}

		return null;
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
	 * @Title: getProducts
	 * @Description: TODO
	 * @param num
	 * @return 
	 * @see com.estore.service.ProductService#getProducts(java.lang.Integer) 
	 */ 
	@Override
	public List<Product> getProducts(Integer num)  {
		// TODO Auto-generated method stub
		List<Product> list = null;
		try {
			list = pdao.getProducts(num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			closeConnection();
		}
		return list;
	}

	/* (non Javadoc) 
	 * @Title: getProduct
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.estore.service.ProductService#getProduct(int) 
	 */ 
	@Override
	public Product getProduct(int id) {
		// TODO Auto-generated method stub
		Product p = null;
		try {
			p = pdao.getProduct(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			closeConnection();
		}
		
		return p;
	}

	/* (non Javadoc) 
	 * @Title: removeProduct
	 * @Description: TODO
	 * @param id
	 * @param request
	 * @return 
	 * @see com.estore.service.ProductService#removeProduct(java.lang.Integer, javax.servlet.http.HttpServletRequest) 
	 */ 
	@Override
	public String removeProduct(Integer id, HttpServletRequest request) {
		String msg = null;
		Product p = null;
		try {
			p = pdao.getProduct(id);
			pdao.removeProduct(id);
			//删除图片
			File f = new File(request.getServletContext().getRealPath(p.getImgurl()));
			File f_s = new File(request.getServletContext().getRealPath(p.getImgurl_s()));
			System.out.println(f);
			if(!f.delete()) {
			//	System.out.println("失败");

				throw new IOException("删除文件失败");
			}
			if(!f_s.delete()) {
			//	System.out.println("失败");

				throw new IOException("删除文件失败");
			}



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = e.getMessage();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//不回滚
		}finally {
			
			closeConnection();
		}
		return msg;
	}

	/* (non Javadoc) 
	 * @Title: updateProduct
	 * @Description: TODO
	 * @param p
	 * @return 
	 * @see com.estore.service.ProductService#updateProduct(com.estore.domain.Product) 
	 */ 
	@Override
	public String updateProduct(Product p) {
		// TODO Auto-generated method stub

		String msg = null;
		try {
			pdao.updateProduct(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = e.getMessage();
		}finally {
			
			closeConnection();
		}
		return msg;
	}

	/* (non Javadoc) 
	 * @Title: getProductByKey
	 * @Description: TODO
	 * @param key
	 * @return 
	 * @see com.estore.service.ProductService#getProductByKey(java.lang.String) 
	 */ 
	@Override
	public List<Product> getProductByKey(String key) {
		// TODO Auto-generated method stub
		List<Product> list = null;
		try {
			list = pdao.getProductByKey("%" + key + "%");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			closeConnection();
		}
		return list;
		
	}


}
