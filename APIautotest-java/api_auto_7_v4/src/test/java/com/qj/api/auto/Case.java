package com.qj.api.auto;

/**保存case信息
 * @author Administrator
 *我们可以把某些文件的信息封装到一个对象里面，那么这个文档的字段就是我们这个对象的属性，我们可以把文档的每一个
 *数值写到我们的对象属性中，然后用集合来保存这些对象，因为集合是有序可重复的所以可以保存，当我们需要的时候就用对象
 *调用对应的方法取出值来
 */
public class Case {
	public String CaseId;
	public String ApiId;
	public String Desc;
	public String Params;
	
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
		return "CaseId="+CaseId+",ApiId="+ApiId+",Desc="+Desc+",Params="+Params;
	}
	
}
