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
public class ExcelUtil_v2 {
	/** 解析excel文件
	 * @param excelPath 解析文件路径 
	 * @param startRow	开始解析的行号
	 * @param endRow	结束解析的行号
	 * @param startcell	开始解析的列号
	 * @param endcell	结束解析的列号
	 * @return	datas 类型object [][]类型的二维数组
	 */
	public static Object[][] datas(String excelPath,int startRow,int endRow,int startcell,int endcell){
		//准备文件对象
			Object [][]datas=null;
				try {//获取workbook对象
					Workbook workbook=WorkbookFactory.create(new File(excelPath));
					//获取sheet页
					Sheet sheet=workbook.getSheet("用例");
					//创建一个二维数组,这个二维数组的行由传进来的结束行号-开始行号+1，列由结束获取的列-开始的列+1
					 datas=new Object[endRow-startRow+1][endcell-startcell+1];
					//获取到行
					for (int i = startRow; i <= endRow; i++) {
						Row row=sheet.getRow(i-1);//获取的时候要使用索引
						
						for (int j = startcell; j <= endcell; j++) {
							Cell cell=row.getCell(j-1);//获取的时候要使用索引
							//将列的值转换成字符串类型21
							cell.setCellType(CellType.STRING);
							//取出列中的值
							String value=cell.getStringCellValue();
							//取出值之后需要将值放在一个二维数组中去
							datas[i-startRow][j-startcell]=value;
						}
						
					}
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		
		return datas;
		
	}
}
