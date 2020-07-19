package com.qj.api.auto;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**post������ʾ�򵥹���
 * @author Administrator
 *
 */
public class PostQ {
	//����һ������ȷ���ύ���ݵ�����Ϊjson
	private static final String CONTENT_TYPE_APPLICATION_JSON = "application/json;charset=utf-8";
	//����һ������ȷ���ύ��������Ϊform��
	private static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded;charset=utf-8";
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		//ȷ�����ʵ�ַ
		String url="http://test.lemonban.com/futureloan/mvc/api/member/register";
		//ȷ�����ʷ�ʽ
		HttpPost post=new HttpPost(url);
		//׼����������
		String mobilephone="18825287001";
		String pwd="123456";
		//��������ӵ������У�����ΪBasicNameValuePair���־���json��ʽ�����
		ArrayList<BasicNameValuePair> paramaters=new ArrayList<BasicNameValuePair>();
		paramaters.add(new BasicNameValuePair("mobilephone", mobilephone));
		paramaters.add(new BasicNameValuePair("pwd", pwd));
		//�����ݽ��д���
//		String jsonStr = JSONObject.toJSONString(paramaters);
//		post.setEntity(new StringEntity(jsonStr));
		//�Ա���ļ��Ͻ���һ������
		post.setEntity(new UrlEncodedFormEntity(paramaters,"utf-8"));
		//ȷ�������ύ��ʽ������ӵ�����ͷ��
		post.addHeader("Content-Type", CONTENT_TYPE_FORM);
		//�����ͻ���
		HttpClient httpClient=HttpClients.createDefault();
		//��������
		HttpResponse httpResponse=httpClient.execute(post);
		//��ȡ��Ӧ�е�״̬��
		int code=httpResponse.getStatusLine().getStatusCode();
		System.out.println("����״̬�롾"+code+"��");
		//��ȡ��Ӧ�е���Ӧ������
//		String body=httpResponse.getEntity().toString();
//		System.out.println("������Ӧ�����ݡ�"+body+"��");
		String result=EntityUtils.toString(httpResponse.getEntity());
		System.out.println(result);
	}
}
