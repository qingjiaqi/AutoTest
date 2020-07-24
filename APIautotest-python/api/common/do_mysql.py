"""
============================
author:MuSen
time:2019/6/14
E-mail:3247119728@qq.com
============================
"""

"""
操作mysql数据库的

封装的目的：
方便读取数据
方便管理测试环境

"""

import pymysql
from common.config import conf


class ReadMysqlData(object):

    def __init__(self):
        # 连接到数据库
        self.con = pymysql.connect(
            host=conf.get('sql', 'host'),
            port=conf.getint('sql', 'port'),
            user=conf.get('sql', 'user'),
            password=conf.get('sql', 'password'),
            database=conf.get('sql', 'database'),
            charset='utf8',
        )
        # 创建游标
        self.cur = self.con.cursor()

    def find_one(self, sql):
        """
        返回找到的第一条数据
        :param sql:
        :return:
        """
        self.cur.execute(sql)
        self.con.commit()
        return self.cur.fetchone()

    def find_all(self, sql):
        """
        返回找到的所有数据
        :param sql:
        :return:
        """
        self.cur.execute(sql)
        return self.cur.fetchall()


    def find_count(self,sql):
        """
        # 查找存在的数据条数
        :return:
        """
        count = self.cur.execute(sql)
        return count


    def close(self):
        """断开连接"""
        self.cur.close()
        self.con.close()




if __name__ == '__main__':
    db = ReadMysqlData()
    sql = "select * from member where mobilephone = '15810447878'"
    rs = db.find_count(sql)


    print(rs,type(rs))