package com.qj.api.auto;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**包含datas通过制定的行号列号解析excel的方法和load将excel中的所有数据封装到对象中去的方法
 * @author Administrator
 *
 */
public class ExcelUtil_v4 {
	
	/** 通过传入行号，列号的方式获取解析excel的方法
	 * @param excelPath 文件地址
	 * @param rowNumber 行号int[]
	 * @param cellNumber 列号int[]
	 * @param sheetName sheet页名字
	 * @return 以二维数组的形式返回数据
	 */
	public static Object[][] datas(String excelPath,int [] rowNumber,int [] cellNumber,String sheetName){
		//准备文件对象
			Object [][]datas=null;
				try {
					Workbook workbook=WorkbookFactory.create(new File(excelPath));//获取wokbook对象
					//获取sheet页
					Sheet sheet=workbook.getSheet(sheetName);
					//创建一个二维数组
					 datas=new Object[rowNumber.length][cellNumber.length];
					//获取到每一行
					for (int i = 0; i <= rowNumber.length-1; i++) {
						Row row=sheet.getRow(rowNumber[i]-1);//获取的时候要使用索引
						
						for (int j = 0; j <= cellNumber.length-1; j++) {
							Cell cell=row.getCell(cellNumber[j]-1);//获取的时候要使用索引
							//将列的值转换成字符串类型
							cell.setCellType(CellType.STRING);
							//取出列中的值
							String value=cell.getStringCellValue();
							//取出值之后需要将值放在一个二维数组中去
							datas[i][j]=value;
						}
						
					}
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
//		System.out.println("这是解析页面的"+datas[0][0]);
		return datas;
		
	}

	/**解析指定excel表单的数据封装为对象
	 * @param <T>
	 * @param excelPath 文件的相对路径
	 * @param sheetName	sheet页名字
	 * @param clazz 
	 */
	public static <T> void load(String excelPath, String sheetName, Class<T> clazz) {//T代表泛型，需要在方法签名上加上这个类型
		//解析excel步骤
		try {
		//获取workbook对象
		Workbook workbook=WorkbookFactory.create(new File(excelPath));
		//获取sheet对象
		Sheet sheet=workbook.getSheet(sheetName);
		
		//因为要根据不同的列名来调用不同的set方法所以需要修拿出表头信息字段
		Row titleRow=sheet.getRow(0);
		//那么我们怎么知道有多少列呢,通过getLastCellNum()方法获取到列数
		int lastCellNum=titleRow.getLastCellNum();
		String [] fields=new String[lastCellNum];//这个数组是用来保存表头字段的
		//获取到每一列的字段名,并将他保存到数组中去；lastCellNum是获取的行号所以不要=号
		for (int i = 0; i < lastCellNum; i++) {
			//根据索引获取到对应的列
			Cell cell=titleRow.getCell(i,MissingCellPolicy.CREATE_NULL_AS_BLANK);//获取到第一行每一列的值
			cell.setCellType(CellType.STRING);//将列值转换成string类型
			String titl=cell.getStringCellValue();//获取到每一列的值
			titl=titl.substring(0, titl.indexOf("("));//截取出字母部分	
//			System.out.println(titl);
			fields[i]=titl;//将表头字段添加到数组中保存
		}
		
		//取所有数据，首先要知道总共有多少行；
		int lastRowIndex=sheet.getLastRowNum();//获取到最后一行的索引值
		//知道有多少行了我们可以遍历拿到所有的行
		for (int i = 1; i <= lastRowIndex; i++) {
			//因为每一行都需要一个Case对象所以放在行循环的位置
			Object obj=clazz.newInstance();
			Row dataRow=sheet.getRow(i);//获取到每一行数据
			
			//因为可能excel里面会有空行导致获取到的行数不与实际有数值的不一样，所以要对空行进行处理
			if(dataRow==null||isEmptyRow(dataRow)){
				continue;
			}
			
			for (int j = 0; j <lastCellNum ; j++) {
			Cell dataCell=dataRow.getCell(j);//获取到行的每一列
			dataCell.setCellType(CellType.STRING);//将每一列里面的值转换成字符串类型
			String value=dataCell.getStringCellValue();//获取到每一列的值
			System.out.println(value);
			//然后将值封装到对象，此时我们需要在前面去创建一个对象，为了降低耦合采用反射
			String motheName="set"+fields[j];//得到方法名
			Method method= clazz.getMethod(motheName, String.class);//通过上面的方法名获取到指定方法
			method.invoke(obj, value);//通过invoke执行方法，传入修改的对象和要传的值
			}
			//将对象添加到解析对象的集合中去
			if (obj instanceof Case) {//instanceof用来判断obj里面的对象是不是case
				Case case1=(Case)obj;
				CaseUtil.cases.add(case1);
			}else if(obj instanceof Rest) {
				Rest rest=(Rest) obj;
				RestUtil.rests.add(rest);
			}
			
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		} 	
	}
	

	/**这是一个处理excel中有空行时的判断条件方法
	 * @param dataRow 行数据
	 * @return true或者flash
	 */
	private static boolean isEmptyRow(Row dataRow) {
		int lastCellNum=dataRow.getLastCellNum();//获取到总共多少列因为传进来的就是行
		for (int i = 0; i < lastCellNum; i++) {
			Cell cell=dataRow.getCell(i,MissingCellPolicy.CREATE_NULL_AS_BLANK);//获取到每一列
			cell.setCellType(CellType.STRING);//将列值设置成字符串类型
			String value=cell.getStringCellValue();//获取到每一列的值
			if(value!=null&&value.trim().length()>0){//trim()这个方式是取出值两端的空格，去除后检查长度是否大于0
				return false;//满足说明不为空返回false
			}
		}
		return true;
	}

	/*//这是为了校验一下对象是不是全部都封装到Case对象里面去了
	 * public static void main(String[] args) {
		for (Case cs :CaseUtil.cases) {
			System.out.println(cs);
		}
	}*/
}
