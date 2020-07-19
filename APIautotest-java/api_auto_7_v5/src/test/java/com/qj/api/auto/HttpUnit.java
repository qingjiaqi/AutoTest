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

/**�ӿڵ��ù�����
 * @author Administrator
 *
 */
public class HttpUnit {
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
			HttpResponse httpResponse=httpClient.execute(httpPost);//���ͽӿ�����
			int code=httpResponse.getStatusLine().getStatusCode();//��ȡ״̬��
			//��Ӧ����,httpResponse.getEntity()��ȡ����Ӧ����,EntityUtils.toString()������ת��Ϊstring���͵�
			result=EntityUtils.toString(httpResponse.getEntity());
			
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
		String resurt="";
		try {
			HttpResponse response = httpClient.execute(httpGet);//��������
			//��ȡ��code
			int code=response.getStatusLine().getStatusCode();
			//��ȡ����Ӧ����
			HttpEntity  entity=response.getEntity();
			//����Ӧ����ת����string����
			resurt	=EntityUtils.toString(entity);
//			System.out.println("����httpunit�����97�е����"+resurt);
//			System.out.println(resurt);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resurt;
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
	
}
