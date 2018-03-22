package com.estore.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 发送邮件的工具类
 * 
 * */
public class MailUtils {
	//发送邮件 
	/**
	 * 使用配置好的账户发送邮件,  使用之前应该先配制发送账户
	 * @param email true -收件方邮件地址 subject true -邮件主题 emailMsg true -邮件正文
	 * */
	public static void sendMail(String email, String subject, String emailMsg)
			throws AddressException, MessagingException {
		//PropertyUtil  读不到制定文件
//		final String account = PropertyUtil.getProperty("account");	
//		final String email_password = PropertyUtil.getProperty("email_password");
//		final String emailAddress = 	PropertyUtil.getProperty("email");
//		System.out.println(account+"--"+email_password+"**"+emailAddress);
//		if(true)return;
		// 1.创建一个程序与邮件服务器会话对象 Session
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "SMTP");
		props.setProperty("mail.host", "smtp.163.com");
		props.setProperty("mail.smtp.auth", "true");// 指定验证为true

		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("17080325667", "wy123456");
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.创建一个Message，它相当于是邮件内容 
		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress("17080325667@163.com")); // 设置发送者

		message.setRecipient(RecipientType.TO, new InternetAddress(email)); // 设置发送方式与接收者

		message.setSubject(subject);
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

		message.setContent(emailMsg, "text/html;charset=utf-8");

		// 3.创建 Transport用于将邮件发送

		Transport.send(message);
	}
}
