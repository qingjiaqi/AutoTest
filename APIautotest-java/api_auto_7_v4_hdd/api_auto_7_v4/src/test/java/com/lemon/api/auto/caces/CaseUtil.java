package com.lemon.api.auto.caces;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**用例工具类
 * @author Administrator
 *
 */
public class CaseUtil {

	//保存所有的用例对象(共享数据)
	public static List<Case> cases = new ArrayList<Case>();
	static {
		//将所有数据封装到cases
		ExcelUtil.load("src/test/resources/cases_v6.xlsx","用例",Case.class);
	}
	/**根据接口编号拿到对应的接口测试数据
	 * @param apiId 指定接口编号
	 * @param cellNames 要拿对应数据的列名
	 * @return
	 */
	public static Object[][] getCaseDatasByApiId(String apiId,String [] cellNames) {
		Class<Case> clazz = Case.class;
		ArrayList<Case> csList = new ArrayList<Case>();
		//通过循环找出指定接口编号对应的用例数据
		for (Case cs : cases) {
			//把用例编号相同的添加到集合里
			if(cs.getApiId().equals(apiId)) {
				csList.add(cs);
			}
		}
		Object[][] datas = new Object[csList.size()][cellNames.length];
		//将数据从集合中取出来
		for (int i = 0; i < csList.size(); i++) {
			Case cs = csList.get(i);
			for (int j = 0; j < cellNames.length; j++) {
				//要反射的方法名
				String methodName = "get"+cellNames[j];
				Method method;
				try {
					method = clazz.getMethod(methodName);
					String value = (String) method.invoke(cs);
					datas[i][j] = value;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return datas;
	}
	
}
