package com.qj.api.auto.cases;

import java.util.Map;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.qj.api.auto.pojo.WriteBackData;
import com.qj.api.auto.util.AssertUtil;
import com.qj.api.auto.util.DBCheckUtil;
import com.qj.api.auto.util.ExcelUtil;
import com.qj.api.auto.util.HttpUnit;
import com.qj.api.auto.util.RestUtil;

/**接口测试统一处理类
 * @author Administrator
 *
 */
//这个版本增加了数据库的校验
public class BaseProcessor {
	//因为我们所有的用例都会用到这些字段，所以直接放在基类中减少代码量
	public String[] cellNames={"CaseId","ApiId","Params","ExpectedResponseData","PreValidateSql",
			"AfterValidateSql"};
	@Test(dataProvider="datas")
	public void test1(String caseId,String apiIdFromcase,String params,String ExpectedResponseData,
			String preValidateSql,String afterValidateSql){
		//调用接口前检查，判断下是否有传进来脚本
		if (preValidateSql!=null&&preValidateSql.trim().length()>0) {
			//在接口执行前调用我们的脚本查询出我们要验证的字段,通过调用数据库工具类方法
			String preValidateResult=DBCheckUtil.doQuery(preValidateSql);
			//将查询结果保存到对象当中去创建对象
			WriteBackData writeBackData=new WriteBackData("用例", caseId, "PreValidateResult", preValidateResult);
			//将查询结果添加到writeBackDatas中去
			 ExcelUtil.writeBackDatas.add(writeBackData);
			
			
		}
		
		//接口地址 通过传进来的apiId去接口信息找到url和请求方式
		String url=RestUtil.getUrlByApiId(apiIdFromcase);
		
		//接口类型
		String type=RestUtil.getTypeByApiId(apiIdFromcase);
		//解析json格式的数据,准备数据
		Map<String, String> paramValues=(Map <String,String>)JSONObject.parse(params);
		//调用doservice方法完成接口调用，拿到响应报文
			String actualResponesData=HttpUnit.doService(type, url, paramValues);
			System.out.println(actualResponesData);
			//创建一个类对响应结果和预期结果进行断言,将我们实际测试结果(actualResponesData)和期望结果(ExpectedResponseData)传入进行对比
			actualResponesData=AssertUtil.assertEquals(actualResponesData,ExpectedResponseData);
			//创建一个对象将属性赋值
			WriteBackData writeBackData=new WriteBackData("用例", caseId, "ActualResponseData", actualResponesData);
			//将对象添加到集合当中保存，所以创建一个集合，创建在excelUtil
			ExcelUtil.writeBackDatas.add(writeBackData); 
			
			//判断一下是否有传入脚本(这是接口调用完了后的脚本查询)
			if (afterValidateSql!=null&&afterValidateSql.trim().length()>0) {
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
		ExcelUtil.batchWriteBackDatas("src/test/resources/cases_v7.xlsx");
		System.out.println("baseprocessor类中的69行"+ExcelUtil.writeBackDatas.size());
		
	}
	
}
