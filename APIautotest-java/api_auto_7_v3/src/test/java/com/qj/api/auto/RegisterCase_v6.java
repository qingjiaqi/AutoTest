package com.qj.api.auto;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

import bsh.org.objectweb.asm.Type;

/**urlҲͨ��excel������ȡ����Ӧ����������url��ַ
 * @author Administrator
 *
 */
public class RegisterCase_v6 {
	@Test(dataProvider="datas")
	public void test1(String appIdFromcase,String params){//{"mobilephone":"19000000000","pwd":""}
		//��ȡ�����Խӿڵ�ַ��Ϣ
		String excelPath="src/test/resources/cases_v3.xlsx";
		int[] rowNumber={2,3,4,5,6,7,8,9,10,11,12,13,14};
		int[] cellNumber={1,3,4};
		String url="";
		String type="";
		Object[][] objects=ExcelUtil_v4.datas(excelPath, rowNumber, cellNumber,"�ӿ���Ϣ");
				for (Object[] object : objects) {
					String appIdFromRest=object[0].toString();
					if(appIdFromRest.equals(appIdFromcase)){
						//��ȡ��url����ֵ����Ӧ����ı���
						 url=object[2].toString();//׼��url;
						 //��ȡ���������Ͳ���ֵ����Ӧ����ı���
						 type=object[1].toString();//ָ����������;
						 break;
					}
				}
		//����json��ʽ������,׼������
		Map<String, String> paramValues=(Map <String,String>)JSONObject.parse(params);
//		 System.out.println("����URL"+url);
			System.out.println(HttpUnit.doService(type, url, paramValues));
//			System.out.println("ִ��û��");
		
	}	
	@DataProvider
	//��ȡ�������ύ����
	public Object[][] datas(){
		String excelPath="src/test/resources/cases_v3.xlsx";
//		Object [][] datas=ExcelUtil_v2.datas(excelPath,2,7,6,7);
		int[] rowNumber={2,3,4,5,6,7};
		int []cellNumber={3,4};
		 Object[][] datas=ExcelUtil_v4.datas(excelPath, rowNumber, cellNumber, "����");
		System.out.println("����ҳ��"+datas[0][0]);
				
		return datas;
	}	
	/*public static void main(String[] args) {
		String excelPath="src/test/resources/cases_v3.xlsx";
//		Object [][] datas=ExcelUtil_v2.datas(excelPath,2,7,6,7);
		int[] rowNumber={2,3,4,5,6,7};
		int []cellNumber={3,4};
		 Object[][] datas=ExcelUtil_v4.datas(excelPath, rowNumber, cellNumber, "����");
		System.out.println("����ҳ��"+datas[0][1]);
	}*/
	
	/*public static void main(String[] args) {
//		public void test1(String appIdFromcase,String params){//{"mobilephone":"19000000000","pwd":""}
			//�ӿڵ�ַ
			String excelPath="src/test/resources/cases_v3.xlsx";
			int[] rowNumber={2,3,4,5,6,7,8,9,10,11,12,13,14};
			int[] cellNumber={1,3,4};
			String url="";
			String type="";
			Object[][] objects=ExcelUtil_v4.datas(excelPath, rowNumber, cellNumber,"�ӿ���Ϣ");
			System.out.println(objects[0][0]);
			System.out.println(objects[0][1]);
			System.out.println(objects[0][2]);

	}*/
}
