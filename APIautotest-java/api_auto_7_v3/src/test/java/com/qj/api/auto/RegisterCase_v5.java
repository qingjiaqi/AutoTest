package com.qj.api.auto;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

/**���ע��ӿڵĲ���,������json��ʽ�����һ������
 * @author Administrator
 *
 */
public class RegisterCase_v5 {
	//д���test����
	
	@Test(dataProvider="datas")
	public void test1(String params){//{"mobilephone":"19000000000","pwd":""}
		String url="http://119.23.241.154:8080/futureloan/mvc/api/member/register?";//�����ַ
				
		//����json��ʽ������,׼������
		Map<String, String> paramValues=(Map <String,String>)JSONObject.parse(params);
		String a=HttpUnit.doPost(url, paramValues);
		System.out.println(a);
		System.out.println();
	}	
	@DataProvider
	public Object[][] datas(){
		String excelPath="src/test/resources/cases_v2.xls";
//		Object [][] datas=ExcelUtil_v2.datas(excelPath,2,7,6,7);
		int[] rowNumber={2,3,4,5,6,7};
		int []cellNumber={6};
//		String sheetName="����";
		Object[][] datas =ExcelUtil_v4.datas(excelPath, rowNumber, cellNumber,"����");
		System.out.println("���ǲ���ҳ���"+datas[0][0]);
				
		return datas;
	}
	
	
	}
	