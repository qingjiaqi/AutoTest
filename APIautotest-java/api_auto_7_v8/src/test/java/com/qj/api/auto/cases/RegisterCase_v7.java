package com.qj.api.auto.cases;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.qj.api.auto.util.CaseUtil;

import bsh.org.objectweb.asm.Type;

/**����������
 * �������÷����������ṩ
 * @author Administrator
 *
 */
public class RegisterCase_v7 extends BaseProcessor {
	
	@DataProvider
	public Object[][] datas(){
		 Object[][] datas=CaseUtil.getCaseDateByApiId("1", cellNames);
//		System.out.println("����ҳ��"+datas[0][0]);
		return datas;
	}		
			
	
	
	
}
