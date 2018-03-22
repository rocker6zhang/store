package com.estore.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * 缩略图
 * 1.比例由输入决定,当输入尺寸比例和原图不匹配时,生成的缩略图比例会失真
 * 2.如果输入的不是图片怎么办?,  ->由调用者考虑
 * */

public class PicUtils {
	private String srcFile;//原文件名
	private String destFile;//新文件名
	
	private int width;//原图像size
	private int height;
	private Image img;//原图像
	String ext;//图片后缀名

	/**
	 * 构造函数
	 * 
	 * 
	 * @param fileName
	 *            String
	 * @throws IOException
	 */
	public PicUtils(String fileName) throws IOException { //a.jpg
		File _file = new File(fileName); // 读入文件
		this.srcFile = fileName;
		// 查找最后一个.获取后缀名
		int index = this.srcFile.lastIndexOf(".");
		this.ext = this.srcFile.substring(index+1);
		
		//合成新的文件名, 原文件名+s
		this.destFile = srcFile.substring(0,index)+"-s."+ext;  //a_s.jpg
		
		img = javax.imageio.ImageIO.read(_file); // 构造Image对象
		width = img.getWidth(null); // 得到源图宽
		height = img.getHeight(null); // 得到源图长
	}

	/**
	 * 强制压缩/放大图片到固定的大小
	 * 
	 * @param w
	 *            int 新宽度
	 * @param h
	 *            int 新高度
	 * @throws IOException
	 */
	public void resize(int w, int h) throws IOException {
		BufferedImage _image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		_image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		
		// 将新的图像(缩小后)输出到文件流
		BufferedOutputStream out = new BufferedOutputStream( new FileOutputStream(destFile)); 
		ImageIO.write(_image, ext, out);
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//		encoder.encode(_image); // 近JPEG编码
		out.close();
		
		
		 //String formatName = dstName.substring(dstName.lastIndexOf(".") + 1);
         //FileOutputStream out = new FileOutputStream(dstName);
         //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
         //encoder.encode(dstImage);
         //ImageIO.write(dstImage, /*"GIF"*/ formatName /* format desired */ , new File(dstName) /* target */ );
	}

	/**
	 * 按照固定的比例缩放图片
	 * 
	 * @param t
	 *            double 比例
	 * @throws IOException
	 */
	public void resize(double t) throws IOException {
		int w = (int) (width * t);
		int h = (int) (height * t);
		resize(w, h);
	}

	/**
	 * 以宽度为基准，等比例放缩图片
	 * 
	 * @param w
	 *            int 新宽度
	 * @throws IOException
	 */
	public void resizeByWidth(int w) throws IOException {
		int h = (int) (height * w / width);
		resize(w, h);
	}

	/**
	 * 以高度为基准，等比例缩放图片
	 * 
	 * @param h
	 *            int 新高度
	 * @throws IOException
	 */
	public void resizeByHeight(int h) throws IOException {
		int w = (int) (width * h / height);
		resize(w, h);
	}

	/**
	 * 按照最大高度限制，生成最大的等比例缩略图
	 * 
	 * @param w
	 *            int 最大宽度
	 * @param h
	 *            int 最大高度
	 * @throws IOException
	 */
	public void resizeFix(int w, int h) throws IOException {
		if (width / height > w / h) {
			resizeByWidth(w);
		} else {
			resizeByHeight(h);
		}
	}

	/**
	 * 设置目标文件名 setDestFile
	 * 
	 * @param fileName
	 *            String 文件名字符串
	 */
	public void setDestFile(String fileName) throws Exception {
		if (!fileName.endsWith(".jpg")) {
			throw new Exception("Dest File Must end with \".jpg\".");
		}
		destFile = fileName;
	}

	/**
	 * 获取目标文件名 getDestFile
	 */
	public String getDestFile() {
		return destFile;
	}

	/**
	 * 获取图片原始宽度 getSrcWidth
	 */
	public int getSrcWidth() {
		return width;
	}

	/**
	 * 获取图片原始高度 getSrcHeight
	 */
	public int getSrcHeight() {
		return height;
	}

}
