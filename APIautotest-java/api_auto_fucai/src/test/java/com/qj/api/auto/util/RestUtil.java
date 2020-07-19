package com.qj.api.auto.util;

import java.util.ArrayList;
import java.util.List;

import com.qj.api.auto.pojo.Rest;

/**请求所需要的URL和请求方式信息获取
 * @author Administrator
 *
 */
public class RestUtil {
	//先要将数据封装到我们的Rest对象里面去然后下面才能去筛选，相当于先要把文件获取到，用list集合存储这些对象
	static  List<Rest> rests=new ArrayList<Rest>();
	
	static{
		List<Rest> list=ExcelUtil.load(propertiesUtil.getExcelPath(), "接口信息", Rest.class);
		//将list添加到rests集合中
		rests.addAll(list);
	}
	public static void main(String[] args) {
		
	}
		
	/**通过接口id获取到我们的URL地址 
	 * @param apiIdFromcase 传进来的接口编号
	 * @return
	 */
	public static String getUrlByApiId(String apiIdFromcase) {
		//当我们拿到所有的对象后我们就可以去遍历对象，通过apiIdFromcase拿到我们需要的URL
		for (Rest rest : rests) {
			if (rest.getApiId().equals(apiIdFromcase)) {
				return  rest.getUrl();
			}
		}
		return "没有该接口编号";
	}
	
	/**根据接口id获取到我们的请求类型
	 * @param apiIdFromcase 传进来的接口编号
	 * @return
	 */
	public static String getTypeByApiId(String apiIdFromcase) {
		for (Rest rest : rests) {
			if (rest.getApiId().equals(apiIdFromcase)) {
				return rest.getType();
			}
		}
		return "没有该接口编号";
	}

}
