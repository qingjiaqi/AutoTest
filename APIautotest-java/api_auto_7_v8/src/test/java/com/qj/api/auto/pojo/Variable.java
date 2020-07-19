package com.qj.api.auto.pojo;

/**存储变量的实体
 * @author Administrator
 *name 变量的名字
 *value 变量的值
 *remarks 备注
 */
public class Variable {
	private String name;
	private String value;
	private String remarks;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
