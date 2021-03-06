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

/**JDBC Java DataBase Connectivity,java数据库连接
 * @author Administrator
 *
 */
public class JDBCUtil {
	//将我们的properties声明为静态
		public static Properties properties=new Properties();
	//为了保证我们的数据能加载完成使用静态代码块
		static{
			//创建一个输入流对象
			InputStream inputStream;
			try {
				inputStream = new FileInputStream(new File("src/test/resources/jdbc.properties"));
				//通过load方法将输入流加载到properties对象中去
				properties.load(inputStream);
			} catch (Exception e) {
				System.out.println("解析jdbc.propert文件异常");
			}
			
			
		}
	
	/**根据sql语句查询表数据，并以map返回，key为字段名，value为字段
	 * @param sql 要执行的查询语句
	 * @param values 条件的字段值
	 * @return 结果通过map形式返回 Map<String, Object>
	 */
	public static Map<String, Object> query(String sql,Object ... values){//三个点代表可变参数，可传可不传，多个可以用数组传入
		 		Map<String, Object> columLaberAndValues=null;
				try {
					//调用自己写的getConnection获取到连接
					Connection connection = getConnection();
					
					//2.获取到我们的prepareStatement(准备查询对象)(此类型的对象有提供数据库操作方法)
					 PreparedStatement preparedStatement=connection.prepareStatement(sql);
					 //3.设置条件字段的值:循环传进来的数组
					 	for (int i = 0; i < values.length; i++) {
					 		//第一 个参数是我们的条件我们是按顺序赋值的
							preparedStatement.setObject(i+1, values[i]);//加1是因为这里的取值是从1开始的
						}

					//4.调用查询方法。执行查询，返回resultSet(结果集)
					 ResultSet resultSet=preparedStatement.executeQuery();
					 //获取到我们结果集的元数据(字段数据)
					 ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
					 //获取到查询结果显示的字段个数
					 int columCount=resultSetMetaData.getColumnCount();
					 //5.从结果集取数据，创建一个map来存储数据,因为作用域的问题需要在try外面声明
					columLaberAndValues=new HashMap<String, Object>();
					//从我们查询到的结果中一个一个取数据当我们有的时候就会进入到while代码块中
					 while (resultSet.next()) {
						 //循环查出每个字段。总共有多少个字段就循环几次
						 for (int i = 0; i < columCount; i++) {
							 //从查询结果中去取出所有的字段名，注意调用的getColumnLabel是从1开始的
							 String columnLabel=resultSetMetaData.getColumnLabel(i+1);
							//传入字段名拿出我们的数据转换成string类型
							String columnValue=resultSet.getObject(columnLabel).toString();
							//将我们的字段和值放入到map中去
							columLaberAndValues.put(columnLabel, columnValue);
						 }
						
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return columLaberAndValues;
	}

	/**解析jdbc.properties文件的信息自己写一个DriverManager.getConnection方法登录数据库
	 * @return 返回一个连接对象 connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		//通过key从properties中取出url
		String url=properties.getProperty("jdbc.url");
		//通过key从properties中取出username
		String user=properties.getProperty("jdbc.username");
		//通过key从properties中取出password
		String password=properties.getProperty("jdbc.username");
		//1.连接到数据库，获取到数据库连接对象
		Connection connection = DriverManager.getConnection(url, user, password);
		//返回我们的连接对象
		return connection;
	}
}
