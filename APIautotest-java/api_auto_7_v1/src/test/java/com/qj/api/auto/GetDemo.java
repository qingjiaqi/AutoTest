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
		//��д�ӿڵ�ַ
//		String url="http://47.107.167.1:8585/appmall/api/app/home/info";
//		String url="http://rap2api.taobao.org/app/mock/116450/api/app/home/info";
		String url="http://rap2api.taobao.org/app/mock/116450/api/app/search/result";
//		String url="https://www.baidu.com/";
		//׼����������
		url=url+"?keyword=ƻ��&lng=114.272&lat=22.599137";
		//��URL���б��룬���Բ�ҪҲ��
		URLEncoder.encode(url, "UTF-8");
		//ȷ������ʽ get
		HttpGet httpGet=new HttpGet(url);
		System.out.println(url);
		//��������
		//��Ҫ����һ���ͻ���httpclient
		HttpClient httpClient=HttpClients.createDefault();
		HttpResponse response=httpClient.execute(httpGet);
		//��ȡ��code
		int code=response.getStatusLine().getStatusCode();
		//��ȡ����Ӧ����
		HttpEntity  entity=response.getEntity();
		//����Ӧ����ת����string����
	String resurt	=EntityUtils.toString(entity);
	System.out.println(resurt);
	}
}
