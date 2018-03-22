package com.estore.utils;



import java.util.UUID;

/**
 * 对fileUpload提供支持的工具类,
 * 提供对于上传文件名及文件保存目录的操作
 * */
public class UploadUtils {

	/**
	 * 截取真实文件名
	 * 
	 * @param file Name
	 * @return 去除路径后的文件名
	 */
	public static String subFileName(String fileName) {
		// 查找最后一个 \出现位置
		int index = fileName.lastIndexOf("\\");
		if (index == -1) {
			return fileName;
		}
		return fileName.substring(index + 1);
	}

	// 获得随机UUID文件名
	public static String generateRandonFileName(String fileName) {
		// 先得到扩展名
		String ext = fileName.substring(fileName.lastIndexOf("."));
		return UUID.randomUUID().toString() + ext;
	}

	// 获得hashcode生成二级目录
	public static String generateRandomDir(String uuidFileName) {
		int hashCode = uuidFileName.hashCode();
		// 一级目录,取hashcode后4位
		int d1 = hashCode & 0xf;
		// 二级目录,取hashcode后5到8位
		int d2 = (hashCode >> 4) & 0xf;
		
		//还可以再细分,  这里只用两层即可
		return "/" + d1 + "/" + d2;
	}

}
