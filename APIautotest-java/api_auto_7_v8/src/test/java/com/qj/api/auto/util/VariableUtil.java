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
	//创建一个map保存我们的变量名和变量值
	public static Map<String, String> variableNameAndValuesMap=new HashMap<String, String>();
	//创建一个集合保存所有的数据对象，要先初始化variables集合不然下面静态代码块添加的时候回报空指针
	public static List<Variable> variables=new ArrayList<Variable>();
	static{
		//第一步加载表单里面的数据一次将每行封装成对象，然后统一添加到集合
		List<Variable> list=ExcelUtil.load("src/test/resources/cases_v8.xlsx", "变量", Variable.class);
		//将集合一次性添加到variables中去
		variables.addAll(list);
		//调用方法将变量和变量的值加载到map中去
		loadVariablesToMap();
		
		
	}


	/**替换参数里面的变量为数据(参数化)
	 * @parameter parameter 含有变量的参数
	 * @return 返回将变量替换成实际数据的参数
	 */
	public static String replaceVariables(String parameter) {
		//循环变量名和变量值的map，先取出变量名就是我门的key
		Set<String> variableNames=variableNameAndValuesMap.keySet();
		for (String variableName : variableNames) {
			//如果参数中包含了变量名
			if (parameter.contains(variableName)) {
				//将参数中的变量全部替换成值第一个参数是变量的名字，第二个参数是要赋予的值
				parameter=parameter.replace(variableName, variableNameAndValuesMap.get(variableName));
			}
		}
		
		return parameter;
	}


	/**
	 * 遍历变量集合将封装的变量名和对应的变量值放入到variableNameAndValuesMap(变量名和变量值)当中去
	 */
	private static void loadVariablesToMap() {
		//循环variables集合拿出所有的字段和值
				for (Variable variable : variables) {
					//获取到变量的名字
					String variableName=variable.getName();
					//获取到变量的值
					String variableValue=variable.getValue();
					variableNameAndValuesMap.put(variableName, variableValue);
				}
		
	}
	

}
