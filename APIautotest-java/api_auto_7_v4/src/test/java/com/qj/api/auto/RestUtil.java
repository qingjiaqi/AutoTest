package com.qj.api.auto;

import java.util.ArrayList;
import java.util.List;

/**��������Ҫ��URL������ʽ��Ϣ��ȡ�������ǽӿ���Ϣsheetҳ�����ݱ�����restʵ������ȥ�����ṩ��Ӧ���ݵĻ�ȡ����
 * @author Administrator
 *
 */
public class RestUtil {
	//��Ҫ�����ݷ�װ�����ǵ�Rest��������ȥ��Ȼ���������ȥɸѡ���൱����Ҫ���ļ���ȡ������list���ϴ洢��Щ����
	static  List<Rest> rests=new ArrayList<Rest>();
	
	static{
		//����load�������ӿ���Ϣsheetҳ���ݷ�װ��rest����
		ExcelUtil_v4.load("src/test/resources/cases_v4.xlsx", "�ӿ���Ϣ", Rest.class);
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
