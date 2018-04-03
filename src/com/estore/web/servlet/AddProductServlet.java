package com.estore.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.estore.domain.Product;
import com.estore.service.ProductService;
import com.estore.service.ProductServiceimpl;
import com.estore.utils.PicUtils;
import com.estore.utils.UploadUtils;

/**
 * 
 * @ClassName: AddProductServlet 
 * @Description: TODO 添加商品的前端控制类
 * @author: zw
 * @date: 2018年3月26日 下午12:08:23
 */
public class AddProductServlet extends HttpServlet {

	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(true)throw new RuntimeException("test");
		String repassword = (String) request.getServletContext().getAttribute("addProduct_password");

		// 用于封装所有请求参数
		Map<String, String[]> map = new HashMap<String, String[]>();

		// 设置临时文件存储位置以及缓存大小
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024 * 100);
		File f = new File(this.getServletContext().getRealPath("/temp"));
		if (!f.exists()) {
			f.mkdirs();
			//System.out.println(f);
		}
		factory.setRepository(f);

		// 2.得到文件上传对象, 
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8"); // 解决上传文件中文乱码.


		//判断是否是上传组件, 正常情况下因该是
		if (! upload.isMultipartContent(request)) {
			//如果不是,  什么也不做,  重定向到首页
			response.sendRedirect("http://www.estore.com");
		}

		//		 upload.setSizeMax(1024*1024*10);//设置上传文件总大小
		try {
			//获得所有上传项目, 已经解析好,封装到FileItem中了, 每一个FileItem表示一个上传项
			List<FileItem> items = upload.parseRequest(request);

			// 3.遍历items,得到每一个上传项
			for (FileItem item : items) {

				//判断是上传项是文件还是文本
				if (item.isFormField()) {
					// 不是上传组件
					map.put(item.getFieldName(),
							//item.getString()获取的是表单内容
							new String[] { item.getString("utf-8") }); 
				} else {
					// 是上传组件.
					// 获取上传文件名称,可能带有路径
					String filename = item.getName();
					if(filename == null || "".equals(filename)) {
						//没有图片文件
						continue;
					}

					//校验文件格式
					int index = filename.lastIndexOf('.');


					if(index == -1 || ".JPG .PNG .GIF".indexOf(filename.substring(index+1).toUpperCase()) == -1 ) {
						//不是图片
						continue;
					}


					// 得到真实名称,去除路径
					filename = UploadUtils.subFileName(filename);

					// 得到随机名称
					String uuidname = UploadUtils.generateRandonFileName(filename);

					// 得到随机目录
					String uuidDir = UploadUtils
							.generateRandomDir(uuidname);

					//将随机目录和随机文件名组成相对路径,与当前ServletContext的真实路径/upload组合成完整路径
					//文件保存在 真实路径/upload 下方便浏览器获取
					File randomDir = new File(this.getServletContext()
							.getRealPath("/upload" + uuidDir));

					// 判断随机目录是否存在，不存在，创建.
					if (!randomDir.exists()) {
						randomDir.mkdirs();
					}
					// 完成文件上传
					File dest = new File(randomDir, uuidname);
					IOUtils.copy(item.getInputStream(),
							new FileOutputStream(dest));

					// 删除临时文件.
					item.delete();

					// 生成缩略图
					PicUtils putils = new PicUtils(dest.getCanonicalPath());// 获取上传文件的绝对磁盘路径。

					// 产生一个200*200的缩略图
					putils.resize(200, 200);

					// 封装imgurl
					map.put("imgurl", new String[] { "/upload" + uuidDir
							+ "/" + uuidname });



				}
			}


			if(! repassword.equals(map.get("password")[0])) {
				System.out.println(map.get("password")[0]);
				request.setAttribute("message", "口令错误");
				request.getRequestDispatcher("/addProduct.jsp").forward(request, response);
				return;
			}


			// 使用BeanUtils将所有数据封装到Product
			Product p = new Product();
			BeanUtils.populate(p, map);

			//校验数据
			String message = p.check();

			if(message == null) {
				// 调用service完成添加操作
				ProductService service = new ProductServiceimpl();


				service.addProduct(p);

				message = "商品 "+p.getName()+" 添加成功!";
				System.out.println(p.getName());
			}

			// 跳转到addProduct.jsp
			request.setAttribute("message", message);
			request.getRequestDispatcher("/addProduct.jsp").forward(request, response);
			return;

		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 

	}




}
