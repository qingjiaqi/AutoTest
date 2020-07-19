package com.qj.api.auto.util;

import org.testng.Assert;

public class AssertUtil {

	/**自定义类库用来对比我们的期望结果和实际测试结果是否一致
	 * @param actualResponesData 实际结果
	 * @param expectedResponseData 期望结果
	 */
	public static String assertEquals(String actualResponesData, String expectedResponseData) {
		String result="通过";
		try {
			// 将实际结果和期望结果对比，如果相等返回通过
			Assert.assertEquals(actualResponesData, expectedResponseData);
			
		} catch (Throwable e) {
			//如果不相等就会进入到这个代码块中此时返回我们的实际结果
			result=actualResponesData;
		}
		return result;
	}
	
}
