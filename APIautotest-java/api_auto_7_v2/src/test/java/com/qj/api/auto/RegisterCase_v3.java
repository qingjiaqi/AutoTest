package com.qj.api.auto;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**完成注册接口的测试
 * @author Administrator
 *
 */
public class RegisterCase_v3 {
	//写多个test用例
	//请求地址 
	String url="http://119.23.241.154:8080/futureloan/mvc/api/member/register?";
	@Test(dataProvider="datas")
	public void test1(String mobilephone,String pwd){
		//准备数据
		Map<String, String> params=new HashMap<String, String>();
		params.put("mobilephone", mobilephone);
		params.put("pwd", pwd);
		System.out.println(HttpUnit.doPost(url, params));
		System.out.println();
	}	
	@DataProvider
	public Object[][] datas(){
		String excelPath="src/test/resources/cases_v1.xls";
//		Object [][] datas=ExcelUtil_v2.datas(excelPath,2,7,6,7);
		int[] rowNumber={2,3,5,7};
		int []cellNumber={6,7};
		Object[][] datas =ExcelUtil_v3.datas(excelPath, rowNumber, cellNumber);
//		System.out.println("判断是否有取到值"+datas[0][0]);
				
		return datas;
	}
	
	
	}
	