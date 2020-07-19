package com.qj.api.auto.cases;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
 * 不需要鉴权操作的测试用例编写处
 * 
 * @author Administrator
 *
 */
public class NotNeedLoginCaseProcessorTest2 extends DateProviderProcessor2{
	// 准备一个日志对象传入当前类的字节码文件,这样就打印出的日志就可知道是那个类的问题
	public Logger logger = Logger.getLogger(NotNeedLoginCaseProcessorTest2.class);
	//创建用例调用方法的对象
	BaseProcessor baseProcessor=new BaseProcessor();
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
//	 注册接口测试方法(register 注册)
	@Test(dataProvider ="registerDate")
	public void register1(String apiIdFromcase, String caseId, String params, String ExpectedResponseData,
			String preValidateSql, String afterValidateSql) {
		//调用统一接口处理方法执行测试用例
		baseProcessor.caseBase(apiIdFromcase, caseId, params, ExpectedResponseData, preValidateSql, afterValidateSql);
	}


	//登录接口(loan add)
	
	@Test(dependsOnMethods={"register1"},dataProvider="loginData")
	public void login(String apiIdFromCase,String caseId,String params,String expectedResponseData,
			String preValidateSql,String afterValidateSql){
		baseProcessor.caseBase(apiIdFromCase, caseId, params, expectedResponseData, preValidateSql, afterValidateSql);

		
	}
	
	//这个方法是将我们成功登录的用的id put到我们变量的map中去修改之前的值
//	@Test(dependsOnMethods={"login"})
	public void toAddProjectUserId() {
		logger.info("开始获取注册用户的id==========================");
		//从数据库查询出刚刚用来注册且成功了的用户id，去添加项目
		//从我们变量的map中获取到我们注册的手机号码
		String variableValue=VariableUtil.variableNameAndValuesMap.get("${toBeRegisterMobilephone}");
		logger.info("获取到的注册手机号为"+variableValue);
		//定义我们的sql语句
		String sql="select id from member   where MobilePhone="+variableValue;
		//调用更新变量方法修改变量map中“去添加项目的用户id”保存的变量值
		VariableUtil.getSpecifiedValueInInterface(sql,"id","${toAddProjectUserId}");
	}
	

//	@AfterSuite
//	// 将所有结果批量写入到我们的文档中去
//	public void batchWriteBackDatas() {
//		ExcelUtil.batchWriteBackDatas(propertiesUtil.getExcelPath("excel.PathName"));
//
//		//
//
//	}


}
