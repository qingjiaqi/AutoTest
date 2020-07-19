package com.lemon.api.auto.caces;

/**保存case的信息
 * @author Administrator
 *
 */
public class Case {

	private String CaseId;
	private String ApiId;
	private String Desc;
	private String Params;
	private String ExpectedResponseData;
	private String ActualResponseData;
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
	public String getCaseId() {
		return CaseId;
	}
	public void setCaseId(String caseId) {
		CaseId = caseId;
	}
	public String getApiId() {
		return ApiId;
	}
	public void setApiId(String apiId) {
		ApiId = apiId;
	}
	public String getDesc() {
		return Desc;
	}
	public void setDesc(String desc) {
		Desc = desc;
	}
	public String getParams() {
		return Params;
	}
	public void setParams(String params) {
		Params = params;
	}
	@Override
	public String toString() {
		return "Case [CaseId=" + CaseId + ", ApiId=" + ApiId + ", Desc=" + Desc + ", Params=" + Params
				+ ", ExpectedResponseData=" + ExpectedResponseData + ", ActualResponseData=" + ActualResponseData + "]";
	}
	
	
}
