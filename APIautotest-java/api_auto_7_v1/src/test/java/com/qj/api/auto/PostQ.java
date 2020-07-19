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

/**post请求演示简单过程
 * @author Administrator
 *
 */
public class PostQ {
	//定义一个常量确认提交数据的类型为json
	private static final String CONTENT_TYPE_APPLICATION_JSON = "application/json;charset=utf-8";
	//定义一个常量确认提交数据类型为form表单
	private static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded;charset=utf-8";
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		//确定访问地址
		String url="http://test.lemonban.com/futureloan/mvc/api/member/register";
		//确定访问方式
		HttpPost post=new HttpPost(url);
		//准备测试数据
		String mobilephone="18825287001";
		String pwd="123456";
		//将数据添加到集合中，类型为BasicNameValuePair这种就是json格式保存的
		ArrayList<BasicNameValuePair> paramaters=new ArrayList<BasicNameValuePair>();
		paramaters.add(new BasicNameValuePair("mobilephone", mobilephone));
		paramaters.add(new BasicNameValuePair("pwd", pwd));
		//对数据进行处理
//		String jsonStr = JSONObject.toJSONString(paramaters);
//		post.setEntity(new StringEntity(jsonStr));
		//对保存的集合进行一个编码
		post.setEntity(new UrlEncodedFormEntity(paramaters,"utf-8"));
		//确定数据提交格式，并添加到请求头中
		post.addHeader("Content-Type", CONTENT_TYPE_FORM);
		//创建客户端
		HttpClient httpClient=HttpClients.createDefault();
		//发起请求
		HttpResponse httpResponse=httpClient.execute(post);
		//获取响应中的状态码
		int code=httpResponse.getStatusLine().getStatusCode();
		System.out.println("这是状态码【"+code+"】");
		//获取响应中的响应体内容
//		String body=httpResponse.getEntity().toString();
//		System.out.println("这是响应的内容【"+body+"】");
		String result=EntityUtils.toString(httpResponse.getEntity());
		System.out.println(result);
	}
}
