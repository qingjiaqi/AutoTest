package com.qj.api.auto.pojo;

/**回写数据对象，将我们需要回写的数据先保存在我们对象里面
 * @author Administrator
 *
 */
public class WriteBackData {
	/**
	 * sheetName sheet页名字
	 * caseId	用例编号
	 * cellNme	列名
	 * result	要写入的数据
	 */
	private String sheetName;
	private String  caseId;
	private  String cellNme;
	private String result;
	public String getShettName() {
		return sheetName;
	}
	public void setShettName(String shettName) {
		this.sheetName = shettName;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getCellNme() {
		return cellNme;
	}
	public void setCellNme(String cellNme) {
		this.cellNme = cellNme;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
	/**
	 * @param sheetName sheet页名字
	 * @param caseId	用例编号
	 * @param cellNme	列名
	 * @param result	要写入的数据
	 */
	public WriteBackData(String sheetName, String caseId, String cellNme, String result) {
		super();
		this.sheetName = sheetName;
		this.caseId = caseId;
		this.cellNme = cellNme;
		this.result = result;
	}
	
	public WriteBackData() {
		super();
	}
	
}
