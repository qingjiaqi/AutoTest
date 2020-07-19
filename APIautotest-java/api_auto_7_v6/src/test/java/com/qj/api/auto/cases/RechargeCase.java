package com.qj.api.auto.cases;

import org.testng.annotations.DataProvider;

import com.qj.api.auto.util.CaseUtil;

public class RechargeCase extends BaseProcessor{
		@DataProvider
		public Object[][] datas(){
			/*"CaseId" �������
			"ApiId", �ӿ�id
			"Params" ����
			"ExpectedResponseData" Ԥ�ڽ��*/
			String[] cellNames={"CaseId","ApiId","Params","ExpectedResponseData"};
			 Object[][] datas=CaseUtil.getCaseDateByApiId("3", cellNames);
			return datas;
		}	
	}
	
