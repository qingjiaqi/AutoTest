package com.qj.api.auto;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

/*��¼�ӿڲ�����
 * @author Administrator
 *
 */
public class LoginCase {
	@Test(dataProvider="caseDate")
	public void test1(String apiIdFromcase,String params){//ͨ����������apiIdȥ�ӿ���Ϣ�ҵ�url������ʽ
		//�ӿڵ�ַ
		String url=RestUtil.getUrlByApiId(apiIdFromcase);
		
		//�ӿ�����
		String type=RestUtil.getTypeByApiId(apiIdFromcase);
		//����json��ʽ������,׼������
		Map<String, String> paramValues=(Map <String,String>)JSONObject.parse(params);
			System.out.println(HttpUnit.doService(type, url, paramValues));

	}	
	@DataProvider(name="caseDate")
	public Object[][] datas(){
		//����һ�����飬����Ҫ��ȡ���ֶ�
		String[] cellNames={"ApiId","Params"};
		//����getCaseDateByApiId������ȡ����������
		 Object[][] datas=CaseUtil.getCaseDateByApiId("2", cellNames);
		System.out.println("����ҳ��"+datas[0][0]);
		return datas;
	}	
}
