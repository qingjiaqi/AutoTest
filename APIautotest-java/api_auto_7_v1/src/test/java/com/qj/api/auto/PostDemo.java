package com.qj.api.auto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.DefaultEditorKit.PasteAction;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class PostDemo {

	public static void main(String[] args) throws ClientProtocolException, IOException {
//		3.��д�ӿڵ�ַ
		String url="http://test.lemonban.com/futureloan/mvc/api/member/register";
//		4.ָ���ӿ�����ʽ post ����httpPost��
		HttpPost httpPost=new HttpPost(url);
		
//		5.׼����������
//		String  mobilephone="18825287005";
//		String pwd="123456";
//		String mobile_type="f111";
//		String meid="12344";
//		String userAccount="test1";
//        String pwd="Deta1234";
		//���������������
		List<BasicNameValuePair> parameters=new ArrayList<BasicNameValuePair>();
		//��Ӳ���
		parameters.add(new BasicNameValuePair("mobilephone", "18825287001"));
		parameters.add(new BasicNameValuePair("pwd", "123456"));
//		parameters.add(new BasicNameValuePair("mobile_type", "android"));
//		parameters.add(new BasicNameValuePair("meid", "FJH5T18819022280"));
		
		
		//��������ֵ��ӵ��������У�setsetEntity��һ���ӿ���Ҫһ���������UrlEncodedFormEntity(parameters,"utf-8")��ʵ��
		//parameters��Ҫ��һ�����϶���
		httpPost.setEntity(new UrlEncodedFormEntity(parameters,"utf-8"));
//		7.�������󣬻�ȡ�ӿ���Ӧ��Ϣ��״̬�룬��Ӧ���ģ�ĳЩ�������Ӧ���ݣ�
		//������ǰ��Ҫ׼���ͻ��ˣ�����HttpClients.createDefault()���߷���
		HttpClient httpClient=HttpClients.createDefault();
		//������ͷ����һ����������Ϊ
		addRequestHeadBeforRequest(httpPost);
		//���ͽӿ�����
		HttpResponse httpResponse=httpClient.execute(httpPost);
		//״̬��
		int code=httpResponse.getStatusLine().getStatusCode();
		System.out.println(code);
		//��Ӧ����,httpResponse.getEntity()��ȡ����Ӧ����,EntityUtils.toString()������ת��Ϊstring���͵�
		String result=EntityUtils.toString(httpResponse.getEntity());
		System.out.println(result);
	}
	/**���������������������ͷ�������Ϣ��
	 * @param httpPost
	 */
	private static void addRequestHeadBeforRequest(HttpRequest httpRequest) {
		//������ͷ������һ�������ύ����
		httpRequest.addHeader("Content-Type", "application/json");
		
	}

}
