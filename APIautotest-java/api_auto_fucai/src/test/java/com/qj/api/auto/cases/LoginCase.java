package com.qj.api.auto.cases;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.qj.api.auto.util.CaseUtil;

/*��¼�ӿڲ�����
 * @author Administrator
 *
 */
public class LoginCase extends BaseProcessor {
	@DataProvider
	public Object[][] datas(){
		 Object[][] datas=CaseUtil.getCaseDateByApiId("1", cellNames);
		return datas;
		
	}	
}