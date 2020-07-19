package com.lemon.api.auto.caces;

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
	public void test(String caseId,String apiId,String parameters,String expectedResponseData) {
		//得到
		String url = RestUtil.getUrlByApiId(apiId);
		//type
		String type = RestUtil.getTypeByApiId(apiId);
		//参数
		Map<String, String> params = (Map<String, String>) JSONObject.parse(parameters);
		String actualResponseData = HttpUtil.doService(url, type, params);
		actualResponseData = AssertUtil.assertEquals(actualResponseData,expectedResponseData);
		//保存回写数据对象
		ExcelUtil.writeBackDatas.add(new WriteBackData("用例", caseId, "ActualResponseData", actualResponseData));
	}
	
	@AfterSuite
	public void batchWriteBackDatas(){
		ExcelUtil.batchWriteBackDatas("src/test/resources/cases_v6.xlsx");
	}
	
}
