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
		
			//����һ����ά���飬��һ�����ִ��������ж��ٸ�һά���飬�ڶ��������ж��ٸ�
			String[][] shuZu=new String[2][3];
			shuZu[0][0]="a";
			shuZu[0][1]="b";
			shuZu[0][2]="c";
			//��ȡ��ÿһ������
			for (int i = 0; i < shuZu.length; i++) {
				//ѭ����һά���������Ԫ��
				for (int j = 0; j < shuZu[i].length; j++) {
					System.out.println(shuZu[i][j]);
				}
			}
		
	}
	
		
		public static String BJ(){
		int[] arr={1,2,3,10,5,7};
		//ȡ������������ֵ
		//����������
		int max=arr[0];
		//Ȼ���������
		for (int i = 1; i < arr.length; i++) {
			if (max>arr[i]) {//�����һ����ȡ�����Ĵ���ô�Ͳ��䣬�����ȡ������С�Ͱ�ȡ����������ֵ��max��Ȼ������Ƚ�
				max=max;
			} else {
				max=arr[i];
			}
			
		}
//		System.out.println(max);
		//ȡ�������е���Сֵ
		int min=arr[0];
		//Ȼ���������
		for (int i = 1; i < arr.length; i++) {
			if (min<arr[i]) {//�����һ����ȡ�����Ĵ���ô�Ͳ��䣬�����ȡ������С�Ͱ�ȡ����������ֵ��min��Ȼ������Ƚ�
				min=min;
			} else {
				min=arr[i];
			}
			
		}
//		System.out.println(min);
		return "���ֵ"+max+"��Сֵ"+min;
		

		
		
}}

