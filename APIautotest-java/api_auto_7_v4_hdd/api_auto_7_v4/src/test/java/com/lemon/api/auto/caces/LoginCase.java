package com.lemon.api.auto.caces;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

/**登录接口测试类
 * @author Administrator
 *
 */
public class LoginCase extends BaseProcessor{

	//读取参数
		@DataProvider
		public Object[][] datas(){
			String[] cellNames = {"CaseId","ApiId","Params","ExpectedResponseData"};
			Object[][] datas = CaseUtil.getCaseDatasByApiId("2", cellNames);
			return datas;
		}
}
