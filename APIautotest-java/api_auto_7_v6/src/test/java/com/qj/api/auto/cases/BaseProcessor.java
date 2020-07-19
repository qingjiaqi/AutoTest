package com.qj.api.auto.cases;

import java.util.Map;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.qj.api.auto.pojo.WriteBackData;
import com.qj.api.auto.util.AssertUtil;
import com.qj.api.auto.util.ExcelUtil;
import com.qj.api.auto.util.HttpUnit;
import com.qj.api.auto.util.RestUtil;

/**
 * 接口测试统一处理类
 * 
 * @author Administrator
 *
 */
public class BaseProcessor {
	/**
	 * @param caseId
	 *            用例编号
	 * @param apiIdFromcase
	 *            用例sheet页的接口id
	 * @param params
	 *            入参
	 * @param ExpectedResponseData
	 *            预期结果
	 */
	@Test(dataProvider = "datas")
	public void test1(String caseId, String apiIdFromcase, String params, String ExpectedResponseData) {// 通过传进来的apiId去接口信息找到url和请求方式
		// 接口地址
		String url = RestUtil.getUrlByApiId(apiIdFromcase);

		// 接口类型
		String type = RestUtil.getTypeByApiId(apiIdFromcase);
		// 解析json格式的数据,准备数据
		Map<String, String> paramValues = (Map<String, String>) JSONObject.parse(params);
		// 调用doservice方法完成接口调用，拿到响应报文
		String actualResponesData = HttpUnit.doService(type, url, paramValues);
		System.out.println(actualResponesData);
		// 创建一个类对响应结果和预期结果进行断言,将我们实际测试结果(actualResponesData)和期望结果(ExpectedResponseData)传入进行对比
		actualResponesData = AssertUtil.assertEquals(actualResponesData, ExpectedResponseData);
		// 创建一个对象将属性赋值
		WriteBackData writeBackData = new WriteBackData("用例", caseId, "ActualResponseData", actualResponesData);
		// 将对象添加到集合当中保存，所以创建一个集合，创建在excelUtil
		ExcelUtil.writeBackDatas.add(writeBackData);
	}

	@AfterSuite
	// 将所有结果批量写入到我们的文档中去
	public void batchWriteBackDatas() {
		ExcelUtil.batchWriteBackDatas("src/test/resources/cases_v6.xlsx");

	}

}
