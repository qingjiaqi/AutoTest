package com.qj.api.auto.pojo;

import java.util.Map;

public class DBQueryResult {
	/**
	 * 脚本编号
	 */
	private String no;
	/**
	 * 脚本执行后查到的数据，保存在map中key表示的字段名或别名，value表示字段的值
	 */
	private Map<String,Object> columenLabelAndValues;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public Map<String, Object> getColumenLabelAndValues() {
		return columenLabelAndValues;
	}
	public void setColumenLabelAndValues(Map<String, Object> columenLabelAndValues) {
		this.columenLabelAndValues = columenLabelAndValues;
	}
}
