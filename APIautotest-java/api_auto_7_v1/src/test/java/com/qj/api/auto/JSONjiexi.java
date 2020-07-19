package com.qj.api.auto;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class JSONjiexi {
	public static void main(String[] args) {
		String arr="{\"columenLabelAndValues\":{\"leaveamount\":\"0.00\"},\"no\":\"1\"},{\"columenLabelAndValues\":"
				+ "{\"totalNum\":\"0\"},\"no\":\"2\"}";
		//通过我们的net.sf.json.JSONObject中的fromObject(要转换的对象)将数据类型装换成json数据类型
		JSONObject jsonstring=JSONObject.fromObject(arr);
		//用jsonstring调用getString(键)获取到键对应的值
		String value=jsonstring.getString("columenLabelAndValues");
		//将取出的值输出的内容{"leaveamount":"0.00"}
		System.out.println(value);
		//如果我们想取出{"leaveamount":"0.00"}的0.00那么我们需要重新将数据装换成json类型
		JSONObject jsonstring2=JSONObject.fromObject(value);
		//用jsonstring调用getString(键)获取到键对应的值
		String value2=jsonstring2.getString("leaveamount");
		System.out.println(value2);
		
	}
}
