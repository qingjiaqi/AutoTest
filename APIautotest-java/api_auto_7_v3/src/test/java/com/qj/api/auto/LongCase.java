package com.qj.api.auto;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

public class LongCase {
	@Test(dataProvider="datas")
	public void test1(String appIdFromcase,String params){//{"mobilephone":"19000000000","pwd":""}
		//接口地址
		String excelPath="src/test/resources/cases_v3.xlsx";
		int[] rowNumber={2,3,4,5,6,7,8,9,10,11,12,13,14};
		int[] cellNumber={1,3,4};
		String url="";
		String type="";
		Object[][] objects=ExcelUtil_v4.datas(excelPath, rowNumber, cellNumber,"接口信息");
				for (Object[] object : objects) {
					String appIdFromRest=object[0].toString();
					if(appIdFromRest.equals(appIdFromcase)){
						 url=object[2].toString();//准备url;
						
						 type=object[1].toString();//指定请求类型;
						 break;
					}
				}
		//解析json格式的数据,准备数据
		Map<String, String> paramValues=(Map <String,String>)JSONObject.parse(params);
			System.out.println(HttpUnit.doService(type, url, paramValues));

	}	
	@DataProvider
	public Object[][] datas(){
		String excelPath="src/test/resources/cases_v3.xlsx";
//		Object [][] datas=ExcelUtil_v2.datas(excelPath,2,7,6,7);
		int[] rowNumber={2,3,4,5,6,7};
		int []cellNumber={3,4};
		 Object[][] datas=ExcelUtil_v4.datas(excelPath, rowNumber, cellNumber, "用例");
		System.out.println("测试页面"+datas[0][0]);
				
		return datas;
	}	
}
