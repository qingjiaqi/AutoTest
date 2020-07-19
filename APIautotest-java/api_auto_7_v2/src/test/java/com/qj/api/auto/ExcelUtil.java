package com.qj.api.auto;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**解析excel文件
 * @author Administrator
 *
 */
public class ExcelUtil {
	public static Object[][] datas(){
		//准备文件对象
		String excelPath="src/test/resources/cases_v1.xls";
			Object [][]datas=null;
				try {//获取workbook对象
					Workbook workbook=WorkbookFactory.create(new File(excelPath));
					//获取sheet页
					Sheet sheet=workbook.getSheet("用例");
					//创建一个二维数组,这个二维数组是6行2列的，也可以说是有6个一维数组每个一维数组中有两个值
					 datas=new Object[6][2];
					//获取到行从第二行开始取
					for (int i = 1; i <= 6; i++) {
						Row row=sheet.getRow(i);
						//从第6列开始取取到第七列
						for (int j = 5; j <= 6; j++) {
							Cell cell=row.getCell(j);
							//将列的值转换成字符串类型
							cell.setCellType(CellType.STRING);
							//取出列中的值
							String value=cell.getStringCellValue();
							//取出值之后需要将值放在一个二维数组中去
							datas[i-1][j-5]=value;
						}
						
					}
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		
		return datas;
		
	}
	public static void main(String[] args) {
		Object[][] datas=datas();
		for (int i = 0; i < datas.length; i++) {
			Object[] data=datas[i];
			for (int j = 0; j < data.length; j++) {
				System.err.println(data[j]);
			}
		}
	}
}
