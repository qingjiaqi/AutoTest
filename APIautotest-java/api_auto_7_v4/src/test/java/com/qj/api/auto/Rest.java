package com.qj.api.auto;

/**Rest类是用来封装接口信息sheet页的数据的
 * @author Administrator
 *
 */
public class Rest {
	public String ApiId;
	public String ApiName;
	public String Type;
	public String Url;
	
	public String getApiId() {
		return ApiId;
	}
	public void setApiId(String apiId) {
		ApiId = apiId;
	}
	public String getApiName() {
		return ApiName;
	}
	public void setApiName(String apiName) {
		ApiName = apiName;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	@Override
	public String toString() {
		return "ApiId="+ApiId+",ApiName="+ApiName+",Type="+Type+",Url="+Url;
	}
}
