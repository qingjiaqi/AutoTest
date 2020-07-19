package com.qj.api.auto;

import java.util.Map;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

/**接口测试统一处理类
 * @author Administrator
 *
 */
public class BaseProcessor {
	@Test(dataProvider="datas")
	public void test1(String caseId,String apiIdFromcase,String params){//通过传进来的apiId去接口信息找到url和请求方式
		//接口地址
		String url=RestUtil.getUrlByApiId(apiIdFromcase);
		
		//接口类型
		String type=RestUtil.getTypeByApiId(apiIdFromcase);
		//解析json格式的数据,准备数据
		Map<String, String> paramValues=(Map <String,String>)JSONObject.parse(params);
		//调用doservice方法完成接口调用，拿到响应报文
			String result=HttpUnit.doService(type, url, paramValues);
			System.out.println(result);
			//创建一个回写对象将请求结果保存到对应的对象中(四个参数的意思是吧excel中的用例sheet页中的用例编号为xx这行的列名为"ActualResponseData"的列，结果为result)
			WriteBackData writeBackData=new WriteBackData("用例", caseId, "ActualResponseData", result);
			//将对象添加到集合当中保存，所以创建一个集合，创建在excelUtil，因为回写方法在这个工具类中
			ExcelUtil.writeBackDatas.add(writeBackData); 
	}
	@AfterSuite
	//将所有结果批量写入到我们的文档中去
	public void batchWriteBackDatas(){
		ExcelUtil.batchWriteBackDatas("src/test/resources/cases_v5.xlsx");
		
	}
	
}
