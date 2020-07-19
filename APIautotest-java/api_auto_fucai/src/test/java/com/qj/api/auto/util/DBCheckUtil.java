package com.qj.api.auto.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.qj.api.auto.pojo.DBChecker;
import com.qj.api.auto.pojo.DBQueryResult;

public class DBCheckUtil {

	/**���ݽű�ִ�в�ѯ�����ز�ѯ���
	 * @param validateSql Ҫִ�еĽű����
	 * @return ����һ���ַ������͵Ľ��{name=value}
	 */
	public static String doQuery(String validateSql) {
		//ͨ��fastjson��ܽ�json��������ɵ�����json���������ݿ�ű���װ�������У����ü��ϴ�����
		List<DBChecker> dbCheckers=JSONObject.parseArray(validateSql,DBChecker.class);
		//����һ�������������ѯ��Ľ��
		List<DBQueryResult> dbQueryResults=new ArrayList<DBQueryResult>();
		//Ҫִ�еĽű������ɼ��ϳ��Ⱦ��������Ա�������
		for (DBChecker dbChecker : dbCheckers) {
			//�õ��ű��ı��
			String no=dbChecker.getNo();
			//�õ��ű�
			String sql=dbChecker.getSql();
			//����ִ�в�ѯ����
			Map<String, Object> columnLabelValues=JDBCUtil.query(sql);
			//�����ݷ�װ��dbchecker��ȥ
			DBQueryResult dbQueryResult = new DBQueryResult();
			//��������õ�������
			dbQueryResult.setNo(no);
			//��ֵ���õ�������
			dbQueryResult.setColumenLabelAndValues(columnLabelValues);
			//��װ���˶��������Ҫ�ǵ��ü��ϴ�����
			dbQueryResults.add(dbQueryResult);
		}
		//�����ǵ�list���Ϸ����л���һ���ַ���
		String dbqueryResult=JSONObject.toJSONString(dbQueryResults);
		return dbqueryResult;
	}


}
