package com.qj.api.auto.cases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qj.api.auto.util.CaseUtil;

/**���ֽӿ���������
 * @author Administrator
 *
 */
//�̳в��������Ļ��࣬��������������ĸ�����ִ֤��
public class Withdraw extends BaseProcessor {
	//��д�����ṩ�߷����ͱ�ǩ
	@DataProvider
	public Object[][] datas() {
		//�������������Ĵ��������еĻ�ȡ���ݷ�����ȡ��������Ҫ����Ϣ����
		Object[][] datas=CaseUtil.getCaseDateByApiId("4", cellNames);
		return datas;
		
	}
	
}
