package com.qj.api.auto.cases;

import java.util.Map;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.qj.api.auto.pojo.WriteBackData;
import com.qj.api.auto.util.AssertUtil;
import com.qj.api.auto.util.DBCheckUtil;
import com.qj.api.auto.util.ExcelUtil;
import com.qj.api.auto.util.HttpUnit;
import com.qj.api.auto.util.RestUtil;

/**�ӿڲ���ͳһ������
 * @author Administrator
 *
 */
//����汾���������ݿ��У��
public class BaseProcessor {
	//��Ϊ�������е����������õ���Щ�ֶΣ�����ֱ�ӷ��ڻ����м��ٴ�����
	public String[] cellNames={"CaseId","ApiId","Params","ExpectedResponseData","PreValidateSql",
			"AfterValidateSql"};
	@Test(dataProvider="datas")
	public void test1(String caseId,String apiIdFromcase,String params,String ExpectedResponseData,
			String preValidateSql,String afterValidateSql){
		//���ýӿ�ǰ��飬�ж����Ƿ��д������ű�
		if (preValidateSql!=null&&preValidateSql.trim().length()>0) {
			//�ڽӿ�ִ��ǰ�������ǵĽű���ѯ������Ҫ��֤���ֶ�,ͨ���������ݿ⹤���෽��
			String preValidateResult=DBCheckUtil.doQuery(preValidateSql);
			//����ѯ������浽������ȥ��������
			WriteBackData writeBackData=new WriteBackData("����", caseId, "PreValidateResult", preValidateResult);
			//����ѯ�����ӵ�writeBackDatas��ȥ
			 ExcelUtil.writeBackDatas.add(writeBackData);
			
			
		}
		
		//�ӿڵ�ַ ͨ����������apiIdȥ�ӿ���Ϣ�ҵ�url������ʽ
		String url=RestUtil.getUrlByApiId(apiIdFromcase);
		
		//�ӿ�����
		String type=RestUtil.getTypeByApiId(apiIdFromcase);
		//����json��ʽ������,׼������
		Map<String, String> paramValues=(Map <String,String>)JSONObject.parse(params);
		//����doservice������ɽӿڵ��ã��õ���Ӧ����
			String actualResponesData=HttpUnit.doService(type, url, paramValues);
			System.out.println(actualResponesData);
			//����һ�������Ӧ�����Ԥ�ڽ�����ж���,������ʵ�ʲ��Խ��(actualResponesData)���������(ExpectedResponseData)������жԱ�
			actualResponesData=AssertUtil.assertEquals(actualResponesData,ExpectedResponseData);
			//����һ���������Ը�ֵ
			WriteBackData writeBackData=new WriteBackData("����", caseId, "ActualResponseData", actualResponesData);
			//��������ӵ����ϵ��б��棬���Դ���һ�����ϣ�������excelUtil
			ExcelUtil.writeBackDatas.add(writeBackData); 
			
			//�ж�һ���Ƿ��д���ű�(���ǽӿڵ������˺�Ľű���ѯ)
			if (afterValidateSql!=null&&afterValidateSql.trim().length()>0) {
				//��ɽű����ò�ѯ��Ҫ��֤���ֶΣ�ͨ���������ݿ⹤���෽��
				String afterValidateResult=DBCheckUtil.doQuery(afterValidateSql);
				//����ѯ���Ľ����װ��WriteBackData����ȥ
				WriteBackData writeBackData2=new WriteBackData("����", caseId,"AfterValidateResult", afterValidateResult);
				//����ȡ���Ķ�����ӵ����ݻ�д������ȥ
				ExcelUtil.writeBackDatas.add(writeBackData2);
			}
	}
	@AfterSuite
	//�����н������д�뵽���ǵ��ĵ���ȥ
	public void batchWriteBackDatas(){
		ExcelUtil.batchWriteBackDatas("src/test/resources/cases_v7.xlsx");
		System.out.println("baseprocessor���е�69��"+ExcelUtil.writeBackDatas.size());
		
	}
	
}
