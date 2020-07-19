package com.qj.api.auto;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

/**完成注册接口的测试
 * @author Administrator
 *
 */
public class RegisterCase_v1 {
	//mobilephone:"13211111111",pwd:""
	//mobilephone:"",pwd:"123456"
	//写多个test用例第一种方法
	String url="http://119.23.241.154:8080/futureloan/mvc/api/member/register?";//请求地址
	@Test
	public void test1(){
		//准备数据创建一个map的参数集合
		Map<String, String> params=new HashMap<String, String>();
		//将数据添加到参数集合中
		params.put("mobilephone", "13211111111");
		params.put("pwd", "");
		//调用对应的http请求方法，返回请求体
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
