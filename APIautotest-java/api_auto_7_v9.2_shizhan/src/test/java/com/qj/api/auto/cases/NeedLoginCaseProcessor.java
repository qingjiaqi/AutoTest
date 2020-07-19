package com.qj.api.auto.cases;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.microsoft.schemas.office.office.STInsetMode;
import com.qj.api.auto.pojo.WriteBackData;
import com.qj.api.auto.util.AssertUtil;
import com.qj.api.auto.util.CaseUtil;
import com.qj.api.auto.util.DBCheckUtil;
import com.qj.api.auto.util.ExcelUtil;
import com.qj.api.auto.util.HttpUnit;
import com.qj.api.auto.util.JDBCUtil;
import com.qj.api.auto.util.RestUtil;
import com.qj.api.auto.util.VariableUtil;
import com.qj.api.auto.util.propertiesUtil;

import bsh.Variable;

/**
 * 测试用例编写类并且需要进行鉴权后才能操作的接口用例在此处写
 * 
 * @author Administrator
 *
 */
public class NeedLoginCaseProcessor extends DateProviderProcessor{
	// 准备一个日志对象传入当前类的字节码文件,这样就打印出的日志就可知道是那个类的问题
	public Logger logger = Logger.getLogger(NeedLoginCaseProcessor.class);
	//创建用例调用方法的对象
	BaseProcessor baseProcessor=new BaseProcessor();
		static{
			//调用登录接口鉴权使用
			LoginSingle loginSingle=new LoginSingle();
			loginSingle.login("login.url", "login.param", "request.mode.post", "api.contet.type.form");
		}
	
	
	/**
	 * @param apiIdFromcase
	 *            用例sheet页的接口id
	 * @param caseId
	 *            用例sheet页的编号
	 * @param params
	 *            用例sheet页的参数
	 * @param ExpectedResponseData
	 *            用例sheet也的预期结果
	 * @param preValidateSql
	 *            用例sheet页的执行前脚本验证
	 * @param afterValidateSql
	 *            用例sheet页的执行后脚本验证
	 */
	// 提现接口测试方法(Withdraw 提现)
//	@Test(dataProvider = "WithdrawDate")
	public void Withdraw(String apiIdFromcase, String caseId, String params, String ExpectedResponseData,
			String preValidateSql, String afterValidateSql) {
		//调用统一接口处理方法执行测试用例
		baseProcessor.caseBase(apiIdFromcase, caseId, params, ExpectedResponseData, preValidateSql, afterValidateSql);
	}

	//充值接口测试方法(recharge 充值)
//	@Test(dataProvider="recharge")
	public void recharge(String apiIdFromcase,String caseId,String params,String expectedResponseData,
			String preValidateSql,String afterValidateSql) {
		//调用接口统一处理方法(无返回值)
//		baseProcessor.caseBase(caseId, apiIdFromcase, params, expectedResponseData, preValidateSql, afterValidateSql);
		baseProcessor.caseBase(apiIdFromcase, caseId, params, expectedResponseData, preValidateSql, afterValidateSql);
	}
	
	//
	//添加项目接口(loan add)
//	@Test(dataProvider="loanAdd")
	public void loanAdd(String apiIdFromCase,String caseId,String params,String expectedResponseData,
			String preValidateSql,String afterValidateSql){
		baseProcessor.caseBase(apiIdFromCase, caseId, params, expectedResponseData, preValidateSql, afterValidateSql);
		//从数据库查询出刚刚新增的项目id为
		//定义我们的sql语句及查询条件的值
		String a="1121713";
		String sql="select id from loan   where MemberID="+a+"  order by CreateTime desc LIMIT 1";
		//调用更新变量方法修改保存变量的map值
		VariableUtil.getSpecifiedValueInInterface(sql,"id","${toExamineProjectId}");
		
	}
	
	//审核项目接口(loan add)
//	@Test(dataProvider="loanAudit")
	public void loanAudit(String apiIdFromCase,String caseId,String params,String expectedResponseData,
			String preValidateSql,String afterValidateSql){
		baseProcessor.caseBase(apiIdFromCase, caseId, params, expectedResponseData, preValidateSql, afterValidateSql);
	}
	
	
	

	@AfterSuite
	// 将所有结果批量写入到我们的文档中去
	public void batchWriteBackDatas() {
		ExcelUtil.batchWriteBackDatas(propertiesUtil.getExcelPath("excel.PathName"));

		//

	}


}
