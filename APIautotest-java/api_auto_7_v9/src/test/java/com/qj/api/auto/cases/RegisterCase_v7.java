package com.qj.api.auto.cases;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.qj.api.auto.util.CaseUtil;

import bsh.org.objectweb.asm.Type;

/**测试用例类
 * 用来调用方法和数据提供
 * @author Administrator
 *
 */
public class RegisterCase_v7 extends BaseProcessor {
	
	@DataProvider
	public Object[][] datas(){
		 Object[][] datas=CaseUtil.getCaseDateByApiId("1", cellNames);
//		System.out.println("测试页面"+datas[0][0]);
		return datas;
	}		
			
	
	
	
}
