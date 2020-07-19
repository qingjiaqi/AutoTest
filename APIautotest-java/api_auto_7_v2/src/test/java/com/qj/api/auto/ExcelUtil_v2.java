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
public class ExcelUtil_v2 {
	/** ����excel�ļ�
	 * @param excelPath �����ļ�·�� 
	 * @param startRow	��ʼ�������к�
	 * @param endRow	�����������к�
	 * @param startcell	��ʼ�������к�
	 * @param endcell	�����������к�
	 * @return	datas ����object [][]���͵Ķ�ά����
	 */
	public static Object[][] datas(String excelPath,int startRow,int endRow,int startcell,int endcell){
		//׼���ļ�����
			Object [][]datas=null;
				try {//��ȡworkbook����
					Workbook workbook=WorkbookFactory.create(new File(excelPath));
					//��ȡsheetҳ
					Sheet sheet=workbook.getSheet("����");
					//����һ����ά����,�����ά��������ɴ������Ľ����к�-��ʼ�к�+1�����ɽ�����ȡ����-��ʼ����+1
					 datas=new Object[endRow-startRow+1][endcell-startcell+1];
					//��ȡ����
					for (int i = startRow; i <= endRow; i++) {
						Row row=sheet.getRow(i-1);//��ȡ��ʱ��Ҫʹ������
						
						for (int j = startcell; j <= endcell; j++) {
							Cell cell=row.getCell(j-1);//��ȡ��ʱ��Ҫʹ������
							//���е�ֵת�����ַ�������21
							cell.setCellType(CellType.STRING);
							//ȡ�����е�ֵ
							String value=cell.getStringCellValue();
							//ȡ��ֵ֮����Ҫ��ֵ����һ����ά������ȥ
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
