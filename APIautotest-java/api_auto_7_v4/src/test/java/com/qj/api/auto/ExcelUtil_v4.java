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

/**����datasͨ���ƶ����к��кŽ���excel�ķ�����load��excel�е��������ݷ�װ��������ȥ�ķ���
 * @author Administrator
 *
 */
public class ExcelUtil_v4 {
	
	/** ͨ�������кţ��кŵķ�ʽ��ȡ����excel�ķ���
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
					//����һ����ά����
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

	/**����ָ��excel�������ݷ�װΪ����
	 * @param <T>
	 * @param excelPath �ļ������·��
	 * @param sheetName	sheetҳ����
	 * @param clazz 
	 */
	public static <T> void load(String excelPath, String sheetName, Class<T> clazz) {//T�����ͣ���Ҫ�ڷ���ǩ���ϼ����������
		//����excel����
		try {
		//��ȡworkbook����
		Workbook workbook=WorkbookFactory.create(new File(excelPath));
		//��ȡsheet����
		Sheet sheet=workbook.getSheet(sheetName);
		
		//��ΪҪ���ݲ�ͬ�����������ò�ͬ��set����������Ҫ���ó���ͷ��Ϣ�ֶ�
		Row titleRow=sheet.getRow(0);
		//��ô������ô֪���ж�������,ͨ��getLastCellNum()������ȡ������
		int lastCellNum=titleRow.getLastCellNum();
		String [] fields=new String[lastCellNum];//������������������ͷ�ֶε�
		//��ȡ��ÿһ�е��ֶ���,���������浽������ȥ��lastCellNum�ǻ�ȡ���к����Բ�Ҫ=��
		for (int i = 0; i < lastCellNum; i++) {
			//����������ȡ����Ӧ����
			Cell cell=titleRow.getCell(i,MissingCellPolicy.CREATE_NULL_AS_BLANK);//��ȡ����һ��ÿһ�е�ֵ
			cell.setCellType(CellType.STRING);//����ֵת����string����
			String titl=cell.getStringCellValue();//��ȡ��ÿһ�е�ֵ
			titl=titl.substring(0, titl.indexOf("("));//��ȡ����ĸ����	
//			System.out.println(titl);
			fields[i]=titl;//����ͷ�ֶ���ӵ������б���
		}
		
		//ȡ�������ݣ�����Ҫ֪���ܹ��ж����У�
		int lastRowIndex=sheet.getLastRowNum();//��ȡ�����һ�е�����ֵ
		//֪���ж����������ǿ��Ա����õ����е���
		for (int i = 1; i <= lastRowIndex; i++) {
			//��Ϊÿһ�ж���Ҫһ��Case�������Է�����ѭ����λ��
			Object obj=clazz.newInstance();
			Row dataRow=sheet.getRow(i);//��ȡ��ÿһ������
			
			//��Ϊ����excel������п��е��»�ȡ������������ʵ������ֵ�Ĳ�һ��������Ҫ�Կ��н��д���
			if(dataRow==null||isEmptyRow(dataRow)){
				continue;
			}
			
			for (int j = 0; j <lastCellNum ; j++) {
			Cell dataCell=dataRow.getCell(j);//��ȡ���е�ÿһ��
			dataCell.setCellType(CellType.STRING);//��ÿһ�������ֵת�����ַ�������
			String value=dataCell.getStringCellValue();//��ȡ��ÿһ�е�ֵ
			System.out.println(value);
			//Ȼ��ֵ��װ�����󣬴�ʱ������Ҫ��ǰ��ȥ����һ������Ϊ�˽�����ϲ��÷���
			String motheName="set"+fields[j];//�õ�������
			Method method= clazz.getMethod(motheName, String.class);//ͨ������ķ�������ȡ��ָ������
			method.invoke(obj, value);//ͨ��invokeִ�з����������޸ĵĶ����Ҫ����ֵ
			}
			//��������ӵ���������ļ�����ȥ
			if (obj instanceof Case) {//instanceof�����ж�obj����Ķ����ǲ���case
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
	

	/**����һ������excel���п���ʱ���ж���������
	 * @param dataRow ������
	 * @return true����flash
	 */
	private static boolean isEmptyRow(Row dataRow) {
		int lastCellNum=dataRow.getLastCellNum();//��ȡ���ܹ���������Ϊ�������ľ�����
		for (int i = 0; i < lastCellNum; i++) {
			Cell cell=dataRow.getCell(i,MissingCellPolicy.CREATE_NULL_AS_BLANK);//��ȡ��ÿһ��
			cell.setCellType(CellType.STRING);//����ֵ���ó��ַ�������
			String value=cell.getStringCellValue();//��ȡ��ÿһ�е�ֵ
			if(value!=null&&value.trim().length()>0){//trim()�����ʽ��ȡ��ֵ���˵Ŀո�ȥ�����鳤���Ƿ����0
				return false;//����˵����Ϊ�շ���false
			}
		}
		return true;
	}

	/*//����Ϊ��У��һ�¶����ǲ���ȫ������װ��Case��������ȥ��
	 * public static void main(String[] args) {
		for (Case cs :CaseUtil.cases) {
			System.out.println(cs);
		}
	}*/
}
