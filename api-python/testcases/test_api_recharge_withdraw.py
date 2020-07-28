"""
============================
author:MuSen
time:2019/6/17
E-mail:3247119728@qq.com
============================
"""
"""
充值和取现接口的用例类
"""

import os
import unittest
from librarys.ddt import data, ddt
from common.read_excel import ReadExcel
from common.logger import logger
from common.config import conf
from common.constant import DATA_DIR
from common.http_requests import HTTPRequest2
from common.do_mysql import ReadMysqlData

# 配置文件中读取excel相关数据
file_name = conf.get('excel', 'file_name')


@ddt
class RechargeTeseCase(unittest.TestCase):
    """充值和取现用例类"""
    # 读取用例数据
    wb = ReadExcel(os.path.join(DATA_DIR, file_name), 'recharge2')
    cases = wb.read_data_obj()

    @classmethod
    def setUpClass(cls):
        logger.info('开始自己注册接口的用例，正在准备')
        # 创建一个操作数据库的对象和发请求的对象
        cls.db = ReadMysqlData()
        cls.request = HTTPRequest2()


    @data(*cases)
    def test_register(self, case):
        """
        校验返回状态和  校验数据库变化值
        :param case:
        :return:

        """
        if "#" in case.data:
            case.data  = case.data.replace('#login_phone#',conf.get('test_data','user'))
            case.data  = case.data.replace('#pwd#', conf.get('test_data', 'pwd'))

        # 判断一个该条用例是否需要进行校验数据
        if case.check_sql:
            # 获取数据库中充值之前的金额
            start_money = self.db.find_one(case.check_sql)[0]
        logger.info("\n开始执行测试用例{}".format(case.title))
        # 发起请求，获取结果
        url = conf.get('env','url')+case.url
        response = self.request.request(method=case.method,url=url,data=eval(case.data))
        print(response.text)
        try:
            logger.info('请求参数：{}'.format(case.data))
            # 预期的结果  case.excepted     实际结果 response.text
            logger.info('\n预期结果：{}，\n实际结果：{}'.format(case.excepted, response.json()["code"]))
            # 校验状态码
            self.assertEqual(str(case.excepted),response.json()['code'])
            # 再次判断是否需要数据库校验
            if case.check_sql:
                # 获取充值之后的金额
                end_money = self.db.find_one(case.check_sql)[0]
                # 比对数据库中的数据变化
                money = eval(case.data)['amount']
                if case.interface == '充值':
                    print('充值前金额{}，充值后金额{}，充值的金额{}'.format(start_money,end_money,money))
                    self.assertEqual(money,end_money-start_money)
                else:
                    print('充值前金额{}，充值后金额{}，充值的金额{}'.format(start_money, end_money, money))
                    self.assertEqual(start_money-end_money,money)
        # 校验数据库
        except AssertionError as e:
            logger.error(e)
            # 结果写入文件
            self.wb.write_data(row=case.case_id+1,column=10,msg='failed')
            self.wb.write_data(row=case.case_id + 1, column=9, msg=response.text)
            raise e
        else:
            logger.info('---测试用例：-{}-已通过---'.format(case.title))
            # 讲结果写入文件
            self.wb.write_data(row=case.case_id+1,column=10,msg='pass')
            self.wb.write_data(row=case.case_id + 1, column=9, msg=response.text)

    @classmethod
    def tearDownClass(cls):
        logger.info('注册接口的用例执行完毕')
        cls.db.close()
        cls.request.close()
