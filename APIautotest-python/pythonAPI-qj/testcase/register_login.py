# -*- coding: utf-8 -*-
# @Time     :2019/09/03  22:43
# @Author   :qingjia
# @File     register_login.py
import  unittest
import os
from common.reader_excel import ReadExcel
from common.constant import CASE_DATA
from common.config import conf
from common.logger import logger
from common.http_requests import HTTP_Rquest
from common.do_mysql import ReadMySqlData
from ddt import ddt,data
from common.tool import rand_phone
from common.text_replace import replace,ConText
# 获取测试用例文件名
fileName=conf.get("excel","file_name")
@ddt
class TestRegister(unittest.TestCase):
    """注册接口类"""
    # 读取数据
    readExcel=ReadExcel(os.path.join(CASE_DATA,fileName),"register")
    # 读取出所有数据
    cases=readExcel.readcell_data_obj()
    logger.info("读取【register】中所有数据完成")
    @classmethod
    def setUpClass(cls):#测试类执行前执行该方法
        logger.info("开始执行注册接口测试用例")
        cls.request=HTTP_Rquest()#获取到执行http请求的对象
        cls.db=ReadMySqlData()#获取到mysql连接对象
    @data(*cases)
    def test_register(self,case):
        #替换用例中的数据
        if "#" in case.data:
            # phone=""
            while True:
                phone=rand_phone()
                count=self.db.find_count("SELECT * FROM `member` WHERE MobilePhone={}".format(phone))
                if count==0:
                    setattr(ConText,"register_phone",phone)
                    break
            data=replace(case.data)
            print(data)



        #执行请求

    def tearDownClass(cls):
        logger.info('注册接口的用例执行完毕')
        cls.db.close()
