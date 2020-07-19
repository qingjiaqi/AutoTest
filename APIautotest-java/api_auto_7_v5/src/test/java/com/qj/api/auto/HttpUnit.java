package com.qj.api.auto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**接口调用工具类
 * @author Administrator
 *
 */
public class HttpUnit {
	/**以post方式处理接口调用
	 * @param url 接口地址
	 * @param params 参数名和参数值 map类型
	 * 
	 * @return 响应string类型
	 */
	public static String doPost(String url,Map<String, String> params){
		
		//4.指定接口请求方式 post 利用httpPost类
		HttpPost httpPost=new HttpPost(url);
		
		//集合用来保存参数
		List<BasicNameValuePair> parameters=new ArrayList<BasicNameValuePair>();
		//循环map，先获取到所有的key
		Set<String> keys= params.keySet();
		for (String name: keys) {
			String value=params.get(name);
			//添加参数
			parameters.add(new BasicNameValuePair(name, value));
			
		}
//		System.out.println(parameters);
		String result="";
		try {
			//将参数和值添加到请求体中，setsetEntity是一个接口需要一个子类对象UrlEncodedFormEntity(parameters,"utf-8")来实现
			//parameters需要传一个集合对象
			httpPost.setEntity(new UrlEncodedFormEntity(parameters,"utf-8"));
//		7.发起请求，获取接口响应信息（状态码，响应报文，某些特殊的响应数据）
			//发请求前需要准备客户端，利用HttpClients.createDefault()工具方法
			HttpClient httpClient=HttpClients.createDefault();
			HttpResponse httpResponse=httpClient.execute(httpPost);//发送接口请求
			int code=httpResponse.getStatusLine().getStatusCode();//获取状态码
			//响应报文,httpResponse.getEntity()获取到响应报文,EntityUtils.toString()将报文转换为string类型的
			result=EntityUtils.toString(httpResponse.getEntity());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(result);
		return result;
	}
	
	/**get请求方式处理接口调用
	 * @param url 接口地址
	 * @param params 参数名和参数值
	 * @return 响应报文 类型为string类型
	 */
	public static String getDemo(String url,Map<String, String> params){	
		//循环map，先获取到所有的key
		Set<String> keys =params.keySet();
		int mak=1;//这是一个标识位
		for (String name : keys) {
			if (mak==1) {
				url+=("?"+name+"="+params.get(name));
			}else {
				url+=("&"+name+"="+params.get(name));
			}
			mak++;
		}
//		System.out.println(url);
//		url=url+("?"+"mobilephone="+mobilephone+"&pwd="+pwd);
		//确定请求方式 get
		HttpGet httpGet=new HttpGet(url);
		HttpClient httpClient=HttpClients.createDefault();//先要创建一个客户端httpclient
		String resurt="";
		try {
			HttpResponse response = httpClient.execute(httpGet);//发起请求
			//获取到code
			int code=response.getStatusLine().getStatusCode();
			//获取到响应报文
			HttpEntity  entity=response.getEntity();
			//将响应报文转换成string类型
			resurt	=EntityUtils.toString(entity);
//			System.out.println("这是httpunit里面的97行的输出"+resurt);
//			System.out.println(resurt);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resurt;
	}
	
	/**这是用来请求接口的可以支持get和post请求;
	 * @param type 请求类型
	 * @param url	接口地址
	 * @param paramValues 上传参数
	 * @return 返回响应报文 类型为string
	 */
	public static String doService(String type,String url, Map<String, String> paramValues) {
		String result="";
		if ("post".equalsIgnoreCase(type)) {
			result=HttpUnit.doPost(url, paramValues);
		}else if ("get".equalsIgnoreCase(type)) {
			result=HttpUnit.getDemo(url, paramValues);
		}
		return result;
	}
	
}
