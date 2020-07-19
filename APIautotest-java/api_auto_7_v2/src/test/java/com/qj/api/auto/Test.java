package com.qj.api.auto;

import java.util.ArrayList;

import org.apache.http.util.EntityUtils;

public class Test {
	public static void main(String[] args) {
		System.out.println(BJ());
		Object [][] datas=ExcelUtil.datas();
		for (Object[] objects : datas) {
			for (Object object : objects) {
				System.out.print("["+object+"]");
			}
			System.out.println();
		}
		
			//定义一个二维数组，第一个数字代表里面有多少个一维数组，第二个代表有多少个
			String[][] shuZu=new String[2][3];
			shuZu[0][0]="a";
			shuZu[0][1]="b";
			shuZu[0][2]="c";
			//先取出每一组数据
			for (int i = 0; i < shuZu.length; i++) {
				//循环出一维数组里面的元素
				for (int j = 0; j < shuZu[i].length; j++) {
					System.out.println(shuZu[i][j]);
				}
			}
		
	}
	
		
		public static String BJ(){
		int[] arr={1,2,3,10,5,7};
		//取出数组中最大的值
		//假设最大的是
		int max=arr[0];
		//然后遍历数组
		for (int i = 1; i < arr.length; i++) {
			if (max>arr[i]) {//如果第一个比取出来的大那么就不变，如果比取出来的小就把取出来的数赋值给max，然后继续比较
				max=max;
			} else {
				max=arr[i];
			}
			
		}
//		System.out.println(max);
		//取出数组中的最小值
		int min=arr[0];
		//然后遍历数组
		for (int i = 1; i < arr.length; i++) {
			if (min<arr[i]) {//如果第一个比取出来的大那么就不变，如果比取出来的小就把取出来的数赋值给min，然后继续比较
				min=min;
			} else {
				min=arr[i];
			}
			
		}
//		System.out.println(min);
		return "最大值"+max+"最小值"+min;
		

		
		
}}

