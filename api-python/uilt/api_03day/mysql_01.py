"""
============================
author:MuSen
time:2019/6/14
E-mail:3247119728@qq.com
============================
"""
"""
python操作mysql数据库

pip install pymysql

项目的数据库地址和密码
host:test.lemonban.com
port:3306
username:test
password:test
第一步：建立连接 连接到数据库 ：connect
"""

import pymysql

# 第一步：连接到数据库
con = pymysql.connect(
    host="test.lemonban.com",  # 数据库的地址
    user='test',  # 登录数据库的账号
    password="test",  # 登录数据库的密码
    port=3306,  # 端口
    database='future',  # 数据库名称
    charset='utf8')

con = pymysql.connect(
    host='localhost',
    port=3306,
    user='root',
    password='mysql',
    charset='utf8',
    database='test'

)

# 第二步：创建一个游标
cur = con.cursor()

# 第三步：执行对应的sql语句  execute(sql)
sql = 'SELECT name,gender,age FROM students;'

# 查询到的数据条数
res = cur.execute(sql)
print(res)

# 第四步：获取数据

# 获取一条  返回元祖形式的
data_one = cur.fetchone()
print(data_one)

# 获取所有的数据
datas = cur.fetchall()
print(datas)

# 添加数据  删除数据  修改数据的操作怎么做

# pymysql操作数据库 默认开启了事务，

# 提交事务
con.commit()

# 封装一个操作mysql 数据库的类


