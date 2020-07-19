package com.qj.api.auto.cases;

import org.testng.annotations.DataProvider;

import com.qj.api.auto.util.CaseUtil;
import com.qj.api.auto.util.ExcelUtil;

/**
 * 统一管理所有测试用例的数据提供者
 * 
 * @author Administrator
 *
 */
public class DateProviderProcessor {
	// 定义需要获取的所有列的列名,是给数据提供者用的
	public static String[] cellNames = { "ApiId", "CaseId", "Params", "ExpectedResponseData", "PreValidateSql",
			"AfterValidateSql" };

	// 注册接口数据提供者
	@DataProvider(name = "registerDate")
	public Object[][] register() {
		Object[][] datas = CaseUtil.getCaseDateByApiId("1", cellNames);
		return datas;
	}
	
	// 登录接口数据提供者
	@DataProvider(name="loginData")
	public Object[][] login() {
		return CaseUtil.getCaseDateByApiId("2", cellNames);
	}
	// 编写数据提供者方法和标签
	@DataProvider(name = "WithdrawDate")
	// 提现接口测试用例数据提供方法
	public Object[][] WithdrawDatas() {
		// 调用我们用例的处理工具类中的获取数据方法获取到我们需要的信息返回
		Object[][] datas = CaseUtil.getCaseDateByApiId("4", cellNames);
		return datas;

	}

	// 充值接口测试用例数据提供者
	@DataProvider(name = "recharge")
	public Object[][] rechargeDatas() {
		// 调用我们用例处理工具类中的获取数据的方法获取到我们需要的信息
		Object[][] datas = CaseUtil.getCaseDateByApiId("3", cellNames);
		return datas;
	}

	// 新增项目的测试数据提供
	@DataProvider(name = "loanAdd")
	public Object[][] loanAdd() {
		Object[][] datas = CaseUtil.getCaseDateByApiId("7", cellNames);
		return datas;
	}

	// 审核项目的测试数据提供
	@DataProvider(name = "loanAudit")
	public Object[][] loanAudit() {
		Object[][] datas = CaseUtil.getCaseDateByApiId("8", cellNames);
		return datas;
	}

}
