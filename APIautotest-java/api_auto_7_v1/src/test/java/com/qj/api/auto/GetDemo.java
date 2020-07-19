package com.qj.api.auto;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GetDemo {
	public static void main(String[] args) throws Exception {
		//填写接口地址
//		String url="http://47.107.167.1:8585/appmall/api/app/home/info";
//		String url="http://rap2api.taobao.org/app/mock/116450/api/app/home/info";
		String url="http://rap2api.taobao.org/app/mock/116450/api/app/search/result";
//		String url="https://www.baidu.com/";
		//准备测试数据
		url=url+"?keyword=苹果&lng=114.272&lat=22.599137";
		//将URL进行编码，可以不要也行
		URLEncoder.encode(url, "UTF-8");
		//确定请求方式 get
		HttpGet httpGet=new HttpGet(url);
		System.out.println(url);
		//发起请求
		//先要创建一个客户端httpclient
		HttpClient httpClient=HttpClients.createDefault();
		HttpResponse response=httpClient.execute(httpGet);
		//获取到code
		int code=response.getStatusLine().getStatusCode();
		//获取到响应报文
		HttpEntity  entity=response.getEntity();
		//将响应报文转换成string类型
	String resurt	=EntityUtils.toString(entity);
	System.out.println(resurt);
	}
}
