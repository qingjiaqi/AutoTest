package com.qj.api.auto.cases;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.qj.api.auto.pojo.WriteBackData;
import com.qj.api.auto.util.AssertUtil;
import com.qj.api.auto.util.DBCheckUtil;
import com.qj.api.auto.util.ExcelUtil;
import com.qj.api.auto.util.HttpUnit;
import com.qj.api.auto.util.RestUtil;
import com.qj.api.auto.util.VariableUtil;
import com.qj.api.auto.util.propertiesUtil;

import bsh.Variable;

/**接口测试统一处理类
 * @author Administrator
 *
 */
//将接口的数据用变量代替，解决封装对象时的类型耦合问题
public class BaseProcessor {
	//准备一个日志对象传入当前类的字节码文件
	public Logger logger=Logger.getLogger(BaseProcessor.class);
	//因为我们所有的用例都会用到这些字段，所以直接放在基类中减少代码量 分别是：用例编号，接口编号，参数，预期结果，执行测试前数据库验证，执行接口后数据库验证
	public String[] cellNames={"CaseId","ApiId","Params","ExpectedResponseData","PreValidateSql",
			"AfterValidateSql"};
	@Test(dataProvider="datas")
	public void test1(String caseId,String apiIdFromcase,String params,String ExpectedResponseData,
			String preValidateSql,String afterValidateSql){
		logger.info("调用接口前的数据验证");
		//"调用接口前"检查，判断下是否有传进来脚本
		if (preValidateSql!=null&&preValidateSql.trim().length()>0) {
			//脚本当中也有参数，所以也需要参数化,将执行前的脚本参数化一波preValidateSql,因为会返回参数化后的数据所以需要接收
			preValidateSql=VariableUtil.replaceVariables(preValidateSql);
			//在接口执行前调用我们的脚本查询出我们要验证的字段,通过调用数据库工具类方法
			String preValidateResult=DBCheckUtil.doQuery(preValidateSql);
			//将查询结果保存到对象当中去创建对象
			WriteBackData writeBackData=new WriteBackData("用例", caseId, "PreValidateResult", preValidateResult);
			//将查询结果添加到writeBackDatas中去
			 ExcelUtil.writeBackDatas.add(writeBackData);
			
			
		}
		
		//接口地址 通过传进来的apiId去接口信息找到url和请求方式
		logger.info("根据接口编号【"+apiIdFromcase+"】获取到请求的url");
		String url=RestUtil.getUrlByApiId(apiIdFromcase);
		
		
		//接口类型
		logger.info("根据接口编号【"+apiIdFromcase+"】获取到请求的接口类型");
		String type=RestUtil.getTypeByApiId(apiIdFromcase);
		
		
		//将变量替换成真实的数据
		params=VariableUtil.replaceVariables(params);
		logger.info("替换变量");
		
		//解析json格式的数据,准备数据
		Map<String, String> paramValues=(Map <String,String>)JSONObject.parse(params);
		//调用doservice方法完成接口调用，拿到响应报文
		logger.info("开始调用接口");
			String actualResponesData=HttpUnit.doService(type, url, paramValues);
//			System.out.println(actualResponesData);
			//创建一个类对响应结果和预期结果进行断言,将我们实际测试结果(actualResponesData)和期望结果(ExpectedResponseData)传入进行对比
			actualResponesData=AssertUtil.assertEquals(actualResponesData,ExpectedResponseData);
			//创建一个对象将属性赋值
			WriteBackData writeBackData=new WriteBackData("用例", caseId, "ActualResponseData", actualResponesData);
			//将响应结果对象添加到集合当中保存，所以创建一个集合，创建在excelUtil
			ExcelUtil.writeBackDatas.add(writeBackData); 
			
			//"调用接口后"判断一下是否有传入脚本(这是接口调用完了后的脚本查询)
			logger.info("接口调用后数据校验");
			if (afterValidateSql!=null&&afterValidateSql.trim().length()>0) {
				//执行后的脚本也有变量也需要参数化，将执行前的脚本参数化一波afterValidateSql,因为会返回参数化后的数据所以需要接收
				afterValidateSql=VariableUtil.replaceVariables(afterValidateSql);
				//完成脚本调用查询到要验证的字段，通过调用数据库工具类方法
				String afterValidateResult=DBCheckUtil.doQuery(afterValidateSql);
				//将查询到的结果封装到WriteBackData类中去
				WriteBackData writeBackData2=new WriteBackData("用例", caseId,"AfterValidateResult", afterValidateResult);
				//将获取到的对象添加到数据回写集合中去
				ExcelUtil.writeBackDatas.add(writeBackData2);
			}
	}
	@AfterSuite
	//将所有结果批量写入到我们的文档中去
	public void batchWriteBackDatas(){
		ExcelUtil.batchWriteBackDatas(propertiesUtil.getExcelPath());
		
//			
		
	}
	
}
