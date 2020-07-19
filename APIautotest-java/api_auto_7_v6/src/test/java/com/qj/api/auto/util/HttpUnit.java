package com.qj.api.auto.util;

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
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**�ӿڵ��ù�����
 * @author Administrator
 *
 */
public class HttpUnit {
	//����һ��map������װ���ǵ�cookie,Ϊ���ܹ���������Ϊ��̬
	public static Map<String, String> cookies = new HashMap<String, String>();
	/**��post��ʽ����ӿڵ���
	 * @param url �ӿڵ�ַ
	 * @param params �������Ͳ���ֵ map����
	 * 
	 * @return ��Ӧstring����
	 */
	public static String doPost(String url,Map<String, String> params){
		
		//4.ָ���ӿ�����ʽ post ����httpPost��
		HttpPost httpPost=new HttpPost(url);
		
		//���������������
		List<BasicNameValuePair> parameters=new ArrayList<BasicNameValuePair>();
		//ѭ��map���Ȼ�ȡ�����е�key
		Set<String> keys= params.keySet();
		for (String name: keys) {
			String value=params.get(name);
			//��Ӳ���
			parameters.add(new BasicNameValuePair(name, value));
			
		}
//		System.out.println(parameters);
		String result="";
		try {
			//��������ֵ��ӵ��������У�setsetEntity��һ���ӿ���Ҫһ���������UrlEncodedFormEntity(parameters,"utf-8")��ʵ��
			//parameters��Ҫ��һ�����϶���
			httpPost.setEntity(new UrlEncodedFormEntity(parameters,"utf-8"));
//		7.�������󣬻�ȡ�ӿ���Ӧ��Ϣ��״̬�룬��Ӧ���ģ�ĳЩ�������Ӧ���ݣ�
			//������ǰ��Ҫ׼���ͻ��ˣ�����HttpClients.createDefault()���߷���
			HttpClient httpClient=HttpClients.createDefault();
			//����һ����������������Jsessionid��ӵ����ǵ�����ͷ��ȥ
			addCokieInrequestHeaderBeforeRequest(httpPost);
			//���ͽӿ�����
			HttpResponse httpResponse=httpClient.execute(httpPost);
			//����һ����������ȡ��������������Ӧͷ��cookie-set��jsessionid��cookie
			getAndStoreCookiesFromResponseHeader(httpResponse);
			int code=httpResponse.getStatusLine().getStatusCode();//��ȡ״̬��
			//��Ӧ����,httpResponse.getEntity()��ȡ����Ӧ����,EntityUtils.toString()������ת��Ϊstring���͵�
			result=EntityUtils.toString(httpResponse.getEntity());
			System.out.println("httpUnit57:code=["+code+"],result=["+result+"]");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(result);
		return result;
	}



	/**get����ʽ����ӿڵ���
	 * @param url �ӿڵ�ַ
	 * @param params �������Ͳ���ֵ
	 * @return ��Ӧ���� ����Ϊstring����
	 */
	public static String getDemo(String url,Map<String, String> params){	
		//ѭ��map���Ȼ�ȡ�����е�key
		Set<String> keys =params.keySet();
		int mak=1;//����һ����ʶλ
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
		//ȷ������ʽ get
		HttpGet httpGet=new HttpGet(url);
		HttpClient httpClient=HttpClients.createDefault();//��Ҫ����һ���ͻ���httpclient
		String result="";
		try {
			addCokieInrequestHeaderBeforeRequest(httpGet);
			//��������
			HttpResponse response = httpClient.execute(httpGet);
			getAndStoreCookiesFromResponseHeader(response);
			//��ȡ��code
			int code=response.getStatusLine().getStatusCode();
			//��ȡ����Ӧ����
			HttpEntity  entity=response.getEntity();
			//����Ӧ����ת����string����
			result	=EntityUtils.toString(entity);
			System.out.println("httpUnit98:code=["+code+"],result=["+result+"]");
//			System.out.println("����httpunit�����97�е����"+resurt);
//			System.out.println(resurt);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**������������ӿڵĿ���֧��get��post����;
	 * @param type ��������
	 * @param url	�ӿڵ�ַ
	 * @param paramValues �ϴ�����
	 * @return ������Ӧ���� ����Ϊstring
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
	
	/**������������������ǵĻỰid��ӵ���������ͷ��ȥ,ʹ���ǽӿ���Ȩ�޽��з���
	 * @param httpRequest �������ͣ���Ϊ���ǵ������ж�������Ҫ������������������
	 */
	private static void addCokieInrequestHeaderBeforeRequest(HttpRequest httpRequest) {
		//�����ǵı���cookie��map����ȡ�����ǵ�sessionid����
		String jsessionidCokie=cookies.get("JSESSIONID");
		if(jsessionidCokie!=null){
			//��Ϊ�յ�ʱ�����ǵĻỰ��ӵ����ǵ�ͷ��Ϣ����ȥ
			httpRequest.addHeader("Cookie", jsessionidCokie);
		}
	}

	/**ȡ�����Ǻ���JSESSIONID�ĻỰid��cookie������Ȩʹ��
	 * @param httpResponse ��Ӧ���ص�����
	 */
	private static void getAndStoreCookiesFromResponseHeader(HttpResponse httpResponse) {
		// ����Ӧͷ��ȡ������ΪSet-Cookie����Ӧͷ����ע���Сд��Ȼ���ȡ����
		Header setCookieHeader=httpResponse.getFirstHeader("Set-Cookie");
		if(setCookieHeader!=null){//���setCookieHeader��Ϊ�ջ�ȡ�������ֵ
			//��ȡ������Set-Cookie��Ӧͷ��ֵ
			String cookiePairString=setCookieHeader.getValue();
			//�ж�һ�����ȡ���ļ�ֵ�Բ�Ϊ�գ�����ȥ���ո�󳤶ȴ���0���������ֵ���зָ�
			if (cookiePairString!=null&&cookiePairString.trim().length()>0) {
				//��Ϊ���ǵ�cookie�����ֵ����;������������;�����и����һ��������н���
			String [] cookiePairs=cookiePairString.split(";");
			if (cookiePairs!=null) {//�ж������Ƿ�Ϊ�������Ϊ��ѭ������
				//ѭ�������ҳ�����jsession��cookie
				for(String cookiePair:cookiePairs){
					//�������JSESSIONID����ζ����Ӧͷ�лỰID�������
					if (cookiePair.contains("JSESSIONID")) {
						//�����ǵĻỰID���浽���ǵ�cookiesmap����
						cookies.put("JSESSIONID", cookiePair);
					}
				}
				
			}
				
			}
			
		}
	}
}
