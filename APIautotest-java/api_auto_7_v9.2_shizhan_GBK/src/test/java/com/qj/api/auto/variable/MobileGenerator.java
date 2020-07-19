package com.qj.api.auto.variable;

import java.util.Map;

import org.openxmlformats.schemas.spreadsheetml.x2006.main.STIconSetType;

import com.microsoft.schemas.office.office.STInsetMode;
import com.qj.api.auto.util.JDBCUtil;

/**
 * @author Administrator
 *������������ɲ�ͬ���ֻ�����
 */
public class MobileGenerator {
	/**��������û�б�ע����ֻ����룬ͨ��ע����뵽���ݿ�
	 * @return ����һ��û��ע������ֻ�����
	 */
	public String generateToBeRegisterMobile(){
		//ͨ��sql����������ֻ�����Ȼ���1��֤�ֻ����������µ�,������Ҫƴ��һ���ַ���Ȼ�ᱻϵͳʶ��Ϊһ���ܴ�����ͨ��concat(,ƴ�ӵ�ֵ)
		String sql="select concat (max(mobilephone)+1,'') as toBeRegisterMobile from member";
		//ͨ�����ݿ⹤����ִ�нű���ȡ���ֶ���ֵ��map
		Map<String, Object> columnLableAndValues=JDBCUtil.query(sql);
		//��map��ȡ�����ǵ��ֶ�ֵ���ظ�������
		return columnLableAndValues.get("toBeRegisterMobile").toString();
	}
	
	/**����һ��ϵͳ�����ڵ��ֻ����뵫��������벻��ȥ���뵽���ݿ�
	 * @return ����һ��û��ע������ֻ�����
	 */
	public String genreateSystemExistMobile() {
		//ͨ��sql����������ֻ�����Ȼ���2��֤�ֻ����������µģ�������Ҫƴ��һ���ַ���Ȼ�ᱻϵͳʶ��Ϊһ���ܴ�����ͨ��concat(,ƴ�ӵ�ֵ)
				String sql="select concat (max(mobilephone)+2,'') as SystemExistMobile from member";
				//ͨ�����ݿ⹤����ִ�нű���ȡ���ֶ���ֵ��map
				Map<String, Object> columnLableAndValues=JDBCUtil.query(sql);
				//��map��ȡ�����ǵ��ֶ�ֵ���ظ�������
				return columnLableAndValues.get("SystemExistMobile").toString();
	}
	
	
}
