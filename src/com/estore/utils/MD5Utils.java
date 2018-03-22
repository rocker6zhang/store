package com.estore.utils;

 

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5加密工具类
 * 
 * 
 * */
public class MD5Utils {
	/**
	 * 用MD5算法加密给定的String参数
	 * @param message true 要加密的字符串
	 * @return 加密后的字符串, 一般是32位
	 * 
	 */
	public static String md5(String message) {
		if(message == null) {
			new NullPointerException("message canot be null");
		}
		byte[] b = null;

		try {
			//获得 息摘要算法 对象,  并调用方法生成 md5 串
			MessageDigest md = MessageDigest.getInstance("MD5");
			b = md.digest(message.getBytes());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return new BigInteger(1, b).toString(16);// 16进制数字;
	}

	public static int md5Num(String password) {
		

		return 0;
	}
}
