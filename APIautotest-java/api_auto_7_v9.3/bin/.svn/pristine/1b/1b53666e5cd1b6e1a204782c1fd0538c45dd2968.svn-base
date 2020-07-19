package com.qj.api.auto.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Administrator
 *用来解析我们的properties文件
 */
public class propertiesUtil {
	//创建一个properties对象用来共享文件中的数据
	public static Properties properties=new Properties();
	//使用静态代码块保证我们的数据能正常加载到properties对象中去
	static{
		//
		String excelPath="src/test/resources/config.properties";
		//创建一个输入流并初始化
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(new File(excelPath));
		} catch (FileNotFoundException e) {
			System.out.println("文件路径有问题，无法加载到文件");
		}
		//需要传进一个流对象，先创建一个流对象
		try {
			properties.load(inStream);
		} catch (IOException e) {
			System.out.println("io读取异常，请检查propreties工具类");
		}
	}
	
	/**这个方法是用来返回config文件中返回的路径的
	 * @param fileKey 文件的key值
	 * @return
	 */
	public  static String getExcelPath(String fileKey) {
	String	excelPath=properties.getProperty(fileKey);

		return excelPath;
		
	}
	/**获取配置信息
	 * @param configureKey 配置的key
	 * @return 返回key对应的值
	 */
	public  static String getValueByKey(String configureKey) {
		String	value=properties.getProperty(configureKey);

			return value;
			
		}
}
