package com.qj.api.auto.cases;

import com.alibaba.fastjson.JSONObject;
import com.qj.api.auto.pojo.WriteBackData;
import com.qj.api.auto.util.*;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;

import java.util.Map;

/**接口测试时的前期准备工作，包括数据的准备接口的执行，回写等
 * @author Administrator
 *Processor(处理，加工)
 */
public class BaseProcessor {
	static Logger logger = Logger.getLogger(BaseProcessor.class);

	/**
	 *
	 * @param apiIdFromCase  用例sheet页的接口id
	 * @param caseId	用例sheet页的编号
	 * @param params		用例sheet页的参数
	 * @param expectedResponseData  用例sheet也的预期结果
	 * @param preValidateSql    用例sheet页的执行前脚本验证
	 * @param afterValidateSql   用例sheet页的执行后脚本验证
	 */
	public  void caseBase(String apiIdFromCase, String caseId, String params, String expectedResponseData, String preValidateSql, String afterValidateSql) {
		logger.info("调用接口前的数据验证");
		//判断用例中是否有脚本需要执行
		if (preValidateSql != null && preValidateSql.trim().length() > 0) {
			// 脚本当中也有参数，所以也需要参数化,将执行前的脚本参数化一波preValidateSql,因为会返回参数化后的数据所以需要接收
			preValidateSql = VariableUtil.replaceVariables(preValidateSql);

			// 在接口执行前调用我们的脚本查询出我们要验证的字段,通过调用数据库工具类方法
			String preValidateResult = DBCheckUtil.doQuery(preValidateSql);
			// 将查询结果保存到对象当中去创建对象
			WriteBackData writeBackData = new WriteBackData("用例", caseId, "PreValidateResult", preValidateResult);
			// 将查询结果添加到writeBackDatas中去
			ExcelUtil.writeBackDatas.add(writeBackData);

		}

		// 接口地址 通过传进来的apiId去接口信息找到url和请求方式
		String url = RestUtil.getUrlByApiId(apiIdFromCase);
		//接口名称  通过传进来的apiId去接口信息找到接口名称
		String name=RestUtil.getNameApiId(apiIdFromCase);
		logger.info("根据接口编号【" + apiIdFromCase + "】"+"【"+name+"接口】"+"获取到请求的url" +"【"+ url+"】");

		// 接口类型
		String type = RestUtil.getTypeByApiId(apiIdFromCase);
		logger.info("根据接口编号【" + apiIdFromCase + "】获取到请求的接口类型" + type);
		logger.info("当前执行用例sheet页的第【"+caseId+"】条用例");

		// 将变量替换成真实的数据
		params = VariableUtil.replaceVariables(params);
		logger.info("替换变量");

		// 解析json格式的数据,准备数据
		Map<String, String> paramValues = (Map<String, String>) JSONObject.parse(params);
//		调用doservice方法完成接口调用，拿到响应报文
		logger.info("开始调用接口");
		String actualResponesData = HttpUnit.doService(type, url, paramValues,
				propertiesUtil.getValueByKey("api.contet.type.form"));
		// System.out.println(actualResponesData);
		// 创建一个类对响应结果和预期结果进行断言,将我们实际测试结果(actualResponesData)和期望结果(ExpectedResponseData)传入进行对比
		actualResponesData = AssertUtil.assertEquals(actualResponesData, expectedResponseData);
		// 创建一个对象将属性赋值
		WriteBackData writeBackData = new WriteBackData("用例", caseId, "ActualResponseData", actualResponesData);
		// 将响应结果对象添加到集合当中保存，所以创建一个集合，创建在excelUtil
		ExcelUtil.writeBackDatas.add(writeBackData);

		// "调用接口后"判断一下是否有传入脚本(这是接口调用完了后的脚本查询)
		logger.info("接口调用后数据校验");
		if (afterValidateSql != null && afterValidateSql.trim().length() > 0) {
			// 执行后的脚本也有变量也需要参数化，将执行前的脚本参数化一波afterValidateSql,因为会返回参数化后的数据所以需要接收
			afterValidateSql = VariableUtil.replaceVariables(afterValidateSql);
			// 完成脚本调用查询到要验证的字段，通过调用数据库工具类方法
			String afterValidateResult = DBCheckUtil.doQuery(afterValidateSql);
			// 将查询到的结果封装到WriteBackData类中去
			WriteBackData writeBackData2 = new WriteBackData("用例", caseId, "AfterValidateResult", afterValidateResult);
			// 将获取到的对象添加到数据回写集合中去
			ExcelUtil.writeBackDatas.add(writeBackData2);
		}
	}


	/**
	 * 用例调用统一处理类 有返回值
	 * @param apiIdFromCase  用例sheet页的接口id
	 * @param caseId   用例sheet页的编号
	 * @param params	用例sheet页的参数
	 * @param expectedResponseData  用例sheet也的预期结果
	 * @param preValidateSql	用例sheet页的执行前脚本验证
	 * @param afterValidateSql		用例sheet页的执行后脚本验证
	 * @return		返回接口响应报文(方便做动态数据的抽取)
	 */
	public  String caseBaseResult(String apiIdFromCase, String caseId, String params, String expectedResponseData,
			String preValidateSql, String afterValidateSql) {
		if (preValidateSql != null && preValidateSql.trim().length() > 0) {
			// 脚本当中也有参数，所以也需要参数化,将执行前的脚本参数化一波preValidateSql,因为会返回参数化后的数据所以需要接收
			preValidateSql = VariableUtil.replaceVariables(preValidateSql);

			// 在接口执行前调用我们的脚本查询出我们要验证的字段,通过调用数据库工具类方法
			String preValidateResult = DBCheckUtil.doQuery(preValidateSql);
			// 将查询结果保存到对象当中去创建对象
			WriteBackData writeBackData = new WriteBackData("用例", caseId, "PreValidateResult", preValidateResult);
			// 将查询结果添加到writeBackDatas中去

			ExcelUtil.writeBackDatas.add(writeBackData);

		}

		// 接口地址 通过传进来的apiId去接口信息找到url和请求方式
		String url = RestUtil.getUrlByApiId(apiIdFromCase);
		logger.info("根据接口编号【" + apiIdFromCase + "】获取到请求的url" + url);

		// 接口类型
		String type = RestUtil.getTypeByApiId(apiIdFromCase);
		logger.info("根据接口编号【" + apiIdFromCase + "】获取到请求的接口类型" + type);

		// 将变量替换成真实的数据
		params = VariableUtil.replaceVariables(params);
		logger.info("替换变量");

		// 解析json格式的数据,准备数据
		Map<String, String> paramValues = (Map<String, String>) JSONObject.parse(params);
		
		logger.info("开始调用接口");
		// 调用doservice方法完成接口调用，拿到响应报文
		String actualResponesData = HttpUnit.doService(type, url, paramValues,
				propertiesUtil.getValueByKey("api.contet.type.form"));
		// System.out.println(actualResponesData);
		// 创建一个类对响应结果和预期结果进行断言,将我们实际测试结果(actualResponesData)和期望结果(ExpectedResponseData)传入进行对比
		actualResponesData = AssertUtil.assertEquals(actualResponesData, expectedResponseData);
		// 创建一个对象将属性赋值
		WriteBackData writeBackData = new WriteBackData("用例", caseId, "ActualResponseData", actualResponesData);
		// 将响应结果对象添加到集合当中保存，所以创建一个集合，创建在excelUtil
		ExcelUtil.writeBackDatas.add(writeBackData);

		// "调用接口后"判断一下是否有传入脚本(这是接口调用完了后的脚本查询)
		logger.info("接口调用后数据校验");
		if (afterValidateSql != null && afterValidateSql.trim().length() > 0) {
			// 执行后的脚本也有变量也需要参数化，将执行前的脚本参数化一波afterValidateSql,因为会返回参数化后的数据所以需要接收
			afterValidateSql = VariableUtil.replaceVariables(afterValidateSql);
			// 完成脚本调用查询到要验证的字段，通过调用数据库工具类方法
			String afterValidateResult = DBCheckUtil.doQuery(afterValidateSql);
			// 将查询到的结果封装到WriteBackData类中去
			WriteBackData writeBackData2 = new WriteBackData("用例", caseId, "AfterValidateResult", afterValidateResult);
			// 将获取到的对象添加到数据回写集合中去
			ExcelUtil.writeBackDatas.add(writeBackData2);
		}
		return actualResponesData;
	}
	


}
