package com.qj.api.auto.cases;

import org.testng.annotations.DataProvider;

import com.qj.api.auto.pojo.Case;
import com.qj.api.auto.util.CaseUtil;

/**��ҳ�ӿڻ�ȡ��
 * @author Administrator
 *
 */
public class InfoCase extends BaseProcessor {
	@DataProvider
	public Object[][] datas() {
		Object[][] datas=CaseUtil.getCaseDateByApiId("4", cellNames);
			return datas;
	}
}
