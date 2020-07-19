package com.qj.api.auto;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

import bsh.org.objectweb.asm.Type;

/**url也通过excel解析获取到对应测试用例的url地址
 * @author Administrator
 *
 */
public class RegisterCase_v6 {
	@Test(dataProvider="datas")
	public void test1(String appIdFromcase,String params){//{"mobilephone":"19000000000","pwd":""}
		//获取到测试接口地址信息
		String excelPath="src/test/resources/cases_v3.xlsx";
		int[] rowNumber={2,3,4,5,6,7,8,9,10,11,12,13,14};
		int[] cellNumber={1,3,4};
		String url="";
		String type="";
		Object[][] objects=ExcelUtil_v4.datas(excelPath, rowNumber, cellNumber,"接口信息");
				for (Object[] object : objects) {
					String appIdFromRest=object[0].toString();
					if(appIdFromRest.equals(appIdFromcase)){
						//获取到url并赋值给对应定义的变量
						 url=object[2].toString();//准备url;
						 //获取到请求类型并赋值给对应定义的变量
						 type=object[1].toString();//指定请求类型;
						 break;
					}
				}
		//解析json格式的数据,准备数据
		Map<String, String> paramValues=(Map <String,String>)JSONObject.parse(params);
//		 System.out.println("这是URL"+url);
			System.out.println(HttpUnit.doService(type, url, paramValues));
//			System.out.println("执行没有");
		
	}	
	@DataProvider
	//获取到测试提交数据
	public Object[][] datas(){
		String excelPath="src/test/resources/cases_v3.xlsx";
//		Object [][] datas=ExcelUtil_v2.datas(excelPath,2,7,6,7);
		int[] rowNumber={2,3,4,5,6,7};
		int []cellNumber={3,4};
		 Object[][] datas=ExcelUtil_v4.datas(excelPath, rowNumber, cellNumber, "用例");
		System.out.println("测试页面"+datas[0][0]);
				
		return datas;
	}	
	/*public static void main(String[] args) {
		String excelPath="src/test/resources/cases_v3.xlsx";
//		Object [][] datas=ExcelUtil_v2.datas(excelPath,2,7,6,7);
		int[] rowNumber={2,3,4,5,6,7};
		int []cellNumber={3,4};
		 Object[][] datas=ExcelUtil_v4.datas(excelPath, rowNumber, cellNumber, "用例");
		System.out.println("测试页面"+datas[0][1]);
	}*/
	
	/*public static void main(String[] args) {
//		public void test1(String appIdFromcase,String params){//{"mobilephone":"19000000000","pwd":""}
			//接口地址
			String excelPath="src/test/resources/cases_v3.xlsx";
			int[] rowNumber={2,3,4,5,6,7,8,9,10,11,12,13,14};
			int[] cellNumber={1,3,4};
			String url="";
			String type="";
			Object[][] objects=ExcelUtil_v4.datas(excelPath, rowNumber, cellNumber,"接口信息");
			System.out.println(objects[0][0]);
			System.out.println(objects[0][1]);
			System.out.println(objects[0][2]);

	}*/
}
