package com.estore.utils;

import java.awt.Color; 
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

/**
 * 生产一次性验证码的工具类.
 * 凑数test
 * */
public class CheckCode {
	/**
	 * 使用awt绘图,绘制一张100*25大小的一次性图形验证码.
	 * 生成一张验证码,其内容是随机的字母和数字, 通过给定的输出流输出.
	 * @param os -输出流,
	 * @return String 类型的验证码内容.
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	public static String getCheckImg(OutputStream os) throws IOException {
		int width = 100, height = 25;
		StringBuffer sb = new StringBuffer();
		
		BufferedImage buf = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = buf.createGraphics();
		
		//set 背景
		g2.setColor(new Color(0XFFE4C4));
		g2.fillRect(0, 0, width, height);

		//set 边框
		g2.setColor(Color.blue);
		g2.drawRect(0, 0, width-1, height-1);

		//画直线
		//g2.drawLine(0,20,width,20);

		Font font = new Font("Fixedsys", Font.PLAIN, 20);  
		//       Font font = new Font("Times New Roman", Font.ROMAN_BASELINE, fontHeight);  
		g2.setFont(font); 
		int  x = 5, y = 20;
		
		//得到随机数字,写到图片上
		for(int i = 0; i < 4; i++){
			
			while(true){
				int ran = new Random().nextInt(123);
				if((ran > '0' && ran < '9') || (ran > 'a' && ran < 'z') || (ran > 'A' && ran < 'Z')){
					String str = String.valueOf((char)ran);
					sb.append(str);
					
					g2.setColor(Color.BLACK);
					g2.drawString(str, x, y);
					x+=25;
					break;
				}
			}
			
			
		}

	
		ImageIO.write(buf, "jpg", os);
		
		
		return sb.toString().toUpperCase();
	}

	BufferedImage getSeccode(){

		return null;
	}
}
