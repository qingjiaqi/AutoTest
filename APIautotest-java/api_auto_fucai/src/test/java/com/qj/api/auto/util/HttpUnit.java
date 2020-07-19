package com.qj.api.auto.util;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import net.sf.json.JSONObject;



/**接口调用工具类
 * @author Administrator
 *接口调用完后的返回数据在这里面
 */
public class HttpUnit {
	public static String result="";
	//创建一个日志对象
	public static Logger logger=Logger.getLogger(HttpUnit.class);
	//创建一个map容器来装我们的cookie,为了能够共享声明为静态
	public static Map<String, String> cookies = new HashMap<String, String>();
	/**以post方式处理接口调用
	 * @param url 接口地址
	 * @param params 参数名和参数值 map类型
	 * 
	 * @return 响应string类型
	 */
	public static String doPost(String url,Map<String, String> params){
		logger.info("接口请求地址"+url);
		//4.指定接口请求方式 post 利用httpPost类
		HttpPost httpPost=new HttpPost(url);
		
		//集合用来保存参数
//		List<BasicNameValuePair> parameters=new ArrayList<BasicNameValuePair>();
		//创建一个JsonObject对象容器
		JsonObject jsonObject = new JsonObject();
		//循环map，先获取到所有的key
		Set<String> keys= params.keySet();
		for (String name: keys) {
			String value=params.get(name);
			
			//添加参数
//			parameters.add(new BasicNameValuePair(name, value));
			//以json格式添加，因为后台要解析json格式的
			jsonObject.addProperty(name, value);
			
		}

//		String result="";
		try {
			//将参数和值添加到请求体中，setsetEntity是一个接口需要一个子类对象UrlEncodedFormEntity(parameters,"utf-8")来实现
			//parameters需要传一个集合对象
//			httpPost.setEntity(new UrlEncodedFormEntity(parameters,"utf-8"));
			httpPost.setEntity(new StringEntity(jsonObject.toString(),"utf-8"));
			logger.info("请求的参数"+jsonObject.toString());
//		7.发起请求，获取接口响应信息（状态码，响应报文，某些特殊的响应数据）
			//发请求前需要准备客户端，利用HttpClients.createDefault()工具方法
			HttpClient httpClient=HttpClients.createDefault();
			//创建一个方法用来将我们Jsessionid添加到我们的请求头中去
			addCokieInrequestHeaderBeforeRequest(httpPost);
			
			//创建一个方法用来将我们tokenId添加到我们的请求头中去
			addTokenIdInrequestHeaderBeforeRequest(httpPost);
			//创建一个方法将请求参数类型放入到请求头中去
			addRequestHeadBeforRequest(httpPost);
			
			//发送接口请求
			HttpResponse httpResponse=httpClient.execute(httpPost);
			//创建一个方法来获取并保存我们有响应头中cookie-set有jsessionid的cookie
			getAndStoreCookiesFromResponseHeader(httpResponse);
			
			int code=httpResponse.getStatusLine().getStatusCode();//获取状态码
			//响应报文,httpResponse.getEntity()获取到响应报文,EntityUtils.toString()将报文转换为string类型的
			result=EntityUtils.toString(httpResponse.getEntity());
			//创建一个方法用来获取响应体里面的token
			getAndStoreTokenFromResponseHead(result);
//			System.out.println("httpUnit57:code=["+code+"],result=["+result+"]");
			logger.info("接口响应报文code="+"["+code+"],result=["+result+"]");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(result);
		return result;
	}



	/**这是用来添加token的方法，放在请求执行之前
	 * @param httpRequest
	 */
	private static void addTokenIdInrequestHeaderBeforeRequest(HttpRequest httpRequest) {
				//调用方法获取到响应中的token值
				String tokenValue=getAndStoreTokenFromResponseHead(result);
				logger.info("这是添加到请求里面去的token值"+tokenValue);
				if (tokenValue!=null) {
					//将获取到的token值添加到请求头中
					httpRequest.addHeader("access_token", tokenValue);
				}
	}



	/**这个方法是用来设置请求头里面的信息的
	 * @param httpPost
	 */
	private static void addRequestHeadBeforRequest(HttpRequest httpRequest) {
		//在请求头中设置一个参数提交类型
		httpRequest.addHeader("Content-Type", "application/json");
		
	}



	/**get请求方式处理接口调用
	 * @param url 接口地址
	 * @param params 参数名和参数值
	 * @return 响应报文 类型为string类型
	 */
	public static String getDemo(String url,Map<String, String> params){	
		logger.info("接口请求地址"+url);
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

		//确定请求方式 get
		
		logger.info("接口请求地址及参数"+url);
		
		String result="";
		try {
			
			//将URL格式设置一下
			URLEncoder.encode(url, "UTF-8");
			//确定请求的方式
			HttpGet httpGet=new HttpGet(url);
			//往请求头加入登录鉴权信息cookie信息
			addCokieInrequestHeaderBeforeRequest(httpGet);
			//创建一个方法用来将我们tokenId添加到我们的请求头中去
			addTokenIdInrequestHeaderBeforeRequest(httpGet);

			HttpClient httpClient=HttpClients.createDefault();//先要创建一个客户端httpclient
			//发起请求
			HttpResponse response = httpClient.execute(httpGet);
			//这一句是用来获取sessionid的
			getAndStoreCookiesFromResponseHeader(response);
//			//这一句用来获取tokenid
//			getAndStoreTokenFromResponseHead(response);
			//获取到code
			int code=response.getStatusLine().getStatusCode();
			//获取到响应报文
			HttpEntity  entity=response.getEntity();
			//将响应报文转换成string类型
			result	=EntityUtils.toString(entity);
			//创建一个方法用来获取响应体里面的token
			getAndStoreTokenFromResponseHead(result);
			logger.info("接口响应报文code="+"["+code+"],result=["+result+"]");
//			System.out.println("httpUnit98:code=["+code+"],result=["+result+"]");
//			System.out.println("这是httpunit里面的97行的输出"+resurt);

		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**从响应报文中获取到access-token的值
	 * @param result 请求后得到到的响应报文 string类型
	 * @return 然后出access-token的值
	 */
	private static String getAndStoreTokenFromResponseHead(String result) {	
		
		String accessTokenValue="";
		if (result.contains("data")) {
			
			// 先将响应返回的string类型的数据解析成jsonobject类型,注意不要导错包
			// import net.sf.json.JSONObject;
			JSONObject jsonObject=JSONObject.fromObject(result);
			//通过键拿出对应的值,注意大小写不然会获取不到
			String dataValue=jsonObject.getString("data");
			if (dataValue.contains("access_token")) {
				logger.info("从这里面获取到token"+result);
				// 因为dataValue取出的值还是个json所以需要重复解析下
				JSONObject tokenJsonObject=jsonObject.fromObject(dataValue);
				//从tokenJsonObject中通过key取出对应的值
				accessTokenValue=tokenJsonObject.getString("access_token");
				
			}
			
		}
		return accessTokenValue;			
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
	
	/**这个方法是用来讲我们的会话sessionid添加到我们请求头中去,使用我们接口有权限进行访问
	 * @param httpRequest 请求类型：因为我们的请求有多种所以要定义这种类型来接收
	 */
	private static void addCokieInrequestHeaderBeforeRequest(HttpRequest httpRequest) {
		//从我们的保存cookie的map里面取出我们的sessionid数据
		String jsessionidCokie=cookies.get("JSESSIONID");
		if(jsessionidCokie!=null){
			//不为空的时候将我们的会话添加到我们的头信息里面去
			httpRequest.addHeader("Cookie", jsessionidCokie);;
		}
		
	}

	/**取出我们含有JSESSIONID的会话id的cookie用来鉴权使用
	 * @param httpResponse 响应返回的数据
	 */
	private static void getAndStoreCookiesFromResponseHeader(HttpResponse httpResponse) {
		// 从响应头中取出名字为Set-Cookie的响应头对象，注意大小写不然会获取不到
		Header setCookieHeader=httpResponse.getFirstHeader("Set-Cookie");
		if(setCookieHeader!=null){//如果setCookieHeader不为空获取到里面的值
			//获取到含有Set-Cookie响应头的值
			String cookiePairString=setCookieHeader.getValue();
			//判断一下如果取到的键值对不为空，并且去除空格后长度大于0，将里面的值进行分割
			if (cookiePairString!=null&&cookiePairString.trim().length()>0) {
				//因为我们的cookie里面的值是以;隔开的所以用;进行切割，并用一个数组进行接收
			String [] cookiePairs=cookiePairString.split(";");
			if (cookiePairs!=null) {//判断数组是否为空如果不为空循环数组
				//循环数组找出含有jsession的cookie
				for(String cookiePair:cookiePairs){
					//如果包含JSESSIONID则意味着响应头有会话ID这个数据
					if (cookiePair.contains("JSESSIONID")) {
						//将我们的会话ID保存到我们的cookiesmap里面
						cookies.put("JSESSIONID", cookiePair);
					}
				}
				
			}
				
			}	
		}
	}
}
