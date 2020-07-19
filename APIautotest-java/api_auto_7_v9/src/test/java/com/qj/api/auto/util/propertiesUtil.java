package com.qj.api.auto.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Administrator
 *�����������ǵ�properties�ļ�
 */
public class propertiesUtil {
	//����һ��properties�������������ļ��е�����
	public static Properties properties=new Properties();
	//ʹ�þ�̬����鱣֤���ǵ��������������ص�properties������ȥ
	static{
		//
		String excelPath="src/test/resources/config.properties";
		//����һ������������ʼ��
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(new File(excelPath));
		} catch (FileNotFoundException e) {
			System.out.println("�ļ�·�������⣬�޷����ص��ļ�");
		}
		//��Ҫ����һ���������ȴ���һ��������
		try {
			properties.load(inStream);
		} catch (IOException e) {
			System.out.println("io��ȡ�쳣������propreties������");
		}
	}
	
	/**�����������������config�ļ��з��ص�·����
	 * @return �����ļ�·�� 
	 */
	public  static String getExcelPath() {
	String	excelPath=properties.getProperty("excel.PathName");

		return excelPath;
		
	}
	
}
