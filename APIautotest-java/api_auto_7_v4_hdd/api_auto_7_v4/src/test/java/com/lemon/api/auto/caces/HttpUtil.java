package com.lemon.api.auto.caces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

//接口调用工具类
public class HttpUtil {

	//保存会话ID
	public static Map<String, String> cookies = new HashMap<String, String>();
	//处理post方式接口调用
	public static String doPost(String url,Map<String, String> params) {
		// 2.指定接口方式：post
		HttpPost post = new HttpPost(url);
		List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
		// 3.遍历出map中所有的参数名
		Set<String> keys = params.keySet();
		//通过循环将参数保存到list集合中
		for (String name : keys) {
			String value = params.get(name);
			parameters.add(new BasicNameValuePair(name, value));
		}
		// 3.2以表单格式传送数据
		String result = "";
		try {
			post.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));
			// 4.发送请求，获取接口响应信息（状态码，响应报文，响应数据）
			HttpClient client = HttpClients.createDefault();
			addCookieInRequestHeaderBeforeRequest(post);
			HttpResponse response = client.execute(post);
			getAndStoreCookiesFromResponseHeader(response);
			// 状态码
			int code = response.getStatusLine().getStatusCode();
			System.out.println(code);
			// 响应报文
			result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return result;
	}
	
	//处理get方式接口调用
		public static String doGet(String url,Map<String, String> params) {
			Set<String> keys = params.keySet();
			//定义一个标志位
			int mark = 1;
			for (String name : keys) {
				if(mark==1) {
					url += ("?"+name+"="+params.get(name));
				}else {
					url += ("&"+name+"="+params.get(name));
				}
				mark++;
			}
			System.out.println(url);
			// 指定接口提交方式:get
			HttpGet get = new HttpGet(url);
			// 发送请求，获取接口响应信息（状态码，响应报文，响应数据）
			HttpClient client = HttpClients.createDefault();
			HttpResponse response;
			String result = "";
			try {
				addCookieInRequestHeaderBeforeRequest(get);
				response = client.execute(get);
				getAndStoreCookiesFromResponseHeader(response);
				// 状态码
				int code = response.getStatusLine().getStatusCode();
				System.out.println(code);
				// 响应报文
				result = EntityUtils.toString(response.getEntity());
				System.out.println(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
	
	/**在发起请求之前在请求头添加cookie
	 * @param post
	 */
	private static void addCookieInRequestHeaderBeforeRequest(HttpRequest request) {
		String jsessionIdCookie = cookies.get("JSESSIONID");
		if(jsessionIdCookie!=null){
			request.addHeader("Cookie", jsessionIdCookie);
		}
	}

	/**获取并保存自响应头的cookies
	 * @param response
	 */
	private static void getAndStoreCookiesFromResponseHeader(HttpResponse response) {
		//从响应头取出名字为“Set-Cookie”的响应头
		Header setCookieHeader = response.getFirstHeader("Set-Cookie");
		if(setCookieHeader!=null){
			//取出此响应头的值
			String cookiePairsString = setCookieHeader.getValue();
			if(cookiePairsString!=null&&cookiePairsString.trim().length()>0){
				//以";"来切割
				String [] cookiePairs = cookiePairsString.split(";");
				if(cookiePairs!=null){
					for (String cookiePair : cookiePairs) {
						//如果包含了JSESSIONID,则表示响应头里有会话ID这个数据
						if(cookiePair.contains("JSESSIONID")){
							System.out.println(cookiePair);
							cookies.put("JSESSIONID", cookiePair);
						}
					}
				}
			}
		}
		
	}
	
	public static String doService(String url,String type,Map<String, String> params) {
		String result = "";
		if ("post".equalsIgnoreCase(type)) {
			result = HttpUtil.doPost(url, params);
		}else if("get".equalsIgnoreCase(type)){
			result = HttpUtil.doGet(url, params);
		}
		return result;
	}
}
