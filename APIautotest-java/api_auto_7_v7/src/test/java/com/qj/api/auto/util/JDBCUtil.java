package com.qj.api.auto.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**JDBC Java DataBase Connectivity,java���ݿ�����
 * @author Administrator
 *
 */
public class JDBCUtil {
	//�����ǵ�properties����Ϊ��̬
		public static Properties properties=new Properties();
	//Ϊ�˱�֤���ǵ������ܼ������ʹ�þ�̬�����
		static{
			//����һ������������
			InputStream inputStream;
			try {
				inputStream = new FileInputStream(new File("src/test/resources/jdbc.properties"));
				//ͨ��load���������������ص�properties������ȥ
				properties.load(inputStream);
			} catch (Exception e) {
				System.out.println("����jdbc.propert�ļ��쳣");
			}
			
			
		}
	
	/**����sql����ѯ�����ݣ�����map���أ�keyΪ�ֶ�����valueΪ�ֶ�
	 * @param sql Ҫִ�еĲ�ѯ���
	 * @param values �������ֶ�ֵ
	 * @return ���ͨ��map��ʽ���� Map<String, Object>
	 */
	public static Map<String, Object> query(String sql,Object ... values){//���������ɱ�������ɴ��ɲ�����������������鴫��
		 		Map<String, Object> columLaberAndValues=null;
				try {
					//�����Լ�д��getConnection��ȡ������
					Connection connection = getConnection();
					
					//2.��ȡ�����ǵ�prepareStatement(׼����ѯ����)(�����͵Ķ������ṩ���ݿ��������)
					 PreparedStatement preparedStatement=connection.prepareStatement(sql);
					 //3.���������ֶε�ֵ:ѭ��������������
					 	for (int i = 0; i < values.length; i++) {
					 		//��һ �����������ǵ����������ǰ�˳��ֵ��
							preparedStatement.setObject(i+1, values[i]);//��1����Ϊ�����ȡֵ�Ǵ�1��ʼ��
						}

					//4.���ò�ѯ������ִ�в�ѯ������resultSet(�����)
					 ResultSet resultSet=preparedStatement.executeQuery();
					 //��ȡ�����ǽ������Ԫ����(�ֶ�����)
					 ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
					 //��ȡ����ѯ�����ʾ���ֶθ���
					 int columCount=resultSetMetaData.getColumnCount();
					 //5.�ӽ����ȡ���ݣ�����һ��map���洢����,��Ϊ�������������Ҫ��try��������
					columLaberAndValues=new HashMap<String, Object>();
					//�����ǲ�ѯ���Ľ����һ��һ��ȡ���ݵ������е�ʱ��ͻ���뵽while�������
					 while (resultSet.next()) {
						 //ѭ�����ÿ���ֶΡ��ܹ��ж��ٸ��ֶξ�ѭ������
						 for (int i = 0; i < columCount; i++) {
							 //�Ӳ�ѯ�����ȥȡ�����е��ֶ�����ע����õ�getColumnLabel�Ǵ�1��ʼ��
							 String columnLabel=resultSetMetaData.getColumnLabel(i+1);
							//�����ֶ����ó����ǵ�����ת����string����
							String columnValue=resultSet.getObject(columnLabel).toString();
							//�����ǵ��ֶκ�ֵ���뵽map��ȥ
							columLaberAndValues.put(columnLabel, columnValue);
						 }
						
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return columLaberAndValues;
	}

	/**����jdbc.properties�ļ�����Ϣ�Լ�дһ��DriverManager.getConnection������¼���ݿ�
	 * @return ����һ�����Ӷ��� connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		//ͨ��key��properties��ȡ��url
		String url=properties.getProperty("jdbc.url");
		//ͨ��key��properties��ȡ��username
		String user=properties.getProperty("jdbc.username");
		//ͨ��key��properties��ȡ��password
		String password=properties.getProperty("jdbc.username");
		//1.���ӵ����ݿ⣬��ȡ�����ݿ����Ӷ���
		Connection connection = DriverManager.getConnection(url, user, password);
		//�������ǵ����Ӷ���
		return connection;
	}
}
