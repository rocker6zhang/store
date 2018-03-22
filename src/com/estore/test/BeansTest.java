package com.estore.test;

import java.math.BigInteger;
import java.util.Date;
import java.util.Vector;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Test;

import com.estore.utils.MD5Utils;
import com.estore.utils.MailUtils;
import com.estore.domain.Product;
import com.estore.domain.User;
import com.estore.utils.*;

public class BeansTest {
	
	User u = new User();
	Product p = new Product();

	

	@Test
	public void f2() {
		u.setUsername("dd");
		u.setEmail("dd");
		u.setPassword("456");
		System.out.println(u.check());
	}


	@Test
	public void f3() {
		p.setName("DD");
		p.setPrice(1.1);
		p.setImgurl("ss.png");
		System.out.println(p.check());
	}

}

