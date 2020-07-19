package com.qj.api.auto.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.qj.api.auto.pojo.DBChecker;
import com.qj.api.auto.pojo.DBQueryResult;

public class DBCheckUtil {

	/**根据脚本执行查询并返回查询结果
	 * @param validateSql 要执行的脚本语句
	 * @return 返回一个字符串类型的结果{name=value}
	 */
	public static String doQuery(String validateSql) {
		//通过fastjson框架将json数组解析成单个数json，并将数据库脚本封装到对象中，并用集合存起来
		List<DBChecker> dbCheckers=JSONObject.parseArray(validateSql,DBChecker.class);
		//声明一个集合来保存查询后的结果
		List<DBQueryResult> dbQueryResults=new ArrayList<DBQueryResult>();
		//要执行的脚本次数由集合长度决定，所以遍历集合
		for (DBChecker dbChecker : dbCheckers) {
			//拿到脚本的标号
			String no=dbChecker.getNo();
			//拿到脚本
			String sql=dbChecker.getSql();
			//调用执行查询方法
			Map<String, Object> columnLabelValues=JDBCUtil.query(sql);
			//将数据封装到dbchecker中去
			DBQueryResult dbQueryResult = new DBQueryResult();
			//将编号设置到对象中
			dbQueryResult.setNo(no);
			//将值设置到对象中
			dbQueryResult.setColumenLabelAndValues(columnLabelValues);
			//封装完了对象后我们要记得用集合存起来
			dbQueryResults.add(dbQueryResult);
		}
		//将我们的list集合反序列化成一个字符串
		String dbqueryResult=JSONObject.toJSONString(dbQueryResults);
		return dbqueryResult;
	}


}
