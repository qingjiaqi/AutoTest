package com.qj.api.auto;

import java.lang.reflect.Method;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

/**
 * Case������ �������������е��������ݷ�װ��case����ȥ
 * 
 * @author Administrator
 *
 */
public class CaseUtil {
	// ����һ���������������ǵ�����,����ΪCase����,������Ϊ��̬�������Ϳ�����ʱȡ��һ���������һ������
	public static List<Case> cases = new ArrayList<Case>();

	// ͨ����̬����鱣֤���ݶ���װ����cases������
	static {
		ExcelUtil_v4.load("src/test/resources/cases_v4.xlsx", "����", Case.class);

	}

	/**
	 * ͨ�����������ȡ��ָ���ӿڱ�ŵ�ָ��������
	 * 
	 * @param apiId
	 *            �ӿڱ��
	 * @param cellNames
	 *            Ҫ��ȡ���е�����
	 * @return �������ǻ�ȡ������ Object[][]
	 */
	public static Object[][] getCaseDateByApiId(String apiId, String[] cellNames) {
		// ��ȡ����case���ֽ����ļ�
		Class clazz = Case.class;

		// ��ȡ������ָ���ӿڵ����ݲ��ŵ�������
		List<Case> csList = new ArrayList<Case>();// ����һ�����������洢���ϵ�����

		// ����cases��������Ķ����ó�apiId�봫�����ĶԱ�
		for (Case cs : cases) {
			if (cs.getApiId().equals(apiId)) {
				csList.add(cs);// �������������ӽ���
			}
		}
		// ����һ����ά���������շ��ϵ������������ֵ
		Object[][] datas = new Object[csList.size()][cellNames.length];
		// ��ȡ�����������������ݺ󣬽���װ��case�����е���������ȡ�����뵽��ά�����й������ṩ�ߵ���
		for (int i = 0; i < csList.size(); i++) {// �����������������ļ���
			Case csArr = csList.get(i);// ����Ҫ��get����ȡ��ÿ���������������ֵ,
			String value = "";
			// ��ȡ�������������Ҫͨ��������ȡ��ÿ������Ҫ�������ݣ���ô������Ҫ�������ݾ��ǵ����ߴ�������Щ�У����ǿ��Ա���������������
			for (int j = 0; j < cellNames.length; j++) {
				String cellName = cellNames[j];// ��ȡ��������������
				// ͨ�������˼���ȡ�������ͨ��get+�����õ���������Ȼ����ã�����ǰ��Ҫ��ȡ��Ҫ����������ֽ����ļ����������ȡ
				String methodName = "get" + cellName;// �����ֶ�ƴ�ӳɶ�Ӧ�ķ�����
				try {
					Method month = clazz.getMethod(methodName);
					value = (String) month.invoke(csArr);// ͨ�����Ƕ�Ӧ�е�get������ȡ����Ӧ��ֵ����Ϊget����û�в������Բ��ô�����
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				datas[i][j] = value;// ��ֵ���뵽���ǵĶ�ά������
			}
		}

		return datas;
	}
	/*
	 * public static void main(String[] args) { String[] a={"Params","Desc"};
	 * Object[][] datas=getCaseDateByApiId("2", a); for (Object[] objects :
	 * datas) { for (Object object : objects) { System.out.println(object); } }
	 * }
	 */
}
