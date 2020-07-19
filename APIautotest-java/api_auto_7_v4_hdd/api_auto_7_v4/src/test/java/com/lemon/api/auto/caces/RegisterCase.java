package com.lemon.api.auto.caces;


import org.testng.annotations.DataProvider;


public class RegisterCase extends BaseProcessor{
	
	// 读取参数
	@DataProvider
	public Object[][] datas() {
		String[] cellNames = {"CaseId","ApiId", "Params","ExpectedResponseData"};
		Object[][] datas = CaseUtil.getCaseDatasByApiId("1", cellNames);
		return datas;
	}
		
}
