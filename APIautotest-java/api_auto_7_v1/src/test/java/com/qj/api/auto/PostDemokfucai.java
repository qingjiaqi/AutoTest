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
//		3.��д�ӿڵ�ַ
//        String url="http://192.168.1.168:8888/ayface/servlet/ssoUser/login?userAccount=test1&password=Deta1234";
		//���ʽӿڵ�ַ
		String url="http://rap2api.taobao.org/app/mock/116450/api/app/search/result";
//    4.ָ���ӿ�����ʽ post ����httpPost��
        HttpPost httpPost=new HttpPost(url);
        //����һ��JsonObject�������� lng=22.728172&lat=114.273917&keyword="��"
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("lng", "22.728172");
        jsonObject.addProperty("lat", "114.273917");
        jsonObject.addProperty("keyword", "��");
//        jsonObject.addProperty("meid", "FJH5T18819022280");
        //��������ֵ��ӵ��������У�setsetEntity��һ���ӿ���Ҫһ���������UrlEncodedFormEntity(parameters,"utf-8")��ʵ��
        //parameters��Ҫ��һ�����϶���
        httpPost.setEntity(new StringEntity(jsonObject.toString(),"utf-8"));
//    7.�������󣬻�ȡ�ӿ���Ӧ��Ϣ��״̬�룬��Ӧ���ģ�ĳЩ�������Ӧ���ݣ�
        //������ǰ��Ҫ׼���ͻ��ˣ�����HttpClients.createDefault()���߷���
        HttpClient httpClient=HttpClients.createDefault();
        //������ͷ����һ����������Ϊ
//        addRequestHeadBeforRequest(httpPost);
        httpPost.addHeader("Content-Type", "application/json");
        //���ͽӿ�����
        HttpResponse httpResponse=httpClient.execute(httpPost);
       
        //״̬��
        int code=httpResponse.getStatusLine().getStatusCode();
        System.out.println(code);
        //��Ӧ����,httpResponse.getEntity()��ȡ����Ӧ����,EntityUtils.toString()������ת��Ϊstring���͵�
        String result=EntityUtils.toString(httpResponse.getEntity());
        System.out.println(result);
//        getAndStoreTokenFromResponseHead(result);
        
      }
	

	
	
	/**���������������������ͷ�������Ϣ��
	 * @param httpPost
	 */
	private static void addRequestHeadBeforRequest(HttpRequest httpRequest) {
		//������ͷ������һ�������ύ����
		httpRequest.addHeader("Content-Type", "application/json");
//		httpRequest.addHeader("Cookie","JSESSIONID=951FBCFB4D377B8C458B57CD3D2FEBF5");
		
		
		
	}
	
	/**���������������ȡAPP�ӿ���Ӧ���е�access_tokenֵ��
	 * @param result ����ӿں󷵻ص���Ӧ�����access_tokenֵ��
	 */
	private static void getAndStoreTokenFromResponseHead(String result) {	
		JSONObject jsonObject = JSONObject.fromObject(result);
		String data=jsonObject.getString("data");
		JSONObject jsonObject1 = JSONObject.fromObject(data);
		System.out.println(jsonObject1.getString("access_token"));
	}

	
	private static String getAndStoreTokenFromResponseHead(HttpResponse httpResponse) {
		// ����Ӧͷ��ȡ������Ϊtoken����Ӧͷ����ע���Сд��Ȼ���ȡ����
//				Header setTokenHeader=httpResponse.getFirstHeader("access_token");
			Header[] setTokenHeader=httpResponse.getHeaders("access_token");
				String tokenid=null;
				if(setTokenHeader!=null){//���setCookieHeader��Ϊ�ջ�ȡ�������ֵ
					//��ȡ������token��Ӧͷ��ֵ
					 tokenid=setTokenHeader.toString();
					 System.out.println(tokenid);
					
					}
				return tokenid;
				}
	
	

}
