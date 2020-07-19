package com.qj.api.auto.pojo;

/**
 * 
 * 
 * @author Administrator 
 * Rest类是用来封装接口信息sheet页的数据的
 */
public class Rest {
	/**
	 * 接口sheet页中的接口id
	 */
	public String ApiId;
	/**
	 * 接口sheet页中的接口名字
	 */
	public String ApiName;

	/**
	 * 接口sheet页中的接口类型
	 */
	public String Type;

	/**
	 * 接口sheet页中的接口url
	 */
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
		return "ApiId=" + ApiId + ",ApiName=" + ApiName + ",Type=" + Type + ",Url=" + Url;
	}
}
