package com.qj.api.auto.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.stream.FileImageInputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ddf.EscherColorRef.SysIndexProcedure;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;

import com.qj.api.auto.pojo.Case;
import com.qj.api.auto.pojo.Rest;
import com.qj.api.auto.pojo.WriteBackData;

import bsh.Variable;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**将解析excel文件封装成一个方法
 * @author Administrator
 *
 */
public class ExcelUtil {
	//创建一个集合来保存我们回写的数据对象
	public static List<WriteBackData> writeBackDatas=new ArrayList<WriteBackData>();
	
	//创建一个map用来保存用例编号对应的行索引
	public static  Map<String, Integer> rowIdentifierRownumMapping=new HashMap<String, Integer>();
	//创建一个map用来保存列名对应的列索引
	public static Map<String, Integer> cellNameCellnumMapping=new HashMap<String, Integer>();
	//加载出要加载的文件的所有的行号对应的索引和列对应的列索引
	static{
		lodRownumAndCellnumMapping(propertiesUtil.getExcelPath("excel.PathName"),"用例");
	}
	
	/**将我们的excel数据保存在一个二维数组中
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

	/**获取caseId和对应的行索引
	 * 获取列名以及对应的列索引
	 * @param excelPath 文件路径
	 * @param sheetName sheet页面名字
	 */
	public static void lodRownumAndCellnumMapping(String excelPath, String sheetName) {
		//声明一个流对象并赋值为空
		InputStream inputStream=null;
		//先把我们的excel解析出来
		try {
			//创建一个输入流对象
			 inputStream=new FileInputStream(new File(excelPath));
			//创建一个workbook对象
			Workbook workbook=WorkbookFactory.create(inputStream);
			//创建一个sheet对象
			Sheet sheet= workbook.getSheet(sheetName);
			//获取到第一行名字
			Row titleRow=sheet.getRow(0);
			//判断一下是否为空
			if (titleRow != null&&!isEmptyRow(titleRow)) {
				//获取行里面的每一列,先获取到列号
				int cellLastNum=titleRow.getLastCellNum();
				//遍历第一行里面的列,返回的cellLastNum是序号所以不要等于号
				for (int i = 0; i < cellLastNum; i++) {
					//获取到列对象,并给个策略防止空
					Cell cell= titleRow.getCell(i,MissingCellPolicy.CREATE_NULL_AS_BLANK);
					//在获取值之前先将值设置成字符类型
					cell.setCellType(CellType.STRING);
					//获取标题列字段所有的值
					String title=cell.getStringCellValue();
					//因为取出来的名字有中文部分所所以进行截取,并重新赋值给title
					title=title.substring(0, title.indexOf("("));
//					System.out.println(title);
					//通过获取的列拿到列所对应的索引值是多少
					int cellNum=cell.getAddress().getColumn();
					//将列名和对应的列索引一起保存在列名列索引的map里面
					cellNameCellnumMapping.put(title, cellNum);
				}
				
			}
			
			//从第二行开始获取所有的数据行
			//遍历前需要拿到总共有多少条数据
			int lastRowNun=sheet.getLastRowNum();
			//遍历因为返回的行数是索引值所有注意要等于号
			for (int i = 1; i <=lastRowNun; i++) {
				//拿到我们的数据行对象
				Row dataRow=sheet.getRow(i);
				//拿到我们数据行的第一列对象
				Cell firstCellofRow=dataRow.getCell(0,MissingCellPolicy.CREATE_NULL_AS_BLANK);
				//将第一列数据设置成字符串类型
				firstCellofRow.setCellType(CellType.STRING);
				//获取到我们的第一列内容也就是我们的接口编号
				String caseId=firstCellofRow.getStringCellValue();
				//再通过行对象获取到我们的行索引
				int ronum=dataRow.getRowNum();
				//将接口编号和行索引写入到我们的map中去
				rowIdentifierRownumMapping.put(caseId, ronum);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//使用了io流一定要记得关
			if (inputStream!=null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	

	/**解析指定excel表单的数据封装为对象
	 * @param <T>
	 * @param excelPath 文件的相对路径
	 * @param sheetName	sheet页名字
	 * @param clazz  封装对象的class文件
	 */
	public static <T> List<T> load(String excelPath, String sheetName, Class<T> clazz) {//T代表泛型，需要在方法签名上加上这个类型
		//创建一个泛型的集合这样就不用在乎传进来的对象类型是什么了
		List<T> list =new ArrayList<T>();
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
			//因为每一行都需要一个对象，所以放在行循环的位置定义一个对象T为泛型即传进来是什么对象就得到一个什么对象
			T obj=clazz.newInstance();
			Row dataRow=sheet.getRow(i);//获取到每一行数据
			
			//因为可能excel里面会有空行导致获取到的行数不与实际有数值的不一样，所以要对空行进行处理
			if(dataRow==null||isEmptyRow(dataRow)){
				continue;
			}
			//拿到此数据行上面的每一列，将数据封装到对象
			for (int j = 0; j <lastCellNum; j++) {
			Cell dataCell=dataRow.getCell(j,MissingCellPolicy.CREATE_NULL_AS_BLANK);//获取到行的每一列
			dataCell.setCellType(CellType.STRING);//将每一列里面的值转换成字符串类型
			String value=dataCell.getStringCellValue();//获取到每一列的值
//			System.out.println(value);
			//然后将值封装到对象，此时我们需要在前面去创建一个对象，为了降低耦合采用反射
			String motheName="set"+fields[j];//通过拼接得到方法名
//			System.out.println(motheName);
			Method method= clazz.getMethod(motheName, String.class);//通过上面的方法名获取到指定方法
			method.invoke(obj, value);//通过invoke执行方法，传入修改的对象和要传的值
			}
			//将对象添加到list集合中去
			list.add(obj);
			
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		} 	
		return list;
	}
	
	/**这是一个处理excel中有空行时的判断条件方法 
	 * @param dataRow 行数据对象
	 * @return true或者flash 
	 * flash代表不为空，true代表数据是空的
	 * 
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


	/**将对象数据回写到我们的excel里面去
	 * @param excelPath  文件路径
	 * @param sheetName	 sheet页名字
	 * @param caseId	用例编号
	 * @param cellName	要写入的列的列名
	 * @param result	写入的数据
	 */
	public static void writeBackData(String excelPath,String sheetName,String caseId, String cellName, String result) {
		//声明一个输入流
		InputStream inputStream=null;
		//声明一个输出流
		OutputStream  outputStream=null;
		try {
			inputStream = new FileInputStream(new File(excelPath));
			//获取一个workbook对象
			Workbook work=WorkbookFactory.create(inputStream);
			//获取到我们的sheet对象
		 Sheet sheet=work.getSheet(sheetName);
		 //根据caseId取出对应的行索引
		 int rownum=rowIdentifierRownumMapping.get(caseId);
		 //定位到我们的行位置
		 Row row=sheet.getRow(rownum);
		 //根据列名取出对应的列索引
		 int cellnum=cellNameCellnumMapping.get(cellName);
		 //根据列索引定位到列对象
		 Cell cell=row.getCell(cellnum,MissingCellPolicy.CREATE_NULL_AS_BLANK);
		 //获取到列后记得处理下类型，将列对象设置成string类型
		 cell.setCellType(CellType.STRING);
		 //将数据放入到这个列中
		 cell.setCellValue(result);
		 //还需要执行一个写入操作
		 //执行写入之前要指定写入的文件在哪了
		 outputStream=new FileOutputStream(new File(excelPath));
		 work.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
				if (inputStream!=null) {
					inputStream.close();
				}
				if (outputStream!=null) {
					outputStream.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		//通过sheet.getRow()--方法可以拿到我们的行位置
		//通过row.getcell()--方法可以拿到我们的列位置
		//通过cell.setCellValue(value)--方法将数据写入
	}

	/**批量回写数据的方法
	 * @param excelPath 文件路径
	 */
	public static void batchWriteBackDatas(String excelPath) {
		//创建一个输出流因为我们要将数据回写
		FileOutputStream fileOutputStream=null;
		//因为我们要关闭我们的流对象在所以需要在外面声明
		FileInputStream fileInputStream=null;
		try {
			//因为创建一个文档对象需要传一个文件对象或者流对象所以创建一个流对象
			 fileInputStream=new FileInputStream(new File(excelPath));
			//操作我们的excel第一步先要获取我们的文档对象
			Workbook workbook=WorkbookFactory.create(fileInputStream);
			//获取文档对象后要获取我们的sheet页面对象,因为方法中没有传sheetname，但是我们把我们的要写的数据都封装到writeBackDatas集合中了
			//遍历我们的对象找出对应的属性
			for (WriteBackData writeBackData : writeBackDatas) {
				//获取到我们对象中的sheetName
				String sheetName=writeBackData.getShettName();
				//获取到我们的sheet对象
				Sheet sheet=workbook.getSheet(sheetName);
				//获取sheet对象后要获取我们要操作的行对象,此时需要我的rownum行索引,我们可以通过先获取到对象里面的caseid然后获取我们的rownum
				//先获取到对象的caseId
				String rowIdentifier=writeBackData.getRowIdentifier();
				//通过rowIdentifier，从我们的行索引map中获取我们的rownum行索引
				int rownum=rowIdentifierRownumMapping.get(rowIdentifier);
				//最终获取到我们的行对象
				Row row=sheet.getRow(rownum);
				//同理获取到我们的列对象了需要先得到列名，然后在通过列名找出我们的列索引
				//获取列名
				String cellName=writeBackData.getCellNme();
				//获取我们列索引
				int cellnum=cellNameCellnumMapping.get(cellName);
				//最终获取到我们的列对象,注意处理空数据
				Cell cell=row.getCell(cellnum, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				//将列里面的值类修改为string类型
				cell.setCellType(CellType.STRING);
				//把我们的数据设置到我们的列中去,先要获取到我们要写入的内容
				//获取到要写入的内容
				String result=writeBackData.getResult();
				//将result设置到列当中
				cell.setCellValue(result);
				//正式写入还需要写到流里面去,所以我们要创建一个流对象
				fileOutputStream=new FileOutputStream(new File(excelPath));
				workbook.write(fileOutputStream);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//关闭我们的流操作
				if(fileInputStream!=null){
					fileInputStream.close();
				}
				if (fileOutputStream!=null) {
					fileOutputStream.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

	/*//这是为了校验一下对象是不是全部都封装到Case对象里面去了
	 * public static void main(String[] args) {
		for (Case cs :CaseUtil.cases) {
			System.out.println(cs);
		}
	}*/
}
