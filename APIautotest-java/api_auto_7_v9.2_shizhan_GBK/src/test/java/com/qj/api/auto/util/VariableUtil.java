package com.qj.api.auto.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.functors.AndPredicate;
import org.apache.log4j.Logger;

import com.qj.api.auto.pojo.Variable;
import com.qj.api.auto.pojo.WriteBackData;



/**���������б����Ĳ���
 * @author Administrator
 *
 */
public class VariableUtil {
	//����һ����־����
	public static Logger logger=Logger.getLogger(VariableUtil.class);
	//����һ��map�������ǵı������ͱ���ֵ
	public static Map<String, String> variableNameAndValuesMap=new HashMap<String, String>();
	//����һ�����ϱ������е����ݶ���Ҫ�ȳ�ʼ��variables���ϲ�Ȼ���澲̬�������ӵ�ʱ��ر���ָ��
	public static List<Variable> variables=new ArrayList<Variable>();
	static{
		//��һ�����ر����������һ�ν�ÿ�з�װ�ɶ���Ȼ��ͳһ��ӵ�����
		List<Variable> list=ExcelUtil.load(propertiesUtil.getExcelPath("excel.PathName"), "����", Variable.class);
		//������һ������ӵ�variables��ȥ
		variables.addAll(list);
		//���÷����������ͱ�����ֵ���ص�map��ȥ
		loadVariablesToMap();
		//���س����Ǳ������е�����ӳ��
		ExcelUtil.lodRownumAndCellnumMapping(propertiesUtil.getExcelPath("excel.PathName"), "����");
		
		
	}


	/**�滻��������ı���Ϊ����(������)
	 * @parameter parameter ���б����Ĳ���
	 * @return ���ؽ������滻��ʵ�����ݵĲ���
	 */
	public static String replaceVariables(String parameter) {
		//ѭ���������ͱ���ֵ��map����ȡ���������������ŵ�key
		Set<String> variableNames=variableNameAndValuesMap.keySet();
		for (String variableName : variableNames) {
			//��������а����˱�����
			if (parameter.contains(variableName)) {
				//�������еı���ȫ���滻��ֵ��һ�������Ǳ��������֣��ڶ���������Ҫ�����ֵ
				parameter=parameter.replace(variableName, variableNameAndValuesMap.get(variableName));
			}
		}
		
		return parameter;
	}


	/**
	 * �����������Ͻ���װ�ı������Ͷ�Ӧ�ı���ֵ���뵽variableNameAndValuesMap(�������ͱ���ֵ)����ȥ
	 */
	private static void loadVariablesToMap() {
		//ѭ��variables�����ó����е��ֶκ�ֵ
				for (Variable variable : variables) {
					//��ȡ������������
					String variableName=variable.getName();
					//��ȡ��������ֵ
					String variableValue=variable.getValue();
					//��ȡ�����ǵķ������ȫ·����ֵ
					String reflectClass=variable.getReflectClass();
					
					//�ж�һ�±�����ֵ�Ƿ�Ϊ�գ���һ����Ϊ�˴�����Щ�����ظ�ʹ�õ������õ�
					if (variableValue==null||variableValue.trim().length()==0) {
						//�ж��·������Ƿ���ֵ���û�о͸���һ��
						if (reflectClass==null|| reflectClass.trim().length()==0) {
							variableValue="��̬����Ԥ��λ";
						}else {
					/*	//��ȡ�����Ƿ������
						String reflectClass=variable.getReflectClass();*/
						//��ȡ�����Ƿ���ķ���
						String reflectMethod=variable.getReflectMethod();
						try {
							//ͨ�����ȫ·���õ����ǵ����ֽ����ļ����������ȫ·��
							Class clazz=Class.forName(reflectClass);
							//�õ��ֽ����ļ��󴴽�һ����������Ϊobject�Ķ���
							Object object=clazz.newInstance();
							//ͨ����������reflectMethod����ȡһ����������method,û�в������Բ�Ҫ��
							Method method=clazz.getMethod(reflectMethod);
							//ͨ����ȡ���Ķ���ִ�з�����,�÷���û�в������Բ�Ҫ���ڶ�������,ֱ�Ӱѷ���ֵ��������ֵ������ֵת����string����
							variableValue=method.invoke(object).toString();
							//�����ɵ�������ӵ�д����Ϣ�Ķ�����ȥ
							ExcelUtil.writeBackDatas.add(new WriteBackData("����", variableName, "ReflectValue", variableValue));
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					}
					//��ȡ�����������ı�ע
					String variableRemarks=VariableUtil.getVariableRemarks(variableName);
					//���������ͱ���ֵ�ŵ�map��ȥ
					logger.info("������:��"+variableName+":"+variableRemarks+"��----��������ֵ:��"+variableValue+"��");
					variableNameAndValuesMap.put(variableName, variableValue);
				}
		
	}


	/**ִ��sql��佫��ѯ�������ݸ��µ����������map��(VariableUtil.variableNameAndValuesMap)�������ݻ�д�������ķ���ֵ����
	 * @param sql Ҫִ�е����ݿ�ű�
	 * @param getValueByColunmName  Ҫ��ȡ��ֵ��Ӧ���ֶ���
	 * @param variableKey  Ҫ�滻�ı���key
	 */
	public static void getSpecifiedValueInInterface(String sql,String getValueByColunmName,String variableKey) {
				//����ִ�����ķ���
				Map<String, Object> queryResult=JDBCUtil.query(sql);
				//��ȡ������Ҫ��ֵ
				String lableValue=queryResult.get(getValueByColunmName).toString();
				if (lableValue!=null||lableValue.trim().length()!=0) {
						
					//�����ǻ�ȡ������Ŀid��ӵ�������ȥ
					VariableUtil.variableNameAndValuesMap.put(variableKey, lableValue);
					//��ȡ������Ҫ�任��key�����ı�ע
					String variableRemarks=VariableUtil.getVariableRemarks(variableKey);
					logger.info("����"+variableKey+":"+variableRemarks+"��"+"������ֵ�滻Ϊ��"+lableValue+"��");
					//��д����ʹ�õ�����
					ExcelUtil.writeBackDatas.add(new WriteBackData("����", variableKey, "ReflectValue", lableValue));
				}
		
	}

	
	/**�����������ȡ���Ǳ����ı�ע�õ�
	 * @param variableEnglishName  �������������
	 * @return ���ر�����ֵ
	 */
	public static String getVariableRemarks(String variableEnglishName) {
		for (Variable variable : variables) {
			if (variable.getName().equals(variableEnglishName)) {
				String remarks= variable.getRemarks();
				if (remarks!=null && remarks.trim().length()!=0) {
					return remarks;
				}
			}
		}
		return "������ע��Ϊ������";
	}

	

}
