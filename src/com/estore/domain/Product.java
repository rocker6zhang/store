package com.estore.domain;

import java.io.Serializable;

/*
 * 商品实体
 * 
 * 功能:
 * 		对应商品表
 * 
 * */



public class Product implements Serializable {
	/*
	 * 			private String id; // 商品编号
				private String name; // 名称
				private double price; // 价格
				private String category; // 分类
				private int pnum; // 数量
				private String imgurl; // 图片路径
				private String description; // 描述
	 * */
	private int id;
	private String name;
	private double price;
	private String category;
	private int pnum;
	private String imgurl;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getImgurl() {
		return imgurl;
	}
	public String getImgurl_s() { 
		//给原图文件名后面加上-s就是缩略图
		int index = imgurl.lastIndexOf(".");
		return imgurl.substring(0, index) + "-s" + imgurl.substring(index);
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + ", pnum=" + pnum
				+ ", imgurl=" + imgurl + ", description=" + description + "]";
	}
	public String check() {
		// TODO Auto-generated method stub
		//非空校验,  图片格式校验
		String msg = null;
		if(name == null || price == 0 || "".equals(name)) {
			msg = "name cannot be null and price should be number";
			return msg;
		}


		if(imgurl == null || "".equals(imgurl)) {
			msg = "图片格式不正确, 只支持 JPG PNG GIF";
		}

		return msg;
	}
	
	public String updateCheck() {
		// TODO Auto-generated method stub
		//非空校验,  图片格式校验
		String msg = null;
		if(name == null || price == 0 || "".equals(name)) {
			msg = "name cannot be null and price should be number";
		}



		return msg;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}






}
