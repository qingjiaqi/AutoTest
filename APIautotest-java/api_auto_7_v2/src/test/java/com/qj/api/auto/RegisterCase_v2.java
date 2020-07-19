package com.qj.api.auto;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**利用testng的数据提供者 完成注册接口的测试
 * @author Administrator
 *
 */
public class RegisterCase_v2 {
	//写多个test用例
	//请求地址
	String url="http://119.23.241.154:8080/futureloan/mvc/api/member/register?";
	@Test(dataProvider="datas")//数据提供者的方法名
	//定义一个方法来接收数据提供者提供的数据，注意参数顺序和个数
	public void test1(String mobilephone,String pwd){
		//准备数据
		Map<String, String> params=new HashMap<String, String>();
		params.put("mobilephone", mobilephone);
		params.put("pwd", pwd);
		System.out.println(HttpUnit.doPost(url, params));
		System.out.println();
	}	
	//直接将多条用例的数据写在代码中
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
	