package com.qj.api.auto.cases;

import java.util.HashMap;
import java.util.Map;

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
 * ����������д�ಢ����Ҫ���м�Ȩ����ܲ����Ľӿ������ڴ˴�д
 * 
 * @author Administrator
 *
 */
public class NeedLoginCaseProcessor extends DateProviderProcessor{
	// ׼��һ����־�����뵱ǰ����ֽ����ļ�,�����ʹ�ӡ������־�Ϳ�֪�����Ǹ��������
	public Logger logger = Logger.getLogger(NeedLoginCaseProcessor.class);
	//�����������÷����Ķ���
	BaseProcessor baseProcessor=new BaseProcessor();
		static{
			//���õ�¼�ӿڼ�Ȩʹ��
			LoginSingle loginSingle=new LoginSingle();
			loginSingle.login("login.url", "login.param", "request.mode.post", "api.contet.type.form");
		}
	
	
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
	// ���ֽӿڲ��Է���(Withdraw ����)
//	@Test(dataProvider = "WithdrawDate")
	public void Withdraw(String apiIdFromcase, String caseId, String params, String ExpectedResponseData,
			String preValidateSql, String afterValidateSql) {
		//����ͳһ�ӿڴ�����ִ�в�������
		baseProcessor.caseBase(apiIdFromcase, caseId, params, ExpectedResponseData, preValidateSql, afterValidateSql);
	}

	//��ֵ�ӿڲ��Է���(recharge ��ֵ)
//	@Test(dataProvider="recharge")
	public void recharge(String apiIdFromcase,String caseId,String params,String expectedResponseData,
			String preValidateSql,String afterValidateSql) {
		//���ýӿ�ͳһ������(�޷���ֵ)
//		baseProcessor.caseBase(caseId, apiIdFromcase, params, expectedResponseData, preValidateSql, afterValidateSql);
		baseProcessor.caseBase(apiIdFromcase, caseId, params, expectedResponseData, preValidateSql, afterValidateSql);
	}
	
	//
	//�����Ŀ�ӿ�(loan add)
//	@Test(dataProvider="loanAdd")
	public void loanAdd(String apiIdFromCase,String caseId,String params,String expectedResponseData,
			String preValidateSql,String afterValidateSql){
		baseProcessor.caseBase(apiIdFromCase, caseId, params, expectedResponseData, preValidateSql, afterValidateSql);
		//�����ݿ��ѯ���ո���������ĿidΪ
		//�������ǵ�sql��估��ѯ������ֵ
		String a="1121713";
		String sql="select id from loan   where MemberID="+a+"  order by CreateTime desc LIMIT 1";
		//���ø��±��������޸ı��������mapֵ
		VariableUtil.getSpecifiedValueInInterface(sql,"id","${toExamineProjectId}");
		
	}
	
	//�����Ŀ�ӿ�(loan add)
//	@Test(dataProvider="loanAudit")
	public void loanAudit(String apiIdFromCase,String caseId,String params,String expectedResponseData,
			String preValidateSql,String afterValidateSql){
		baseProcessor.caseBase(apiIdFromCase, caseId, params, expectedResponseData, preValidateSql, afterValidateSql);
	}
	
	
	

	@AfterSuite
	// �����н������д�뵽���ǵ��ĵ���ȥ
	public void batchWriteBackDatas() {
		ExcelUtil.batchWriteBackDatas(propertiesUtil.getExcelPath("excel.PathName"));

		//

	}


}
