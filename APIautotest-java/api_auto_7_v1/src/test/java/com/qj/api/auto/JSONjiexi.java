package com.qj.api.auto;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class JSONjiexi {
	public static void main(String[] args) {
		String arr="{\"columenLabelAndValues\":{\"leaveamount\":\"0.00\"},\"no\":\"1\"},{\"columenLabelAndValues\":"
				+ "{\"totalNum\":\"0\"},\"no\":\"2\"}";
		//ͨ�����ǵ�net.sf.json.JSONObject�е�fromObject(Ҫת���Ķ���)����������װ����json��������
		JSONObject jsonstring=JSONObject.fromObject(arr);
		//��jsonstring����getString(��)��ȡ������Ӧ��ֵ
		String value=jsonstring.getString("columenLabelAndValues");
		//��ȡ����ֵ���������{"leaveamount":"0.00"}
		System.out.println(value);
		//���������ȡ��{"leaveamount":"0.00"}��0.00��ô������Ҫ���½�����װ����json����
		JSONObject jsonstring2=JSONObject.fromObject(value);
		//��jsonstring����getString(��)��ȡ������Ӧ��ֵ
		String value2=jsonstring2.getString("leaveamount");
		System.out.println(value2);
		
	}
}
