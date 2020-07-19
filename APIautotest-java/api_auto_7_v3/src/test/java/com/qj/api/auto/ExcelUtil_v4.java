package com.qj.api.auto;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**将解析excel文件封装成一个方法
 * @author Administrator
 *
 */
public class ExcelUtil_v4 {
	
	/**
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
					//创建一个二维数组,这个二维数组的行由行数组长度决定，列由传进来的列数组长度决定
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
}
