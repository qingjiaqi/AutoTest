package com.qj.api.auto.cases;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.qj.api.auto.util.CaseUtil;

/*µÇÂ¼½Ó¿Ú²âÊÔÀà
 * @author Administrator
 *
 */
public class LoginCase extends BaseProcessor {
	@DataProvider
	public Object[][] datas(){
		String[] cellNames={"CaseId","ApiId","Params","ExpectedResponseData"};
		 Object[][] datas=CaseUtil.getCaseDateByApiId("2", cellNames);
		return datas;
	}	
}