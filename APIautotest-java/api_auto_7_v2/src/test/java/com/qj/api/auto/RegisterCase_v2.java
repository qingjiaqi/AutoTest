package com.qj.api.auto;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**����testng�������ṩ�� ���ע��ӿڵĲ���
 * @author Administrator
 *
 */
public class RegisterCase_v2 {
	//д���test����
	//�����ַ
	String url="http://119.23.241.154:8080/futureloan/mvc/api/member/register?";
	@Test(dataProvider="datas")//�����ṩ�ߵķ�����
	//����һ�����������������ṩ���ṩ�����ݣ�ע�����˳��͸���
	public void test1(String mobilephone,String pwd){
		//׼������
		Map<String, String> params=new HashMap<String, String>();
		params.put("mobilephone", mobilephone);
		params.put("pwd", pwd);
		System.out.println(HttpUnit.doPost(url, params));
		System.out.println();
	}	
	//ֱ�ӽ���������������д�ڴ�����
	@DataProvider
	public Object[][] datas(){
		Object [][] datas={
				{"13211111111",""},
				{"","123456"},
				{"132","123456"},
				{"13211111111","12345"},
				{"13211111113","123456"},
				{"13211111113","123456"}
				};
		return datas;
	}
	
	
	}
	