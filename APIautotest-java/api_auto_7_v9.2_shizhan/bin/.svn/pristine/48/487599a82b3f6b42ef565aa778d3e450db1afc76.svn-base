package com.qj.api.auto.pojo;

/**保存case信息
 * @author Administrator
 *我们可以把某些文件的信息封装到一个对象里面，那么这个文档的字段就是我们这个对象的属性，我们可以把文档的每一个
 *数值写到我们的对象属性中，然后用集合来保存这些对象，因为集合是有序可重复的所以可以保存，当我们需要的时候就用对象
 *调用对应的方法取出值来
 *CaseId :用例编号
 *ApiId 接口编号
 *Desc 用例描述
 *Params 参数
 *ExpectedResponseData; 预期结果
 *ActualResponseData;响应结果
 *PreValidateSql(接口执行前的脚本验证)
 *PreValidateResult(接口执行前数据库验证结果)
 *AfterValidateSql(接口执行后的脚本验证)
 *AfterValidateResult(接口执行后数据库验证结果)
 *DueryDesc(脚本查询说明)
 */
public class Case {
	public String CaseId;
	public String ApiId;
	public String Desc;
	public String Params; 
	public String ExpectedResponseData;
	public String ActualResponseData;
	public String PreValidateSql;
	public String PreValidateResult;
	public String AfterValidateSql;
	public String AfterValidateResult;
	public String QueryDesc;
	
	public String getPreValidateSql() {
		return PreValidateSql;
	}


	public String getQueryDesc() {
		return QueryDesc;
	}


	public void setQueryDesc(String queryDesc) {
		QueryDesc = queryDesc;
	}


	public void setPreValidateSql(String preValidateSql) {
		PreValidateSql = preValidateSql;
	}

	public String getPreValidateResult() {
		return PreValidateResult;
	}

	public void setPreValidateResult(String preValidateResult) {
		PreValidateResult = preValidateResult;
	}

	public String getAfterValidateSql() {
		return AfterValidateSql;
	}

	public void setAfterValidateSql(String afterValidateSql) {
		AfterValidateSql = afterValidateSql;
	}

	public String getAfterValidateResult() {
		return AfterValidateResult;
	}

	public void setAfterValidateResult(String afterValidateResult) {
		AfterValidateResult = afterValidateResult;
	}


	
	
	public String getExpectedResponseData() {
		return ExpectedResponseData;
	}

	public void setExpectedResponseData(String expectedResponseData) {
		ExpectedResponseData = expectedResponseData;
	}

	public String getActualResponseData() {
		return ActualResponseData;
	}

	public void setActualResponseData(String actualResponseData) {
		ActualResponseData = actualResponseData;
	}

	public void setCaseId(String CaseId) {
		this.CaseId=CaseId;
	}
	
	public String getCaseId(){
		return CaseId;
	}
	
	public void setApiId(String ApiId){
		this.ApiId=ApiId;
	}
	
	public String getApiId() {
		return ApiId;
	}
	
	public void setDesc(String Desc){
		this.Desc=Desc;
	}
	
	public String getDesc(){
		return Desc;
	}
	
	public void setParams(String Params){
		this.Params=Params;
	}
	
	public String getParams(){
		return Params;
	}
	
	@Override
	public String toString() {
		// 重写tostring方法
		return "CaseId="+CaseId+",ApiId="+ApiId+",Desc="+Desc+",Params="+Params+
				",ExpectedResponseData="+ExpectedResponseData+",ActualResponseData="+ActualResponseData+",QueryDesc="+QueryDesc;
				
	}
	
}
