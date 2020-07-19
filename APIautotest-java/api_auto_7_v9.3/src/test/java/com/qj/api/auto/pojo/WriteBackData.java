package com.qj.api.auto.pojo;

/**
 * 回写数据对象，将我们需要回写的数据先保存在我们对象里面
 * 
 * @author Administrator sheetName sheet页名字 rowIdentifier 行标识 cellNme 列名 result
 *         要写入的数据
 */
public class WriteBackData {
	/**
	 * sheetName sheet页名字
	 */
	private String sheetName;
	/**
	 * rowIdentifier 行标识
	 */
	private String rowIdentifier;
	/**
	 * cellNme 列名
	 */
	private String cellNme;
	/**
	 * result 要写入的数据
	 */
	private String result;

	/**获取sheet页名称
	 * @return
	 */
	public String getShettName() {
		return sheetName;
	}

	/**设置sheet也名称
	 * @param shettName
	 */
	public void setShettName(String shettName) {
		this.sheetName = shettName;
	}

	/**获取行标识 用例编号
	 * @return
	 */
	public String getRowIdentifier() {
		return rowIdentifier;
	}

	public void setRowIdentifier(String rowIdentifier) {
		this.rowIdentifier = rowIdentifier;
	}

	/**获取到列名
	 * @return
	 */
	public String getCellNme() {
		return cellNme;
	}

	public void setCellNme(String cellNme) {
		this.cellNme = cellNme;
	}

	/**获取到要回写的结果
	 * @return
	 */
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	/** 有参构造方法
	 * @param sheetName
	 *            sheet页名字
	 * @param caseId
	 *            用例编号
	 * @param cellNme
	 *            列名
	 * @param result
	 *            要写入的数据
	 */
	public WriteBackData(String sheetName, String rowIdentifier, String cellNme, String result) {
		super();
		this.sheetName = sheetName;
		this.rowIdentifier = rowIdentifier;
		this.cellNme = cellNme;
		this.result = result;
	}

	public WriteBackData() {
		super();
	}

}
