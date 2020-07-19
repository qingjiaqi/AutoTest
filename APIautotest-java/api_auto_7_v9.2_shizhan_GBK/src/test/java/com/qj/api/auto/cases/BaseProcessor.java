package com.qj.api.auto.cases;

import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.qj.api.auto.pojo.WriteBackData;
import com.qj.api.auto.util.AssertUtil;
import com.qj.api.auto.util.DBCheckUtil;
import com.qj.api.auto.util.ExcelUtil;
import com.qj.api.auto.util.HttpUnit;
import com.qj.api.auto.util.RestUtil;
import com.qj.api.auto.util.VariableUtil;
import com.qj.api.auto.util.propertiesUtil;
/**�ӿڲ���ʱ��ǰ��׼���������������ݵ�׼���ӿڵ�ִ�У���д��
 * @author Administrator
 *Processor(�������ӹ�)
 */
public class BaseProcessor {
	static Logger logger = Logger.getLogger(BaseProcessor.class);

	/**
	 * ��������ͳһ������  �޷���ֵ
	 * 
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
	public  void caseBase(String apiIdFromCase, String caseId, String params, String expectedResponseData,
			String preValidateSql, String afterValidateSql) {
		logger.info("���ýӿ�ǰ��������֤");
		//�ж��������Ƿ��нű���Ҫִ��
		if (preValidateSql != null && preValidateSql.trim().length() > 0) {
			// �ű�����Ҳ�в���������Ҳ��Ҫ������,��ִ��ǰ�Ľű�������һ��preValidateSql,��Ϊ�᷵�ز������������������Ҫ����
			preValidateSql = VariableUtil.replaceVariables(preValidateSql);

			// �ڽӿ�ִ��ǰ�������ǵĽű���ѯ������Ҫ��֤���ֶ�,ͨ���������ݿ⹤���෽��
			String preValidateResult = DBCheckUtil.doQuery(preValidateSql);
			// ����ѯ������浽������ȥ��������
			WriteBackData writeBackData = new WriteBackData("����", caseId, "PreValidateResult", preValidateResult);
			// ����ѯ������ӵ�writeBackDatas��ȥ
			ExcelUtil.writeBackDatas.add(writeBackData);

		}

		// �ӿڵ�ַ ͨ����������apiIdȥ�ӿ���Ϣ�ҵ�url������ʽ
		String url = RestUtil.getUrlByApiId(apiIdFromCase);
		//�ӿ�����  ͨ����������apiIdȥ�ӿ���Ϣ�ҵ��ӿ�����
		String name=RestUtil.getNameApiId(apiIdFromCase);
		logger.info("���ݽӿڱ�š�" + apiIdFromCase + "��"+"��"+name+"�ӿڡ�"+"��ȡ�������url" +"��"+ url+"��");

		// �ӿ�����
		String type = RestUtil.getTypeByApiId(apiIdFromCase);
		logger.info("���ݽӿڱ�š�" + apiIdFromCase + "����ȡ������Ľӿ�����" + type);

		// �������滻����ʵ������
		params = VariableUtil.replaceVariables(params);
		logger.info("�滻����");

		// ����json��ʽ������,׼������
		Map<String, String> paramValues = (Map<String, String>) JSONObject.parse(params);
		// ����doservice������ɽӿڵ��ã��õ���Ӧ����
		logger.info("��ʼ���ýӿ�");
		String actualResponesData = HttpUnit.doService(type, url, paramValues,
				propertiesUtil.getValueByKey("api.contet.type.form"));
		// System.out.println(actualResponesData);
		// ����һ�������Ӧ�����Ԥ�ڽ�����ж���,������ʵ�ʲ��Խ��(actualResponesData)���������(ExpectedResponseData)������жԱ�
		actualResponesData = AssertUtil.assertEquals(actualResponesData, expectedResponseData);
		// ����һ���������Ը�ֵ
		WriteBackData writeBackData = new WriteBackData("����", caseId, "ActualResponseData", actualResponesData);
		// ����Ӧ����������ӵ����ϵ��б��棬���Դ���һ�����ϣ�������excelUtil
		ExcelUtil.writeBackDatas.add(writeBackData);

		// "���ýӿں�"�ж�һ���Ƿ��д���ű�(���ǽӿڵ������˺�Ľű���ѯ)
		logger.info("�ӿڵ��ú�����У��");
		if (afterValidateSql != null && afterValidateSql.trim().length() > 0) {
			// ִ�к�Ľű�Ҳ�б���Ҳ��Ҫ����������ִ��ǰ�Ľű�������һ��afterValidateSql,��Ϊ�᷵�ز������������������Ҫ����
			afterValidateSql = VariableUtil.replaceVariables(afterValidateSql);
			// ��ɽű����ò�ѯ��Ҫ��֤���ֶΣ�ͨ���������ݿ⹤���෽��
			String afterValidateResult = DBCheckUtil.doQuery(afterValidateSql);
			// ����ѯ���Ľ����װ��WriteBackData����ȥ
			WriteBackData writeBackData2 = new WriteBackData("����", caseId, "AfterValidateResult", afterValidateResult);
			// ����ȡ���Ķ������ӵ����ݻ�д������ȥ
			ExcelUtil.writeBackDatas.add(writeBackData2);
		}
	}

	/**
	 * ��������ͳһ������ �з���ֵ
	 * 
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
	 * @return   ���ؽӿ���Ӧ����(��������̬���ݵĳ�ȡ)
	 */
	
	public  String caseBaseResult(String apiIdFromCase, String caseId, String params, String expectedResponseData,
			String preValidateSql, String afterValidateSql) {
		if (preValidateSql != null && preValidateSql.trim().length() > 0) {
			// �ű�����Ҳ�в���������Ҳ��Ҫ������,��ִ��ǰ�Ľű�������һ��preValidateSql,��Ϊ�᷵�ز������������������Ҫ����
			preValidateSql = VariableUtil.replaceVariables(preValidateSql);

			// �ڽӿ�ִ��ǰ�������ǵĽű���ѯ������Ҫ��֤���ֶ�,ͨ���������ݿ⹤���෽��
			String preValidateResult = DBCheckUtil.doQuery(preValidateSql);
			// ����ѯ������浽������ȥ��������
			WriteBackData writeBackData = new WriteBackData("����", caseId, "PreValidateResult", preValidateResult);
			// ����ѯ������ӵ�writeBackDatas��ȥ
			ExcelUtil.writeBackDatas.add(writeBackData);

		}

		// �ӿڵ�ַ ͨ����������apiIdȥ�ӿ���Ϣ�ҵ�url������ʽ
		String url = RestUtil.getUrlByApiId(apiIdFromCase);
		logger.info("���ݽӿڱ�š�" + apiIdFromCase + "����ȡ�������url" + url);

		// �ӿ�����
		String type = RestUtil.getTypeByApiId(apiIdFromCase);
		logger.info("���ݽӿڱ�š�" + apiIdFromCase + "����ȡ������Ľӿ�����" + type);

		// �������滻����ʵ������
		params = VariableUtil.replaceVariables(params);
		logger.info("�滻����");

		// ����json��ʽ������,׼������
		Map<String, String> paramValues = (Map<String, String>) JSONObject.parse(params);
		logger.info("��ʼ���ýӿ�");
		// ����doservice������ɽӿڵ��ã��õ���Ӧ����
		String actualResponesData = HttpUnit.doService(type, url, paramValues,
				propertiesUtil.getValueByKey("api.contet.type.form"));
		// System.out.println(actualResponesData);
		// ����һ�������Ӧ�����Ԥ�ڽ�����ж���,������ʵ�ʲ��Խ��(actualResponesData)���������(ExpectedResponseData)������жԱ�
		actualResponesData = AssertUtil.assertEquals(actualResponesData, expectedResponseData);
		// ����һ���������Ը�ֵ
		WriteBackData writeBackData = new WriteBackData("����", caseId, "ActualResponseData", actualResponesData);
		// ����Ӧ����������ӵ����ϵ��б��棬���Դ���һ�����ϣ�������excelUtil
		ExcelUtil.writeBackDatas.add(writeBackData);

		// "���ýӿں�"�ж�һ���Ƿ��д���ű�(���ǽӿڵ������˺�Ľű���ѯ)
		logger.info("�ӿڵ��ú�����У��");
		if (afterValidateSql != null && afterValidateSql.trim().length() > 0) {
			// ִ�к�Ľű�Ҳ�б���Ҳ��Ҫ����������ִ��ǰ�Ľű�������һ��afterValidateSql,��Ϊ�᷵�ز������������������Ҫ����
			afterValidateSql = VariableUtil.replaceVariables(afterValidateSql);
			// ��ɽű����ò�ѯ��Ҫ��֤���ֶΣ�ͨ���������ݿ⹤���෽��
			String afterValidateResult = DBCheckUtil.doQuery(afterValidateSql);
			// ����ѯ���Ľ����װ��WriteBackData����ȥ
			WriteBackData writeBackData2 = new WriteBackData("����", caseId, "AfterValidateResult", afterValidateResult);
			// ����ȡ���Ķ������ӵ����ݻ�д������ȥ
			ExcelUtil.writeBackDatas.add(writeBackData2);
		}
		return actualResponesData;
	}

}