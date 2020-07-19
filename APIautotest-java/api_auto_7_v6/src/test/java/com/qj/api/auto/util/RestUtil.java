package com.qj.api.auto.util;

import java.util.ArrayList;
import java.util.List;

import com.qj.api.auto.pojo.Rest;

/**��������Ҫ��URL������ʽ��Ϣ��ȡ
 * @author Administrator
 *
 */
public class RestUtil {
	//��Ҫ�����ݷ�װ�����ǵ�Rest��������ȥȻ���������ȥɸѡ���൱����Ҫ���ļ���ȡ������list���ϴ洢��Щ����
	static  List<Rest> rests=new ArrayList<Rest>();
	
	static{
		ExcelUtil.load("src/test/resources/cases_v6.xlsx", "�ӿ���Ϣ", Rest.class);
	}
	public static void main(String[] args) {
		
	}
		
	/**ͨ���ӿ�id��ȡ�����ǵ�URL��ַ 
	 * @param apiIdFromcase �������Ľӿڱ��
	 * @return
	 */
	public static String getUrlByApiId(String apiIdFromcase) {
		//�������õ����еĶ�������ǾͿ���ȥ��������ͨ��apiIdFromcase�õ�������Ҫ��URL
		for (Rest rest : rests) {
			if (rest.getApiId().equals(apiIdFromcase)) {
				return  rest.getUrl();
			}
		}
		return "û�иýӿڱ��";
	}
	
	/**���ݽӿ�id��ȡ�����ǵ���������
	 * @param apiIdFromcase �������Ľӿڱ��
	 * @return
	 */
	public static String getTypeByApiId(String apiIdFromcase) {
		for (Rest rest : rests) {
			if (rest.getApiId().equals(apiIdFromcase)) {
				return rest.getType();
			}
		}
		return "û�иýӿڱ��";
	}

}