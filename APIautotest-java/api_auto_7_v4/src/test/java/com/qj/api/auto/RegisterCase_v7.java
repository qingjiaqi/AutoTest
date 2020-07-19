package com.qj.api.auto;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

import bsh.org.objectweb.asm.Type;

public class RegisterCase_v7 {
	@Test(dataProvider="datas")
	public void test1(String apiIdFromcase,String params){//通过传进来的apiId去接口信息找到url和请求方式
		//接口地址
		String url=RestUtil.getUrlByApiId(apiIdFromcase);
		
		//接口类型
		String type=RestUtil.getTypeByApiId(apiIdFromcase);
		//解析json格式的数据,准备数据
		Map<String, String> paramValues=(Map <String,String>)JSONObject.parse(params);
			System.out.println(HttpUnit.doService(type, url, paramValues));

	}	
	@DataProvider
	public Object[][] datas(){
		String[] cellNames={"ApiId","Params"};
		 Object[][] datas=CaseUtil.getCaseDateByApiId("1", cellNames);
		System.out.println("测试页面"+datas[0][0]);
		return datas;
	}	
	
}
