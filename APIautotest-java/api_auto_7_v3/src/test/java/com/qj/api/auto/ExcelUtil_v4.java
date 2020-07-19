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

/**������excel�ļ���װ��һ������
 * @author Administrator
 *
 */
public class ExcelUtil_v4 {
	
	/**
	 * @param excelPath �ļ���ַ
	 * @param rowNumber �к�int[]
	 * @param cellNumber �к�int[]
	 * @param sheetName sheetҳ����
	 * @return �Զ�ά�������ʽ��������
	 */
	public static Object[][] datas(String excelPath,int [] rowNumber,int [] cellNumber,String sheetName){
		//׼���ļ�����
			Object [][]datas=null;
				try {
					Workbook workbook=WorkbookFactory.create(new File(excelPath));//��ȡwokbook����
					//��ȡsheetҳ
					Sheet sheet=workbook.getSheet(sheetName);
					//����һ����ά����,�����ά��������������鳤�Ⱦ��������ɴ������������鳤�Ⱦ���
					 datas=new Object[rowNumber.length][cellNumber.length];
					//��ȡ��ÿһ��
					for (int i = 0; i <= rowNumber.length-1; i++) {
						Row row=sheet.getRow(rowNumber[i]-1);//��ȡ��ʱ��Ҫʹ������
						
						for (int j = 0; j <= cellNumber.length-1; j++) {
							Cell cell=row.getCell(cellNumber[j]-1);//��ȡ��ʱ��Ҫʹ������
							//���е�ֵת�����ַ�������
							cell.setCellType(CellType.STRING);
							//ȡ�����е�ֵ
							String value=cell.getStringCellValue();
							//ȡ��ֵ֮����Ҫ��ֵ����һ����ά������ȥ
							datas[i][j]=value;
						}
						
					}
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
//		System.out.println("���ǽ���ҳ���"+datas[0][0]);
		return datas;
		
	}
}
