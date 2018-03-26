package com.estore.web.servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estore.utils.CheckCode;
import com.estore.utils.CheckImg;


/**
 * 
 * @ClassName: CheckCodeServlet 
 * @Description: TODO 校验码图片控制器, 生成中位字符校验码
 * @author: zw
 * @date: 2018年3月26日 下午12:17:43
 */
public class CheckCodeServlet extends HttpServlet {

	@Override 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//当session有效期到了,被销毁后,再访问次servlet会报以下exceptin

		//Cannot create a session after the response has been committed

		//request.getSession(); 据说加这个可以解决,

		//读入验证码文字(成语)
		List<String> words = (List<String>) request.getServletContext().getAttribute("words");
		//首次访问,创建一个
		if(words == null) {
			// 初始化阶段，读取new_words.txt
			// web工程中读取 文件，必须使用绝对磁盘路径
			
			
			words = new ArrayList<String>(200);
			
			String path = (request.getServletContext().getRealPath("new_words.txt"));
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						new FileInputStream(path), "utf-8"));
				String line;
				while ((line = reader.readLine()) != null) {
					words.add(line);
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}



		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "no-cache"); 
		response.setHeader("CacheControl", "no-cache");
		response.setHeader("Expires", "-1");
		//String code = CheckCode.getCheckImg(response.getOutputStream());
		String code = CheckImg.getImg(words,response.getOutputStream());

		request.getSession().setAttribute("checkCode",code);
		return;
	}

}
