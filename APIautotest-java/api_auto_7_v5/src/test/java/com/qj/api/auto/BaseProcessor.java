package com.qj.api.auto;

import java.util.Map;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

/**�ӿڲ���ͳһ������
 * @author Administrator
 *
 */
public class BaseProcessor {
	@Test(dataProvider="datas")
	public void test1(String caseId,String apiIdFromcase,String params){//ͨ����������apiIdȥ�ӿ���Ϣ�ҵ�url������ʽ
		//�ӿڵ�ַ
		String url=RestUtil.getUrlByApiId(apiIdFromcase);
		
		//�ӿ�����
		String type=RestUtil.getTypeByApiId(apiIdFromcase);
		//����json��ʽ������,׼������
		Map<String, String> paramValues=(Map <String,String>)JSONObject.parse(params);
		//����doservice������ɽӿڵ��ã��õ���Ӧ����
			String result=HttpUnit.doService(type, url, paramValues);
			System.out.println(result);
			//����һ����д�������������浽��Ӧ�Ķ�����(�ĸ���������˼�ǰ�excel�е�����sheetҳ�е��������Ϊxx���е�����Ϊ"ActualResponseData"���У����Ϊresult)
			WriteBackData writeBackData=new WriteBackData("����", caseId, "ActualResponseData", result);
			//��������ӵ����ϵ��б��棬���Դ���һ�����ϣ�������excelUtil����Ϊ��д�����������������
			ExcelUtil.writeBackDatas.add(writeBackData); 
	}
	@AfterSuite
	//�����н������д�뵽���ǵ��ĵ���ȥ
	public void batchWriteBackDatas(){
		ExcelUtil.batchWriteBackDatas("src/test/resources/cases_v5.xlsx");
		
	}
	
}
