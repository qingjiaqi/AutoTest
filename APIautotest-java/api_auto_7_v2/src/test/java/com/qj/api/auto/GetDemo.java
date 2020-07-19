package com.qj.api.auto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GetDemo {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		//填写接口地址
		String url="http://test.lemonban.com/futureloan/mvc/api/member/register";
		
		//准备测试数据
		String  mobilephone="13211111111";
		String pwd="123456";
		
		Map<String, String> params=new HashMap<String, String>();
		params.put("mobilephone", mobilephone);
		params.put("pwd", pwd);
		String resPonse=HttpUnit.getDemo(url, params);
		System.out.println(resPonse);
	}
	
	
}
