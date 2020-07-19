package com.qj.api.auto.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.qj.api.auto.pojo.Variable;
import com.qj.api.auto.pojo.WriteBackData;



/**
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
		List<Variable> list=ExcelUtil.load(propertiesUtil.getExcelPath(), "����", Variable.class);
		//������һ������ӵ�variables��ȥ
		variables.addAll(list);
		//���÷����������ͱ�����ֵ���ص�map��ȥ
		loadVariablesToMap();
		//���س����Ǳ������е�����ӳ��
		ExcelUtil.lodRownumAndCellnumMapping(propertiesUtil.getExcelPath(), "����");
		
		
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
					
					//�ж�һ�±�����ֵ�Ƿ�Ϊ�գ���һ����Ϊ�˴�����Щ�����ظ�ʹ�õ������õ�
					if (variableValue==null||variableValue.trim().length()==0) {
						//��ȡ�����Ƿ������
						String reflectClass=variable.getReflectClass();
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
					
					
					//���������ͱ���ֵ�ŵ�map��ȥ
					logger.info("������:"+variableName+"����ֵ:"+variableValue);
					variableNameAndValuesMap.put(variableName, variableValue);
				}
		
	}
	

}
