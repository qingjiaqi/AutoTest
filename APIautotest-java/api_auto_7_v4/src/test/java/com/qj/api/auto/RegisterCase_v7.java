package com.qj.api.auto;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

import bsh.org.objectweb.asm.Type;

public class RegisterCase_v7 {
	@Test(dataProvider="datas")
	public void test1(String apiIdFromcase,String params){//ͨ����������apiIdȥ�ӿ���Ϣ�ҵ�url������ʽ
		//�ӿڵ�ַ
		String url=RestUtil.getUrlByApiId(apiIdFromcase);
		
		//�ӿ�����
		String type=RestUtil.getTypeByApiId(apiIdFromcase);
		//����json��ʽ������,׼������
		Map<String, String> paramValues=(Map <String,String>)JSONObject.parse(params);
			System.out.println(HttpUnit.doService(type, url, paramValues));

	}	
	@DataProvider
	public Object[][] datas(){
		String[] cellNames={"ApiId","Params"};
		 Object[][] datas=CaseUtil.getCaseDateByApiId("1", cellNames);
		System.out.println("����ҳ��"+datas[0][0]);
		return datas;
	}	
	
}
