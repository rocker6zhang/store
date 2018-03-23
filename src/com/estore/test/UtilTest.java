package com.estore.test;

import java.math.BigInteger;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Test;

import com.estore.utils.MD5Utils;
import com.estore.utils.MailUtils;
import com.estore.utils.*;

public class UtilTest {

	
	public int[] f() {
		System.out.println(MD5Utils.md5(""));
		System.out.println(MD5Utils.md5("1"));
		System.out.println(MD5Utils.md5("2"));
		System.out.println(new Date());
		Object o = null;
		Date d = (Date) o;
		System.out.println(ss()+"".hashCode());
		//System.out.println(MD5Utils.md5(null));
		 int s = 0;
	            return new int[]{s};
	        
	}
	
	int ss() {
		try {
			return 0;
			
		}catch(Exception e) {
			
		}finally {
			
			return 1;
		}
	}

	@Test

	public void f2()  {
		ResourceBundle r = ResourceBundle.getBundle("my");
		System.out.println(r.getString("email"));
	}

	@Test
	public void f3() {
		System.out.println(PropertyUtil.getProperty("path"));
	}

	@Test
	public void f4() {
		for(int i = 0; i < 50; i++) {
			System.out.println("<div class='l'>"+(i<<i)+"</div>");
		}
	}

	@Test
	public void f5() {
		int k = 1;
		int num = 102;
		int m = 0;
		int count = 0;
		int flag = 0;
		if( k == 0) {
			for(int i = 10;; i = i * 10) {

				//循环结束条件
				if(num  == 0) {
//					System.out.println(num);
					break;
				}

				//得到个位数字
				m = (num % 10);

				if(i == 10) {
					//这时的m是原数字的 个位
					count = 1;
				}
				if(i == 100) {
					//这时的m是原数字的 十位
					count += m;
				}
				if(i > 100) {
					count += (m*i/100);
				}
				//				System.out.println(count+"         "+m);

				//舍去个位数字
				num = num / 10;
			}



		}else {

			for(int i = 10;; i = i * 10) {

				//循环结束条件
				if(num  == 0) {
					//System.out.println(num);
					break;
				}

				//得到个位数字
				m = (num % 10);

				if(i == 10) {
					//这时的m是原数字的 个位
					if(m >= k) 
					count = 1;
				}
				if(i == 100) {
					//这时的m是原数字的 十位
					if(m >= k+1) {
						count += 10;
					
					count += (m-1);
					}
					if(m == k) {
						count += (flag+1);
						count += (m-1);
					}
					
					
				}
				if(i > 100) {
					if(m >= k+1) {
						count += (i/10);
					}
						
						count += ((m-1)*19);
						
					
				}
				//				System.out.println(count+"         "+m);

				//舍去个位数字
				num = num / 10;
				
				flag = m;
			}


		}


























		System.out.println(count);

	}































	@Test
	public void s() {
		for(int i = 0; i < 1000; i++) {
			System.out.print(i+"\t");
			if(i%10 == 9) {
				System.out.println();
			}
			
		}
	}


	@Test
	public void intersection() {
		// write your code here
		//		 
		//		 int a =3;
		//		 int b = 3;
		//		 BigInteger newa = new BigInteger (new Integer(a).toString());
		//		 BigInteger newb = new BigInteger (new Integer(b).toString());
		//		 newa.add(newb);

		long n = 105;
		long m = 100;
		for(long i = n - 1; i > 1; i--){
			n = n * i;
			for(long j = getZero(n); j > 0; j--){
				m = m * 10;
			}
			n = n % m;
			m = 100;

			System.out.println(n+"       "+i);

		}





	}

	long getZero(long n){
		char[] ch = String.valueOf(n).toCharArray();
		long count = 0;
		for(int i = ch.length-1; i >= 0; i--) {
			if(ch[i] == '0') {
				count++;
			}else {
				break;
			}
		}

		return count;
	}
}

