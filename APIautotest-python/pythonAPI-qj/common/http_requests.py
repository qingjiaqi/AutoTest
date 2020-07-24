# -*- coding: utf-8 -*-
# @Time     :2019/08/28  23:28
# @Author   :qingjia
# @File     http_requests.py
import requests
from common.logger import logger
"""
1.根据传入的请求，判断调用不同的请求方式
2.输出想要看到的log信息
"""
class HTTP_Rquest(object):
   """直接发送请求不记录cookies信息"""
   def request(self,method, url,
                params=None,  data=None,
                headers=None, cookies=None,json=None):
       #判断请求方法
       method=method.lower()
       if method=="get":
           logger.info('正在发送【{}】请求，请求地址：【{}】，请求参数：【{}】'.format(method,url, params))
           return requests.get(url=url,params=params,headers=headers,cookies=cookies)
       elif method=="post":
           if json:
             logger.info('正在发送【{}】请求，请求地址：【{}】，json格式请求参数：【{}】'.format(method, url, json))
             return  requests.post(url=url,json=json,headers=headers,cookies=cookies)
           else:
             logger.info('正在发送【{}】请求，请求地址：【{}】，请求参数：【{}】'.format(method, url, data))
             return  requests.post(url=url,data=data,headers=headers,cookies=cookies)


class HTTP_RquestSession(object):
    """这个请求会自动保存cookie信息"""
    def __init__(self):
        #创建一个session对象
        self.session=requests.sessions.Session()

    def request(self, method, url,
                params=None, data=None,
                headers=None, cookies=None, json=None):
        # 判断请求方法
        method = method.lower()
        if method == "get":
            logger.info('正在发送【{}】请求，请求地址：【{}】，请求参数：【{}】'.format(method, url, params))
            return self.session.get(url=url, params=params, headers=headers, cookies=cookies)
        elif method == "post":
            if json:
                logger.info('正在发送【{}】请求，请求地址：【{}】，json格式请求参数：【{}】'.format(method, url, json))
                return self.session.post(url=url, json=json, headers=headers, cookies=cookies)
            else:
                logger.info('正在发送【{}】请求，请求地址：【{}】，请求参数：【{}】'.format(method, url, data))
                return self.session.post(url=url, data=data, headers=headers, cookies=cookies)
    def close(self):
        """关闭session对象"""
        self.session.close()

if __name__ == '__main__':
    regiter_url="http://test.lemonban.com/futureloan/mvc/api/member/register"
    data={"mobilephone":18825287005,"pwd":"123456","regname":"ll"}
    log_url="http://test.lemonban.com/futureloan/mvc/api/member/login"
    log_data={"mobilephone":18825287005,"pwd":"123456"}
    charge_url="http://test.lemonban.com/futureloan/mvc/api/member/recharge"
    charge_data={"mobilephone":"18825287005","amount":101}

    # http=HTTPRquest()
    http=HTTP_RquestSession()
    res= http.request("post",log_url,data=log_data)
    print(res.json())
    res = http.request("post", charge_url, data=charge_data)
    print(res.json())


