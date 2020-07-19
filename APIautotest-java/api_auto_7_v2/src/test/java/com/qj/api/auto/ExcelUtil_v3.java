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

/**������excel�ļ���װ��һ�����������Դ��������к��е�����
 * @author Administrator
 *
 */
public class ExcelUtil_v3 {
	
	/**
	 * @param excelPath �ļ�·��
	 * @param rowNumber �����к����������ʽ
	 * @param cellNumber �����к����������ʽ
	 * @return
	 */
	public static Object[][] datas(String excelPath,int [] rowNumber,int [] cellNumber){
		//׼���ļ�����
			Object [][]datas=null;
				try {//��ȡworkbook����
					Workbook workbook=WorkbookFactory.create(new File(excelPath));
					//��ȡsheetҳ
					Sheet sheet=workbook.getSheet("����");
					//����һ����ά����,�����ά��������ɴ������Ľ����к�-��ʼ�к�+1�����ɽ�����ȡ����-��ʼ����+1
					 datas=new Object[rowNumber.length][cellNumber.length];
					//��ȡ����
					for (int i = 0; i <= rowNumber.length-1; i++) {
						Row row=sheet.getRow(rowNumber[i]-1);//��1ȡ��ʱ��Ҫʹ������
						
						for (int j = 0; j <=cellNumber.length-1; j++) {
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
//				System.out.println("�ж��Ƿ���ȡ��ֵ"+datas[0][0]);
		
		return datas;
		
	}
}
