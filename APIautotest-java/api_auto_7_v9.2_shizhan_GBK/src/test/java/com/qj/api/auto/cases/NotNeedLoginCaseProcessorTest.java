package com.qj.api.auto.cases;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.microsoft.schemas.office.office.STInsetMode;
import com.qj.api.auto.pojo.WriteBackData;
import com.qj.api.auto.util.AssertUtil;
import com.qj.api.auto.util.CaseUtil;
import com.qj.api.auto.util.DBCheckUtil;
import com.qj.api.auto.util.ExcelUtil;
import com.qj.api.auto.util.HttpUnit;
import com.qj.api.auto.util.JDBCUtil;
import com.qj.api.auto.util.RestUtil;
import com.qj.api.auto.util.VariableUtil;
import com.qj.api.auto.util.propertiesUtil;

import bsh.Variable;

/**
 * ����Ҫ��Ȩ�����Ĳ���������д��
 * 
 * @author Administrator
 *
 */
public class NotNeedLoginCaseProcessorTest extends DateProviderProcessor{
	// ׼��һ����־�����뵱ǰ����ֽ����ļ�,�����ʹ�ӡ������־�Ϳ�֪�����Ǹ��������
	public Logger logger = Logger.getLogger(NotNeedLoginCaseProcessorTest.class);
	//�����������÷����Ķ���
	BaseProcessor baseProcessor=new BaseProcessor();
	/**
	 * @param apiIdFromcase
	 *            ����sheetҳ�Ľӿ�id
	 * @param caseId
	 *            ����sheetҳ�ı��
	 * @param params
	 *            ����sheetҳ�Ĳ���
	 * @param ExpectedResponseData
	 *            ����sheetҲ��Ԥ�ڽ��
	 * @param preValidateSql
	 *            ����sheetҳ��ִ��ǰ�ű���֤
	 * @param afterValidateSql
	 *            ����sheetҳ��ִ�к�ű���֤
	 */
//	 ע��ӿڲ��Է���(register ע��)
	@Test(dataProvider = "registerDate")
	public void register(String apiIdFromcase, String caseId, String params, String ExpectedResponseData,
			String preValidateSql, String afterValidateSql) {
		//����ͳһ�ӿڴ�����ִ�в�������
		baseProcessor.caseBase(apiIdFromcase, caseId, params, ExpectedResponseData, preValidateSql, afterValidateSql);
	}


	//��¼�ӿ�(loan add)
	
	@Test(dependsOnMethods={"register"},dataProvider="loginData")
	public void login(String apiIdFromCase,String caseId,String params,String expectedResponseData,
			String preValidateSql,String afterValidateSql){
		baseProcessor.caseBase(apiIdFromCase, caseId, params, expectedResponseData, preValidateSql, afterValidateSql);

		
	}
	
	//��������ǽ����ǳɹ���¼���õ�id put�����Ǳ�����map��ȥ�޸�֮ǰ��ֵ
	@Test(dependsOnMethods={"login"})
	public void toAddProjectUserId() {
		logger.info("��ʼ��ȡע���û���id==========================");
		//�����ݿ��ѯ���ո�����ע���ҳɹ��˵��û�id��ȥ�����Ŀ
		//�����Ǳ�����map�л�ȡ������ע����ֻ�����
		String variableValue=VariableUtil.variableNameAndValuesMap.get("${toBeRegisterMobilephone}");
		logger.info("��ȡ����ע���ֻ���Ϊ"+variableValue);
		//�������ǵ�sql���
		String sql="select id from member   where MobilePhone="+variableValue;
		//���ø��±��������޸ı���map�С�ȥ�����Ŀ���û�id������ı���ֵ
		VariableUtil.getSpecifiedValueInInterface(sql,"id","${toAddProjectUserId}");
	}
	
	

	@AfterSuite
	// �����н������д�뵽���ǵ��ĵ���ȥ
	public void batchWriteBackDatas() {
		ExcelUtil.batchWriteBackDatas(propertiesUtil.getExcelPath("excel.PathName"));

		//

	}


}
