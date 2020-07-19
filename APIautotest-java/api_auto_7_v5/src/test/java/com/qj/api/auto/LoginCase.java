package com.qj.api.auto;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

/*µÇÂ¼½Ó¿Ú²âÊÔÀà
 * @author Administrator
 *
 */
public class LoginCase extends BaseProcessor {
	@DataProvider(name="q")
	public Object[][] datas(){
		String[] cellNames={"CaseId","ApiId","Params"};
		 Object[][] datas=CaseUtil.getCaseDateByApiId("2", cellNames);
		return datas;
	}	
}