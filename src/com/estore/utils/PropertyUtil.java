package com.estore.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.plaf.synth.SynthSpinnerUI;

/**
 * Desc: properties文件获取工具类
 * Created by hafiz.zhang on 2016/9/15.
 */
public class PropertyUtil {
   // private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
    private static Properties props;
    static{
        loadProps();
    }

    synchronized  static private void loadProps(){
    	System.out.println(System.getProperty("user.dir"));
        //logger.info("开始加载properties文件内容.......");
        props = new Properties();
        InputStream in = null;
      //第一种，通过类加载器进行获取properties文件流
      //第二种，通过类进行获取properties文件流
        //in = PropertyUtil.class.getResourceAsStream("/jdbc.properties");
        try {
        	//第一种，通过类加载器进行获取properties文件流                                                             
        	in = PropertyUtil.class.getClassLoader().getResourceAsStream("D:\\newWork\\myEstore\\WebRoot\\WEB-INF\\user.properties");
        	//System.out.println(in);
        	//第二种，通过类进行获取properties文件流
            //in = PropertyUtil.class.getResourceAsStream("/jdbc.properties");
            props.load(in);
        } catch (FileNotFoundException e) {
            //logger.error("jdbc.properties文件未找到");
        } catch (IOException e) {
            //logger.error("出现IOException");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
               // logger.error("jdbc.properties文件流关闭出现异常");
            }
        }
//        logger.info("加载properties文件内容完成...........");
//        logger.info("properties文件内容：" + props);
    }

    public static String getProperty(String key){
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }
}