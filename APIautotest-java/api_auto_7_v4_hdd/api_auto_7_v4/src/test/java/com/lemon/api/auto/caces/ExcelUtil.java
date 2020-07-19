package com.lemon.api.auto.caces;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	public static Map<String,Integer> caseIdRownumMapping = new HashMap<String, Integer>();
	public static Map<String, Integer> cellNameCellnumMapping = new HashMap<String, Integer>();
	public static List<WriteBackData> writeBackDatas = new ArrayList<WriteBackData>();
	static {
		loadRownumAndCellnumMapping("src/test/resources/cases_v6.xlsx","用例");
	}
	
	/**
	 * @param excelPath：文件路径
	 * @param caseName：sheet名称
	 * @param rows：哪几行
	 * @param cells：哪几列
	 * @return
	 */
	public static Object[][] datas(String excelPath,String caseName,int[] rows,int[] cells ){
		Object[][] datas = null;
		try {
			datas = new Object[rows.length][cells.length];
			//获取workbook对象
			Workbook workbook = WorkbookFactory.create(new File(excelPath));
			//获取sheet对象,导ss的包
			Sheet sheet = workbook.getSheet(caseName);
			for (int i = 0; i < rows.length; i++) {
				//获取行，这里是获取行索引
				Row row = sheet.getRow(rows[i]-1);
				for (int j = 0; j < cells.length; j++) {
					//获取列，这里是获取列索引
					Cell cell = row.getCell(cells[j]-1);
					//将列设置为字符串类型
					cell.setCellType(CellType.STRING);
					String value = cell.getStringCellValue();
					datas[i][j] = value;
				}
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return datas;
	}


	/**获取caseid以及它对应的行索引
	 * 获取cellname以及它对应的列索引
	 * @param excelPath
	 * @param sheetName
	 */
	private static void loadRownumAndCellnumMapping(String excelPath, String sheetName) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(excelPath));
			Workbook workbook = WorkbookFactory.create(inputStream);
			Sheet sheet = workbook.getSheet(sheetName);
			//获取第一行（标题行）
			Row titleRow = sheet.getRow(0);
			if(titleRow!=null&&!isEmptyRow(titleRow)) {
				int lastCellnum = titleRow.getLastCellNum();
				//循环处理标题行的每一列
				for (int i = 0; i < lastCellnum; i++) {
					Cell cell = titleRow.getCell(i,MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					String title = cell.getStringCellValue();
					title = title.substring(0, title.indexOf("("));
					int cellnum = cell.getAddress().getColumn();
					cellNameCellnumMapping.put(title, cellnum);
				}
				//从第二行开始，获取所有的数据行
				int lastRownum = sheet.getLastRowNum();
				for (int i = 1; i <= lastRownum; i++) {
					Row dataRow = sheet.getRow(i);
					Cell firstCellOfRow = dataRow.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					firstCellOfRow.setCellType(CellType.STRING);
					String caseId = firstCellOfRow.getStringCellValue();
					int rownum = dataRow.getRowNum();
					caseIdRownumMapping.put(caseId, rownum);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(inputStream!=null) {
					inputStream.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
	}


	/**解析指定Excel表单的数据，封装为对象
	 * @param <T>
	 * @param excelPath 解析Excel路径
	 * @param sheetName Excel表单名
	 * @param clazz 
	 */
	public static <T> void load(String excelPath, String sheetName, Class<T> clazz) {
		//获取workbook对象
		try {
			Workbook workbook = WorkbookFactory.create(new File(excelPath));
			//获取sheet对象
			Sheet sheet = workbook.getSheet(sheetName);
			//获取第一行
			Row titleRow = sheet.getRow(0);
			//获取最后一列的列号
			int lastCellNum = titleRow.getLastCellNum();
			//循环处理每一列,for循环要取列号
			String[] filds = new String[lastCellNum];
			for (int i = 0; i < lastCellNum; i++) {
				//根据列索引获取对应的列
				Cell cell = titleRow.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				//将列设置成字符串
				cell.setCellType(CellType.STRING);
				//获取列的值
				String title = cell.getStringCellValue();
				title = title.substring(0, title.indexOf("("));
				//将列名添加到集合里
				filds[i] = title;
			}
			int lastRowIndex = sheet.getLastRowNum();
			//循环处理每一个数据行
			for (int i = 1; i <= lastRowIndex ; i++) {
				Object obj = clazz.newInstance();
				//拿到第一个数据行
				Row dataRow = sheet.getRow(i);
				if (dataRow==null||isEmptyRow(dataRow)) {
					continue;
				}
				//拿到此数据行的每一列
				for (int j = 0; j < lastCellNum; j++) {
					Cell cell = dataRow.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					String value = cell.getStringCellValue();
					//获取要反射的方法名
					String methodName = "set"+filds[j];
					Method method = clazz.getMethod(methodName, String.class);
					method.invoke(obj, value);
				}
				//将对象添加到集合里
				if(obj instanceof Case) {
					Case cs = (Case) obj;
					CaseUtil.cases.add(cs);
				}else if(obj instanceof Rest) {
					Rest rs = (Rest) obj;
					RestUtil.rests.add(rs);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private static boolean isEmptyRow(Row dataRow) {
		int lastCellNum = dataRow.getLastCellNum();
		for (int i = 0; i < lastCellNum; i++) {
			Cell cell = dataRow.getCell(i,MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cell.setCellType(CellType.STRING);
			String value = cell.getStringCellValue();
			if(value!=null&&value.trim().length()>0) {
				return false;
			}
		}
		return true;
	}


	/**回写接口响应报文
	 * @param excelPath
	 * @param sheetName
	 * @param caseId
	 * @param cellName
	 * @param result
	 */
	public static void writeBackData(String excelPath,String sheetName,String caseId, String cellName, String result) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = new FileInputStream(new File(excelPath));
			Workbook workbook = WorkbookFactory.create(inputStream);
			Sheet sheet = workbook.getSheet(sheetName);
			int rownum = caseIdRownumMapping.get(caseId);
			Row row = sheet.getRow(rownum);
			int cellnum = cellNameCellnumMapping.get(cellName);
			Cell cell = row.getCell(cellnum, MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cell.setCellType(CellType.STRING);
			cell.setCellValue(result);
			outputStream = new FileOutputStream(new File(excelPath));
			workbook.write(outputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(inputStream!=null) {
					inputStream.close();
				}
				if(outputStream!=null) {
					outputStream.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}


	/**批量回写数据的方法
	 * @param excelPath 文件路径
	 */
	public static void batchWriteBackDatas(String excelPath) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = new FileInputStream(new File(excelPath));
			Workbook workbook = WorkbookFactory.create(inputStream);
			//循环读取数据
			for (WriteBackData wData : writeBackDatas) {
				//获取sheetName
				String sheetName = wData.getSheetName();
				Sheet sheet = workbook.getSheet(sheetName);
				String caseId = wData.getCaseId();
				int rownum = caseIdRownumMapping.get(caseId);
				Row row = sheet.getRow(rownum);
				String cellName = wData.getCellName();
				int cellnum = cellNameCellnumMapping.get(cellName);
				Cell cell = row.getCell(cellnum, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING);
				String result = wData.getResult();
				cell.setCellValue(result);
			}
			outputStream = new FileOutputStream(new File(excelPath));
			workbook.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(inputStream!=null){
					inputStream.close();
				}
				if (outputStream!=null) {
					outputStream.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
