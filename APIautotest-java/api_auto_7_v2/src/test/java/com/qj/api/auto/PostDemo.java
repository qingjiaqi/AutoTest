package com.qj.api.auto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class PostDemo {
	
	public static void main(String[] args) {
		String url="http://119.23.241.154:8080/futureloan/mvc/api/member/register?";//请求地址
		//准备测试数据
		String  mobilephone="13211111111";
		String pwd="123456";
		Map<String, String> hasMap=new HashMap<String, String>();
		hasMap.put("mobilephone", mobilephone);
		hasMap.put("pwd", pwd);
		String resPonse= HttpUnit.doPost(url, hasMap);
		System.out.println(resPonse);
	}
	
	
	}


