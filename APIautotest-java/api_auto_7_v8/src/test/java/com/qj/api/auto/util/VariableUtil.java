package com.qj.api.auto.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qj.api.auto.pojo.Variable;



/**
 * @author Administrator
 *
 */
public class VariableUtil {
	//����һ��map�������ǵı������ͱ���ֵ
	public static Map<String, String> variableNameAndValuesMap=new HashMap<String, String>();
	//����һ�����ϱ������е����ݶ���Ҫ�ȳ�ʼ��variables���ϲ�Ȼ���澲̬�������ӵ�ʱ��ر���ָ��
	public static List<Variable> variables=new ArrayList<Variable>();
	static{
		//��һ�����ر����������һ�ν�ÿ�з�װ�ɶ���Ȼ��ͳһ��ӵ�����
		List<Variable> list=ExcelUtil.load("src/test/resources/cases_v8.xlsx", "����", Variable.class);
		//������һ������ӵ�variables��ȥ
		variables.addAll(list);
		//���÷����������ͱ�����ֵ���ص�map��ȥ
		loadVariablesToMap();
		
		
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
					variableNameAndValuesMap.put(variableName, variableValue);
				}
		
	}
	

}
