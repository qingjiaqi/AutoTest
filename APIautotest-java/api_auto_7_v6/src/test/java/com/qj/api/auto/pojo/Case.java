package com.qj.api.auto.pojo;

/**����case��Ϣ
 * @author Administrator
 *���ǿ��԰�ĳЩ�ļ�����Ϣ��װ��һ���������棬��ô����ĵ����ֶξ������������������ԣ����ǿ��԰��ĵ���ÿһ��
 *��ֵд�����ǵĶ��������У�Ȼ���ü�����������Щ������Ϊ������������ظ������Կ��Ա��棬��������Ҫ��ʱ����ö���
 *���ö�Ӧ�ķ���ȡ��ֵ��
 *CaseId :�������
 *ApiId �ӿڱ��
 *Desc ��������
 *Params ����
 *ExpectedResponseData Ԥ�ڽ��
 *ActualResponseData ʵ�ʽ��
 */
public class Case {
	public String CaseId;
	public String ApiId;
	public String Desc;
	public String Params; 
	public String ExpectedResponseData;
	public String ActualResponseData;
	
	
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
		// ��дtostring����
		return "CaseId="+CaseId+",ApiId="+ApiId+",Desc="+Desc+",Params="+Params+
				",ExpectedResponseData="+ExpectedResponseData+",ActualResponseData="+ActualResponseData;
				
	}
	
}
