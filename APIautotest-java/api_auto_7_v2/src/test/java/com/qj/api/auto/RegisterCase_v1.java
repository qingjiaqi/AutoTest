package com.qj.api.auto;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

/**���ע��ӿڵĲ���
 * @author Administrator
 *
 */
public class RegisterCase_v1 {
	//mobilephone:"13211111111",pwd:""
	//mobilephone:"",pwd:"123456"
	//д���test������һ�ַ���
	String url="http://119.23.241.154:8080/futureloan/mvc/api/member/register?";//�����ַ
	@Test
	public void test1(){
		//׼�����ݴ���һ��map�Ĳ�������
		Map<String, String> params=new HashMap<String, String>();
		//��������ӵ�����������
		params.put("mobilephone", "13211111111");
		params.put("pwd", "");
		//���ö�Ӧ��http���󷽷�������������
		System.out.println(HttpUnit.doPost(url, params));
		System.out.println();
	}	
	
	@Test
	public void test2(){
		
		Map<String, String> params=new HashMap<String, String>();
		params.put("mobilephone", "");
		params.put("pwd", "123456");
		System.out.println(HttpUnit.doPost(url, params));
		System.out.println();


	}	
	@Test
	public void test3(){
		Map<String, String> params=new HashMap<String, String>();
		params.put("mobilephone", "132");
		params.put("pwd", "123456");
		System.out.println(HttpUnit.doPost(url, params));
		System.out.println();


	}	
	
	@Test
	public void test4(){
		Map<String, String> params=new HashMap<String, String>();
		params.put("mobilephone", "13211111111");
		params.put("pwd", "12345");
		System.out.println(HttpUnit.doPost(url, params));
		System.out.println();


	}	
	
	@Test
	public void test5(){
		Map<String, String> params=new HashMap<String, String>();
		params.put("mobilephone", "13211111112");
		params.put("pwd", "123456");
		System.out.println(HttpUnit.doPost(url, params));
		System.out.println();


	}	
	@Test
	public void test6(){
		Map<String, String> params=new HashMap<String, String>();
		params.put("mobilephone", "13211111112");
		params.put("pwd", "123456");
		System.out.println(HttpUnit.doPost(url, params));

	}	
	
}
