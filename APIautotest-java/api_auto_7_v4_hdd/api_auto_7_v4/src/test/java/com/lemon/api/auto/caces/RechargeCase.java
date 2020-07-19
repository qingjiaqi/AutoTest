package com.lemon.api.auto.caces;

import org.testng.annotations.DataProvider;

public class RechargeCase extends BaseProcessor{

	//读取参数
	@DataProvider
	public Object[][] datas(){
		String[] cellNames = {"CaseId","ApiId","Params","ExpectedResponseData"};
		Object[][] datas = CaseUtil.getCaseDatasByApiId("3", cellNames);
		return datas;
	}
}
