package com.qj.api.auto.variable;

import java.util.Map;

import org.openxmlformats.schemas.spreadsheetml.x2006.main.STIconSetType;

import com.microsoft.schemas.office.office.STInsetMode;
import com.qj.api.auto.util.JDBCUtil;

/**
 * @author Administrator
 *这个类用来生成不同的手机号码
 */
public class MobileGenerator {
	/**用来生成没有被注册的手机号码，通过注册插入到数据库
	 * @return 返回一个没有注册过的手机号码
	 */
	public String generateToBeRegisterMobile(){
		//通过sql语句查出最大的手机号码然后加1保证手机号码是最新的,这里需要拼接一个字符不然会被系统识别为一个很大数据通过concat(,拼接的值)
		String sql="select concat (max(mobilephone)+1,'') as toBeRegisterMobile from member";
		//通过数据库工具类执行脚本获取到字段与值的map
		Map<String, Object> columnLableAndValues=JDBCUtil.query(sql);
		//从map中取出我们的字段值返回给调用者
		return columnLableAndValues.get("toBeRegisterMobile").toString();
	}
	
	/**创建一个系统不存在的手机号码但是这个号码不会去插入到数据库
	 * @return 返回一个没有注册过的手机号码
	 */
	public String genreateSystemExistMobile() {
		//通过sql语句查出最大的手机号码然后加2保证手机号码是最新的，这里需要拼接一个字符不然会被系统识别为一个很大数据通过concat(,拼接的值)
				String sql="select concat (max(mobilephone)+2,'') as SystemExistMobile from member";
				//通过数据库工具类执行脚本获取到字段与值的map
				Map<String, Object> columnLableAndValues=JDBCUtil.query(sql);
				//从map中取出我们的字段值返回给调用者
				return columnLableAndValues.get("SystemExistMobile").toString();
	}
	
	
}
