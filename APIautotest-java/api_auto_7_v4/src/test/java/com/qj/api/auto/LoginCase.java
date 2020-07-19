package com.qj.api.auto;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

/*登录接口测试类
 * @author Administrator
 *
 */
public class LoginCase {
	@Test(dataProvider="caseDate")
	public void test1(String apiIdFromcase,String params){//通过传进来的apiId去接口信息找到url和请求方式
		//接口地址
		String url=RestUtil.getUrlByApiId(apiIdFromcase);
		
		//接口类型
		String type=RestUtil.getTypeByApiId(apiIdFromcase);
		//解析json格式的数据,准备数据
		Map<String, String> paramValues=(Map <String,String>)JSONObject.parse(params);
			System.out.println(HttpUnit.doService(type, url, paramValues));

	}	
	@DataProvider(name="caseDate")
	public Object[][] datas(){
		//定义一个数组，保存要读取的字段
		String[] cellNames={"ApiId","Params"};
		//调用getCaseDateByApiId方法获取到测试数据
		 Object[][] datas=CaseUtil.getCaseDateByApiId("2", cellNames);
		System.out.println("测试页面"+datas[0][0]);
		return datas;
	}	
}
