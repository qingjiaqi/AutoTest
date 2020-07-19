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
//		3.填写接口地址
		String url="http://test.lemonban.com/futureloan/mvc/api/member/register";
//		4.指定接口请求方式 post 利用httpPost类
		HttpPost httpPost=new HttpPost(url);
		
//		5.准备测试数据
//		String  mobilephone="18825287005";
//		String pwd="123456";
//		String mobile_type="f111";
//		String meid="12344";
//		String userAccount="test1";
//        String pwd="Deta1234";
		//集合用来保存参数
		List<BasicNameValuePair> parameters=new ArrayList<BasicNameValuePair>();
		//添加参数
		parameters.add(new BasicNameValuePair("mobilephone", "18825287001"));
		parameters.add(new BasicNameValuePair("pwd", "123456"));
//		parameters.add(new BasicNameValuePair("mobile_type", "android"));
//		parameters.add(new BasicNameValuePair("meid", "FJH5T18819022280"));
		
		
		//将参数和值添加到请求体中，setsetEntity是一个接口需要一个子类对象UrlEncodedFormEntity(parameters,"utf-8")来实现
		//parameters需要传一个集合对象
		httpPost.setEntity(new UrlEncodedFormEntity(parameters,"utf-8"));
//		7.发起请求，获取接口响应信息（状态码，响应报文，某些特殊的响应数据）
		//发请求前需要准备客户端，利用HttpClients.createDefault()工具方法
		HttpClient httpClient=HttpClients.createDefault();
		//给请求头设置一个内容类型为
		addRequestHeadBeforRequest(httpPost);
		//发送接口请求
		HttpResponse httpResponse=httpClient.execute(httpPost);
		//状态码
		int code=httpResponse.getStatusLine().getStatusCode();
		System.out.println(code);
		//响应报文,httpResponse.getEntity()获取到响应报文,EntityUtils.toString()将报文转换为string类型的
		String result=EntityUtils.toString(httpResponse.getEntity());
		System.out.println(result);
	}
	/**这个方法是用来设置请求头里面的信息的
	 * @param httpPost
	 */
	private static void addRequestHeadBeforRequest(HttpRequest httpRequest) {
		//在请求头中设置一个参数提交类型
		httpRequest.addHeader("Content-Type", "application/json");
		
	}

}
