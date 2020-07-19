package com.qj.api.auto.cases;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.qj.api.auto.pojo.WriteBackData;
import com.qj.api.auto.util.AssertUtil;
import com.qj.api.auto.util.DBCheckUtil;
import com.qj.api.auto.util.ExcelUtil;
import com.qj.api.auto.util.HttpUnit;
import com.qj.api.auto.util.RestUtil;
import com.qj.api.auto.util.VariableUtil;
import com.qj.api.auto.util.propertiesUtil;

import bsh.Variable;

/**�ӿڲ���ͳһ������
 * @author Administrator
 *
 */
//���ӿڵ������ñ������棬�����װ����ʱ�������������
public class BaseProcessor {
	//׼��һ����־�����뵱ǰ����ֽ����ļ�
	public Logger logger=Logger.getLogger(BaseProcessor.class);
	//��Ϊ�������е����������õ���Щ�ֶΣ�����ֱ�ӷ��ڻ����м��ٴ����� �ֱ��ǣ�������ţ��ӿڱ�ţ�������Ԥ�ڽ����ִ�в���ǰ���ݿ���֤��ִ�нӿں����ݿ���֤
	public String[] cellNames={"CaseId","ApiId","Params","ExpectedResponseData","PreValidateSql",
			"AfterValidateSql"};
	@Test(dataProvider="datas")
	public void test1(String caseId,String apiIdFromcase,String params,String ExpectedResponseData,
			String preValidateSql,String afterValidateSql){
		logger.info("���ýӿ�ǰ��������֤");
		//"���ýӿ�ǰ"��飬�ж����Ƿ��д������ű�
		if (preValidateSql!=null&&preValidateSql.trim().length()>0) {
			//�ű�����Ҳ�в���������Ҳ��Ҫ������,��ִ��ǰ�Ľű�������һ��preValidateSql,��Ϊ�᷵�ز������������������Ҫ����
			preValidateSql=VariableUtil.replaceVariables(preValidateSql);
			//�ڽӿ�ִ��ǰ�������ǵĽű���ѯ������Ҫ��֤���ֶ�,ͨ���������ݿ⹤���෽��
			String preValidateResult=DBCheckUtil.doQuery(preValidateSql);
			//����ѯ������浽������ȥ��������
			WriteBackData writeBackData=new WriteBackData("����", caseId, "PreValidateResult", preValidateResult);
			//����ѯ�����ӵ�writeBackDatas��ȥ
			 ExcelUtil.writeBackDatas.add(writeBackData);
			
			
		}
		
		//�ӿڵ�ַ ͨ����������apiIdȥ�ӿ���Ϣ�ҵ�url������ʽ
		logger.info("���ݽӿڱ�š�"+apiIdFromcase+"����ȡ�������url");
		String url=RestUtil.getUrlByApiId(apiIdFromcase);
		
		
		//�ӿ�����
		logger.info("���ݽӿڱ�š�"+apiIdFromcase+"����ȡ������Ľӿ�����");
		String type=RestUtil.getTypeByApiId(apiIdFromcase);
		
		
		//�������滻����ʵ������
		params=VariableUtil.replaceVariables(params);
		logger.info("�滻����");
		
		//����json��ʽ������,׼������
		Map<String, String> paramValues=(Map <String,String>)JSONObject.parse(params);
		//����doservice������ɽӿڵ��ã��õ���Ӧ����
		logger.info("��ʼ���ýӿ�");
			String actualResponesData=HttpUnit.doService(type, url, paramValues);
//			System.out.println(actualResponesData);
			//����һ�������Ӧ�����Ԥ�ڽ�����ж���,������ʵ�ʲ��Խ��(actualResponesData)���������(ExpectedResponseData)������жԱ�
			actualResponesData=AssertUtil.assertEquals(actualResponesData,ExpectedResponseData);
			//����һ���������Ը�ֵ
			WriteBackData writeBackData=new WriteBackData("����", caseId, "ActualResponseData", actualResponesData);
			//����Ӧ���������ӵ����ϵ��б��棬���Դ���һ�����ϣ�������excelUtil
			ExcelUtil.writeBackDatas.add(writeBackData); 
			
			//"���ýӿں�"�ж�һ���Ƿ��д���ű�(���ǽӿڵ������˺�Ľű���ѯ)
			logger.info("�ӿڵ��ú�����У��");
			if (afterValidateSql!=null&&afterValidateSql.trim().length()>0) {
				//ִ�к�Ľű�Ҳ�б���Ҳ��Ҫ����������ִ��ǰ�Ľű�������һ��afterValidateSql,��Ϊ�᷵�ز������������������Ҫ����
				afterValidateSql=VariableUtil.replaceVariables(afterValidateSql);
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
		ExcelUtil.batchWriteBackDatas(propertiesUtil.getExcelPath());
		
//			
		
	}
	
}
