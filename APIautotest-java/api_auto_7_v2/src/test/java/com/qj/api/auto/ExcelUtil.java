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

/**����excel�ļ�
 * @author Administrator
 *
 */
public class ExcelUtil {
	public static Object[][] datas(){
		//׼���ļ�����
		String excelPath="src/test/resources/cases_v1.xls";
			Object [][]datas=null;
				try {//��ȡworkbook����
					Workbook workbook=WorkbookFactory.create(new File(excelPath));
					//��ȡsheetҳ
					Sheet sheet=workbook.getSheet("����");
					//����һ����ά����,�����ά������6��2�еģ�Ҳ����˵����6��һά����ÿ��һά������������ֵ
					 datas=new Object[6][2];
					//��ȡ���дӵڶ��п�ʼȡ
					for (int i = 1; i <= 6; i++) {
						Row row=sheet.getRow(i);
						//�ӵ�6�п�ʼȡȡ��������
						for (int j = 5; j <= 6; j++) {
							Cell cell=row.getCell(j);
							//���е�ֵת�����ַ�������
							cell.setCellType(CellType.STRING);
							//ȡ�����е�ֵ
							String value=cell.getStringCellValue();
							//ȡ��ֵ֮����Ҫ��ֵ����һ����ά������ȥ
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
