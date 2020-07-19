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



/**处理用例中变量的操作
 * @author Administrator
 *
 */
public class VariableUtil {
	//创建一个日志对象
	public static Logger logger=Logger.getLogger(VariableUtil.class);
	//创建一个map保存我们的变量名和变量值
	public static Map<String, String> variableNameAndValuesMap=new HashMap<String, String>();
	//创建一个集合保存所有的数据对象，要先初始化variables集合不然下面静态代码块添加的时候回报空指针
	public static List<Variable> variables=new ArrayList<Variable>();
	static{
		//第一步加载表单里面的数据一次将每行封装成对象，然后统一添加到集合
		List<Variable> list=ExcelUtil.load(propertiesUtil.getExcelPath("excel.PathName"), "变量", Variable.class);
		//将集合一次性添加到variables中去
		variables.addAll(list);
		//调用方法将变量和变量的值加载到map中去
		loadVariablesToMap();
		//加载出我们变量表中的行列映射
		ExcelUtil.lodRownumAndCellnumMapping(propertiesUtil.getExcelPath("excel.PathName"), "变量");
		
		
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
					//获取到我们的反射的类全路径的值
					String reflectClass=variable.getReflectClass();
					
					//判断一下变量的值是否为空，这一块是为了处理那些不可重复使用的数据用的
					if (variableValue==null||variableValue.trim().length()==0) {
						//判断下反射类是否有值如果没有就赋予一个
						if (reflectClass==null|| reflectClass.trim().length()==0) {
							variableValue="动态变量预留位";
						}else {
					/*	//获取到我们反射的类
						String reflectClass=variable.getReflectClass();*/
						//获取到我们反射的方法
						String reflectMethod=variable.getReflectMethod();
						try {
							//通过类的全路径得到我们的类字节码文件，传入类的全路径
							Class clazz=Class.forName(reflectClass);
							//拿到字节码文件后创建一个对象类型为object的对象
							Object object=clazz.newInstance();
							//通过方法名“reflectMethod”获取一个方法对象method,没有参数所以不要传
							Method method=clazz.getMethod(reflectMethod);
							//通过获取到的对象执行方调用,该方法没有参数所以不要传第二个参数,直接把返回值给到变量值，并将值转换成string类型
							variableValue=method.invoke(object).toString();
							//将生成的数据添加到写入信息的对象中去
							ExcelUtil.writeBackDatas.add(new WriteBackData("变量", variableName, "ReflectValue", variableValue));
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					}
					//获取到变量的中文备注
					String variableRemarks=VariableUtil.getVariableRemarks(variableName);
					//将变量名和变量值放到map中去
					logger.info("变量名:【"+variableName+":"+variableRemarks+"】----》》变量值:【"+variableValue+"】");
					variableNameAndValuesMap.put(variableName, variableValue);
				}
		
	}


	/**执行sql语句将查询到的数据更新到保存变量的map中(VariableUtil.variableNameAndValuesMap)并将数据回写到变量的反射值列中
	 * @param sql 要执行的数据库脚本
	 * @param getValueByColunmName  要获取的值对应的字段名
	 * @param variableKey  要替换的变量key
	 */
	public static void getSpecifiedValueInInterface(String sql,String getValueByColunmName,String variableKey) {
				//调用执行语句的方法
				Map<String, Object> queryResult=JDBCUtil.query(sql);
				//获取到我们要的值
				String lableValue=queryResult.get(getValueByColunmName).toString();
				if (lableValue!=null||lableValue.trim().length()!=0) {
						
					//将我们获取到的项目id添加到变量中去
					VariableUtil.variableNameAndValuesMap.put(variableKey, lableValue);
					//获取到我们要变换的key的中文备注
					String variableRemarks=VariableUtil.getVariableRemarks(variableKey);
					logger.info("将【"+variableKey+":"+variableRemarks+"】"+"变量的值替换为【"+lableValue+"】");
					//回写我们使用的数据
					ExcelUtil.writeBackDatas.add(new WriteBackData("变量", variableKey, "ReflectValue", lableValue));
				}
		
	}

	
	/**这个方法是来取我们变量的备注用的
	 * @param variableEnglishName  闯入变量的名字
	 * @return 返回变量的值
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
		return "变量备注名为空请检查";
	}

	

}
