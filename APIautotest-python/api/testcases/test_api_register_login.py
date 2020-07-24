"""
============================
author:MuSen
time:2019/6/14
E-mail:3247119728@qq.com
============================
"""
import os
import random
import unittest
from librarys.ddt import data, ddt
from common.read_excel import ReadExcel
from common.logger import logger
from common.config import conf
from common.constant import DATA_DIR
from common.http_requests import HTTPRequest
from common.do_mysql import ReadMysqlData

# 配置文件中读取excel相关数据
file_name = conf.get('excel', 'file_name')

def rand_phone():
    phone = '134'
    for i in range(8):
        i = random.randint(0, 9)
        phone += str(i)
    return phone


@ddt
class RegisterTeseCase(unittest.TestCase):
    """注册接口用例类"""
    # 读取用例数据
    wb = ReadExcel(os.path.join(DATA_DIR, file_name), 'register')
    cases = wb.read_data_obj()
    print("这是读取到的excel数【{0}】".format(cases))

    @classmethod
    def setUpClass(cls):
        logger.info('开始自己注册接口的用例，正在准备')
        cls.request = HTTPRequest()
        cls.db = ReadMysqlData()
    @data(*cases)
    def test_register(self, case):

        # 发送请求之前，替换用例数据
        if "#register_phone#" in case.data:
            # 替换测试的号码
            # 方法一：随机生成一个手机号码
            while True:
                phone = rand_phone()
                # 查询数据库该号码是否存在
                print(phone)
                count = self.db.find_count('SELECT * FROM member WHERE MobilePhone={};'.format(phone))
                if count==0:
                    break
            # 方法二 从数据库中找出最大值的手机号码，+1

            case.data = case.data.replace("#register_phone#",phone)

        if "#phoe#" in case.data:
            phone = self.db.find_one('SELECT MobilePhone FROM `member` LIMIT 1')[0]
            case.data = case.data.replace("#phone#", phone)



        # 发送请求获取结果
        url = conf.get('env', 'url') + case.url
        response = self.request.request(method=case.method, url=url, data=eval(case.data))
        try:
            logger.info('请求参数：{}'.format(case.data))
            # 预期的结果  case.excepted     实际结果 response.text
            logger.info('\n预期结果：{}，\n实际结果：{}'.format(case.excepted, response.text))
            self.assertEqual(case.excepted, response.text)
        except AssertionError as e:
            # 测试未通过，输出日志
            logger.error(e)
            # 在excel用例文件中写入结果
            self.wb.write_data(row=case.case_id + 1, column=8, msg='failed')
            logger.info('---测试用例：-{}-失败---'.format(case.title))
            raise e
        else:
            # 测试通过，输出日志
            logger.info('---测试用例：-{}-已通过---'.format(case.title))
            # 在excel用例文件中写入结果
            self.wb.write_data(row=case.case_id + 1, column=8, msg='pass')

    @classmethod
    def tearDownClass(cls):
        logger.info('注册接口的用例执行完毕')
        cls.db.close()


@ddt
class LoginTestCase(unittest.TestCase):
    """登陆接口用例类"""
    wb = ReadExcel(os.path.join(DATA_DIR, file_name), 'login')
    cases = wb.read_data_obj()
    @classmethod
    def setUpClass(cls):
        logger.info('准备测试工作')
        """该方法在调用加载该测试用例类的时候会自动执行"""
        cls.request = HTTPRequest()

    @data(*cases)
    def test_login(self, case):
        # 替换用例数据
        case.data = case.data.replace('#login_phone#', conf.get('test_data', 'user'))
        case.data = case.data.replace('#pwd#', conf.get('test_data', 'pwd'))

        try:
            # 发送请求
            url = conf.get('env', 'url') + case.url
            res = self.request.request(method=case.method, url=url, data=eval(case.data))
            # 比对结果
            logger.info('\n预期结果:{}\n实际结果:{}'.format(case.excepted, res.text))
            self.assertEqual(case.excepted, res.text)
        except AssertionError as e:
            # 输出日志
            logger.error('\n用例--{}--未通过'.format(case.title))
            logger.error(e)
            # 结果写入文件
            self.wb.write_data(row=case.case_id + 1, column=8, msg='failed')
            raise e
        else:
            logger.info('\n测试用例--{}---已通过'.format(case.title))
            self.wb.write_data(row=case.case_id + 1, column=8, msg='pass')

    @classmethod
    def tearDownClass(cls):
        logger.info('登录接口测试完毕')


