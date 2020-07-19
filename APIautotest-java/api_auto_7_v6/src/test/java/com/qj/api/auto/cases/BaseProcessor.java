package com.qj.api.auto.cases;

import java.util.Map;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.qj.api.auto.pojo.WriteBackData;
import com.qj.api.auto.util.AssertUtil;
import com.qj.api.auto.util.ExcelUtil;
import com.qj.api.auto.util.HttpUnit;
import com.qj.api.auto.util.RestUtil;

/**
 * �ӿڲ���ͳһ������
 * 
 * @author Administrator
 *
 */
public class BaseProcessor {
	/**
	 * @param caseId
	 *            �������
	 * @param apiIdFromcase
	 *            ����sheetҳ�Ľӿ�id
	 * @param params
	 *            ���
	 * @param ExpectedResponseData
	 *            Ԥ�ڽ��
	 */
	@Test(dataProvider = "datas")
	public void test1(String caseId, String apiIdFromcase, String params, String ExpectedResponseData) {// ͨ����������apiIdȥ�ӿ���Ϣ�ҵ�url������ʽ
		// �ӿڵ�ַ
		String url = RestUtil.getUrlByApiId(apiIdFromcase);

		// �ӿ�����
		String type = RestUtil.getTypeByApiId(apiIdFromcase);
		// ����json��ʽ������,׼������
		Map<String, String> paramValues = (Map<String, String>) JSONObject.parse(params);
		// ����doservice������ɽӿڵ��ã��õ���Ӧ����
		String actualResponesData = HttpUnit.doService(type, url, paramValues);
		System.out.println(actualResponesData);
		// ����һ�������Ӧ�����Ԥ�ڽ�����ж���,������ʵ�ʲ��Խ��(actualResponesData)���������(ExpectedResponseData)������жԱ�
		actualResponesData = AssertUtil.assertEquals(actualResponesData, ExpectedResponseData);
		// ����һ���������Ը�ֵ
		WriteBackData writeBackData = new WriteBackData("����", caseId, "ActualResponseData", actualResponesData);
		// ��������ӵ����ϵ��б��棬���Դ���һ�����ϣ�������excelUtil
		ExcelUtil.writeBackDatas.add(writeBackData);
	}

	@AfterSuite
	// �����н������д�뵽���ǵ��ĵ���ȥ
	public void batchWriteBackDatas() {
		ExcelUtil.batchWriteBackDatas("src/test/resources/cases_v6.xlsx");

	}

}
