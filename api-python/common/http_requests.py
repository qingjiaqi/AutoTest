"""
============================
author:MuSen
time:2019/6/12
E-mail:3247119728@qq.com
============================
"""
import requests
from common.logger import logger

"""
封装的目的：
1、是为了根据用例中的请求方法，来决定发起什么类型的请求
2、输出login日志

"""


class HTTPRequest(object):
    """直接发请求不记录cookies信息的 """

    def request(self, method, url,
                params=None, data=None,
                headers=None, cookies=None, json=None):
        # 判断请求的方法

        method = method.lower()
        if method == 'post':
            # 判断是否使用json来传参（适用于项目中接口参数有使用json传参的）
            if json:
                logger.info('正在发送请求，请求地址：{}，请求参数：{}'.format(url, json))
                return requests.post(url=url, json=json, headers=headers, cookies=cookies)
            else:
                logger.info('正在发送请求，请求地址：{}，请求参数：{}'.format(url, data))
                return requests.post(url=url, data=data, headers=headers, cookies=cookies)
        elif method == 'get':
            logger.info('正在发送请求，请求地址：{}，请求参数：{}'.format(url, params))
            return requests.get(url=url, params=params, headers=headers, cookies=cookies)


class HTTPRequest2(object):
    """记录cookies信息,给下一次请求用 """

    def __init__(self):
        # 创建一个session对象
        self.session = requests.sessions.Session()

    def request(self, method, url,
                params=None, data=None,
                headers=None, cookies=None, json=None):
        # 判断请求的方法

        method = method.lower()
        if method == 'post':
            # 判断是否使用json来传参（适用于项目中接口参数有使用json传参的）
            if json:
                logger.info('正在发送请求，请求地址：{}，请求参数：{}'.format(url, json))
                return self.session.post(url=url, json=json, headers=headers, cookies=cookies)
            else:
                logger.info('正在发送请求，请求地址：{}，请求参数：{}'.format(url, data))
                return self.session.post(url=url, data=data, headers=headers, cookies=cookies)
        elif method == 'get':
            logger.info('正在发送请求，请求地址：{}，请求参数：{}'.format(url, params))
            return self.session.get(url=url, params=params, headers=headers, cookies=cookies)

    def close(self):
        self.session.close()

