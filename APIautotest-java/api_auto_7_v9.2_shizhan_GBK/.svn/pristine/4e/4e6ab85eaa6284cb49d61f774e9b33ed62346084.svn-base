package com.qj.api.auto.util;

import java.lang.reflect.Method;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.qj.api.auto.pojo.Case;

/**Case工具类
 * 保存我们所有的用例数据
 * @author Administrator
 *
 */
public class CaseUtil {
		//创建一个集合来保存我们的数据,数据为Case类型,并设置为静态的这样就可以随时取，一个对象代表一行数据
	public static List<Case> cases=new ArrayList<Case>(); 
	//通过静态代码块保证数据都封装进了cases对象中
	static{
		List<Case> list=ExcelUtil.load(propertiesUtil.getExcelPath("excel.PathName"),"用例",Case.class);
		//将list集合添加到cases集合中去
		cases.addAll(list);
	}
	/**通过这个方法获取到指定接口编号的指定列数据
	 * @param apiId 接口编号
	 * @param cellNames 要获取的列的列名
	 * @return 返回我们获取的数据 Object[][]
	 */
	public static Object[][] getCaseDateByApiId(String apiId,String[] cellNames){
		//获取我们case的字节码文件
		Class clazz=Case.class;
		
			//获取到我们指定接口的数据并放到集合中
		List<Case> csList=new ArrayList<Case>();//创建一个数据用来存储符合的数据
	
		//遍历cases集合里面的对象拿出apiId与传进来的对比
		for (Case cs : cases) {
			if(cs.getApiId().equals(apiId)){
				csList.add(cs);//将满足的数据添加进来
		}
			}
			//创建一个二维数组来接收符合的数据里面的列值
			Object[][] datas=new Object[csList.size()][cellNames.length];
		//获取到满足条件的行数据后，将行里面的列数据取出放入到二维数组中供数据提供者调用
			for (int i = 0; i < csList.size(); i++) {//遍历我们满足条件的集合
				Case csArr=csList.get(i);//集合要用get方法取出里面的对象,
				String value="";
				//获取到对象后我们需要通过方法获取到每行中的列数据，那么我们需要知道传进来那些列，我们可以遍历传进来的数组
				for (int j = 0; j < cellNames.length; j++) {
					String cellName=cellNames[j];//获取到传进来的列名
					//通过反射的思想获取来解耦，先通过get+列名得到方法名，然后调用，反射前先要获取到要操作的类的字节码文件，在上面获取
					String methodName="get"+cellName;//根据字段拼接成对应的方法名
					try {
						Method month=clazz.getMethod(methodName);
						value=(String) month.invoke(csArr);//通过我们对应列的get方法获取到对应的值，因为get方法没有参数所以不用传参数
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					datas[i][j]=value;//将值放入到我们的二维数组中
					
				}
			}
		
		return datas;
		}
	

	/*public static void main(String[] args) {
		String[] a={"Params","Desc"};
		Object[][] datas=getCaseDateByApiId("2", a);
		for (Object[] objects : datas) {
			for (Object object : objects) {
				System.out.println(object);
			}
		}
	}*/
}
