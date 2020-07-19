package com.qj.api.auto;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;

import net.sf.json.JSONObject;

public class PostDemokfucai {
	public static void main(String[] args) throws ClientProtocolException, IOException {
//		3.填写接口地址
//        String url="http://192.168.1.168:8888/ayface/servlet/ssoUser/login?userAccount=test1&password=Deta1234";
		//福彩接口地址
		String url="http://rap2api.taobao.org/app/mock/116450/api/app/search/result";
//    4.指定接口请求方式 post 利用httpPost类
        HttpPost httpPost=new HttpPost(url);
        //创建一个JsonObject对象容器 lng=22.728172&lat=114.273917&keyword="胡"
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("lng", "22.728172");
        jsonObject.addProperty("lat", "114.273917");
        jsonObject.addProperty("keyword", "胡");
//        jsonObject.addProperty("meid", "FJH5T18819022280");
        //将参数和值添加到请求体中，setsetEntity是一个接口需要一个子类对象UrlEncodedFormEntity(parameters,"utf-8")来实现
        //parameters需要传一个集合对象
        httpPost.setEntity(new StringEntity(jsonObject.toString(),"utf-8"));
//    7.发起请求，获取接口响应信息（状态码，响应报文，某些特殊的响应数据）
        //发请求前需要准备客户端，利用HttpClients.createDefault()工具方法
        HttpClient httpClient=HttpClients.createDefault();
        //给请求头设置一个内容类型为
//        addRequestHeadBeforRequest(httpPost);
        httpPost.addHeader("Content-Type", "application/json");
        //发送接口请求
        HttpResponse httpResponse=httpClient.execute(httpPost);
       
        //状态码
        int code=httpResponse.getStatusLine().getStatusCode();
        System.out.println(code);
        //响应报文,httpResponse.getEntity()获取到响应报文,EntityUtils.toString()将报文转换为string类型的
        String result=EntityUtils.toString(httpResponse.getEntity());
        System.out.println(result);
//        getAndStoreTokenFromResponseHead(result);
        
      }
	

	
	
	/**这个方法是用来设置请求头里面的信息的
	 * @param httpPost
	 */
	private static void addRequestHeadBeforRequest(HttpRequest httpRequest) {
		//在请求头中设置一个参数提交类型
		httpRequest.addHeader("Content-Type", "application/json");
//		httpRequest.addHeader("Cookie","JSESSIONID=951FBCFB4D377B8C458B57CD3D2FEBF5");
		
		
		
	}
	
	/**这个方法是用来获取APP接口响应体中的access_token值的
	 * @param result 请求接口后返回的响应体包含access_token值的
	 */
	private static void getAndStoreTokenFromResponseHead(String result) {	
		JSONObject jsonObject = JSONObject.fromObject(result);
		String data=jsonObject.getString("data");
		JSONObject jsonObject1 = JSONObject.fromObject(data);
		System.out.println(jsonObject1.getString("access_token"));
	}

	
	private static String getAndStoreTokenFromResponseHead(HttpResponse httpResponse) {
		// 从响应头中取出名字为token的响应头对象，注意大小写不然会获取不到
//				Header setTokenHeader=httpResponse.getFirstHeader("access_token");
			Header[] setTokenHeader=httpResponse.getHeaders("access_token");
				String tokenid=null;
				if(setTokenHeader!=null){//如果setCookieHeader不为空获取到里面的值
					//获取到含有token响应头的值
					 tokenid=setTokenHeader.toString();
					 System.out.println(tokenid);
					
					}
				return tokenid;
				}
	
	

}
