package com.qj.api.auto.pojo;

/**��д���ݶ��󣬽�������Ҫ��д�������ȱ��������Ƕ�������
 * @author Administrator
 *
 */
public class WriteBackData {
	/**
	 * sheetName sheetҳ����
	 * caseId	�������
	 * cellNme	����
	 * result	Ҫд�������
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
	 * @param sheetName sheetҳ����
	 * @param caseId	�������
	 * @param cellNme	����
	 * @param result	Ҫд�������
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
