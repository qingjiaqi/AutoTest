"""
============================
author:MuSen
time:2019/6/12
E-mail:3247119728@qq.com
============================
"""
import requests
from common.logger import logger


class HTTPRequests(object):
    """不需要记录sesseion，发送请求的类"""

    def request(self, url, mehtod, data=None, headers=None, cookies=None):

        # 把请求方法转换成大写
        method = mehtod.upper()
        logger.info('正在发起请求，请求地址{}，请求数据'.format(url, data))
        # 判断请求方法
        if method == "GET":
            res = requests.get(url=url, params=data, headers=headers, cookies=cookies)
        # post请求
        elif method == 'POST':
            res = requests.post(url=url, data=data, headers=headers, cookies=cookies)
        else:
            res = None
        return res


class HTTPRequests2(object):
    """
    使用session来记录cookies数据
    """

    def __init__(self):
        # 创建一个session对象
        self.session = requests.sessions.Session()

    def request(self, url, mehtod, data=None, json=None, headers=None, cookies=None):

        # 把请求方法转换成大写

        method = mehtod.upper()
        logger.info('正在发起请求，请求地址{}，请求数据'.format(url, data))
        # 判断请求方法
        if method == "GET":
            res = self.session.get(url=url, params=data, headers=headers, cookies=cookies)
        # post请求
        elif method == 'POST':
            res = self.session.post(url=url, data=data, headers=headers, cookies=cookies)
        else:
            res = None
        return res

    def close(self):
        self.session.close()
