package com.qj.api.auto.cases;

import org.testng.annotations.DataProvider;

import com.qj.api.auto.util.CaseUtil;

public class RechargeCase extends BaseProcessor{
		@DataProvider
		public Object[][] datas(){
			/*"CaseId" 用例编号
			"ApiId", 接口id
			"Params" 参数
			"ExpectedResponseData" 预期结果*/
			String[] cellNames={"CaseId","ApiId","Params","ExpectedResponseData"};
			 Object[][] datas=CaseUtil.getCaseDateByApiId("3", cellNames);
			return datas;
		}	
	}
	
