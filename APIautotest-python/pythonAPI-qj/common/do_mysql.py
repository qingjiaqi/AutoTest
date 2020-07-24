# -*- coding: utf-8 -*-
# @Time     :2019/09/03  21:01
# @Author   :qingjia
# @File     do_mysql.py
import pymysql
from common.config import conf
from common.logger import logger

class ReadMySqlData(object):
    def __init__(self):
        # 获取连接对象
        self.connect=pymysql.connect(
            host=conf.get('sql','host'),
            port=conf.getint('sql','port'),#端口号要是int类型的
            user=conf.get('sql','user'),
            password=conf.get('sql', 'password'),
            charset='utf8',
            database=conf.get('sql', 'database'),
        )

        #创建游标
        self.cur=self.connect.cursor()
    def find_one(self,sql):
        """查询返回的第一条数据
        :param sql 执行的sql
        :return 返回一条数据 元组类型
        """
        logger.info("执行的sql语句为【{}】".format(sql))
        self.connect.commit()#刷新下数据库数据
        self.cur.execute(sql)#执行sql
        data_one=self.cur.fetchone()#获取到第一条数据
        return  data_one
    def find_all(self,sql):
        """查询返回查询到的所有数据
        :param sql 执行的sql
        :return 返回所有数据 元组嵌套元组
        """
        logger.info("执行的sql语句为【{}】".format(sql))
        self.connect.commit()#刷新数据库数据
        self.cur.execute(sql)
        return  self.cur.fetchall()#查询所有数
    def find_count(self,sql):
        """查询返回查询到的条数
        :param sql 执行的sql
        :return 返回查询到的条数
        """
        logger.info("执行的sql语句为【{}】".format(sql))
        self.connect.commit()#刷新数据库数据
        return self.cur.execute(sql) #返回查询到的条数
    def close(self):
        """断开连接"""
        self.connect.close()
        self.cur.close()

if __name__ == '__main__':
    data=ReadMySqlData().find_count("select *  from member limit 10")
    print(data)



