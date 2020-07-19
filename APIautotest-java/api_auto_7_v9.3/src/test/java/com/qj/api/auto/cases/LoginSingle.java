package com.qj.api.auto.cases;


import com.alibaba.fastjson.JSONObject;
import com.qj.api.auto.util.HttpUnit;
import com.qj.api.auto.util.propertiesUtil;
import org.apache.log4j.Logger;

import java.util.Map;

/**正常登录，用来给需要登录的接口调用
 * @author Administrator
 *
 */
public class LoginSingle {
	Logger logger=Logger.getLogger(LoginSingle.class);
		/**单次登录鉴权用 
		 * @param urlKey 请求地址key
		 * @param paramKey  请求参数的key
		 * @param requestType 请求方式key
		 * @param contetType 请求类型key
		 */
		public   void login(String urlKey,String paramKey,String requestType,String contetType ) {
			//获取到登录的url
			String url=propertiesUtil.getValueByKey(urlKey);
			//登录用的参数
			String param=propertiesUtil.getValueByKey(paramKey);
			logger.info("登录使用的参数为【"+param+"】");
			//将json参数通过解析保存到map中
			Map<String, String> paramValues=(Map<String, String>) JSONObject.parse(param);
			//获取到请求方式
			String requestMode=propertiesUtil.getValueByKey(requestType);
			//获取到提交类型
			String type=propertiesUtil.getValueByKey(contetType);
			//调用httpUtil中的执行接口方法
			String result=HttpUnit.doService(requestMode, url, paramValues, type);
			//判断返回的结果中是否包含登录成功

		
			if (result.contains("登录成功")) {
				logger.info("登录接口执行成功");
			}else {
				logger.error("登录失败请检查");
			}
			
		}

}
