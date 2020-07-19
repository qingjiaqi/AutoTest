package com.qj.api.auto.pojo;

/**存储变量的实体
 * @author Administrator
 *name 变量的名字
 *value 变量的值
 *reflectClass 反射的类
 *reflectMethod 反射的方法
 *reflectValue  反射获取的值
 *remarks 备注
 */
public class Variable {
	private String name;
	private String value;
	private String remarks;
	private String reflectClass;
	private String reflectMethod;
	private String reflectValue;
	public String getName() {
		return name;
	}
	public String getReflectClass() {
		return reflectClass;
	}
	public void setReflectClass(String reflectClass) {
		this.reflectClass = reflectClass;
	}
	public String getReflectMethod() {
		return reflectMethod;
	}
	public void setReflectMethod(String reflectMethod) {
		this.reflectMethod = reflectMethod;
	}
	public String getReflectValue() {
		return reflectValue;
	}
	public void setReflectValue(String reflectValue) {
		this.reflectValue = reflectValue;
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
