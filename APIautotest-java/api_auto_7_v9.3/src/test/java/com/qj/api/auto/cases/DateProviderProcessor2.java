package com.qj.api.auto.cases;

import com.qj.api.auto.util.CaseUtil;
import org.testng.annotations.DataProvider;

/**
 * 统一管理所有测试用例的数据提供者
 * 
 * @author Administrator
 *
 */
public class DateProviderProcessor2 {
	// 定义需要获取的所有列的列名,是给数据提供者用的
	public static String[] cellNames = { "ApiId", "CaseId", "Params", "ExpectedResponseData", "PreValidateSql",
			"AfterValidateSql" };

	// 注册接口数据提供者
	@DataProvider(name = "registerDate")
	public Object[][] register() {
		Object[][] datas = CaseUtil.getCaseDateByApiId2("1", cellNames);
		return datas;
	}
	
	// 登录接口数据提供者
	@DataProvider(name="loginData")
	public Object[][] login() {
		return CaseUtil.getCaseDateByApiId2("2", cellNames);
	}
	
}
