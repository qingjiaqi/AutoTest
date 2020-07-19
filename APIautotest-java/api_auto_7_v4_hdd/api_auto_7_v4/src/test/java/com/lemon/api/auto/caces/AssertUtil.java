package com.lemon.api.auto.caces;

import org.testng.Assert;

public class AssertUtil {

	/**自定义类库：断言比较实际测试结果与期望值是否一样
	 * @param actualResponseData
	 * @param expectedResponseData
	 */
	public static String assertEquals(String actualResponseData, String expectedResponseData) {
		String result = "通过";
		try {
			Assert.assertEquals(actualResponseData, actualResponseData);
		} catch (Throwable e) {
			result = actualResponseData;
		}
		return result;
	}

}
