# -*- coding: utf-8 -*-
# @Time     :2020/03/01  17:55
# @Author   :qingjia
# @File     HttpRequestProcessing 处理http请求
import requests
import sys
import json
import urllib
import re

class HttpRequestProcessing():
    def isExistsZh(self,strObj):
        # 判断字符中是否包含中文
        # compile该函数根据包含的正则表达式的字符串创建模式对象。可以实现更有效率的匹配。
        matchPatttern=re.compile(u'[\u4e00-\u9fa5]+')
        return re.search(matchPatttern,strObj)
    def excuteHttpRequest(self,method,url,params=None,headers=None,request_type='json',cookies=None):
        """
        执行http请求是\n
        :param method: 请求方法\n
        :param url: 请求地址\n
        :param params: 参数\n
        :param headers: 请求头\n
        :param request_type:  请求参数类型，暂时支持form，json\n
        :return:
        """
        if not url:
            raise RuntimeError('url is None')
        if not method:
            raise RuntimeError('method is None')
        strArray=url.split("/")
        if len(strArray)>2:
            domainName=strArray[2] #拿到域名
        else:
            raise RuntimeError('url is invalid')
        if isinstance(params,(list,dict,tuple)):
              # 将数据转成json格式
            params=json.dumps(params,ensure_ascii=False)
        if headers:
            headers=eval(headers)
        method = method.upper()
        body = params
        if body and (request_type.upper()=='FORM' or method=='GET' ):
            # 因为form时必须传字典格式
            body = eval(params)
        if self.isExistsZh(url):
            url=urllib.parse.quote(url,':=&/?') #如果url中含有中文那就将中文转为url格式编码，排除:=&/?
        if method == "POST":
            response=requests.request(method=method,url=url,data=body,headers=headers,cookies=cookies)
        if method== "PATCH" or method=="PUT":
            response=requests.request(method=method,url=url,data=body,headers=headers,cookies=cookies)
        if method=="GET":
            response=requests.request(method=method,url=url,params=body,headers=headers,cookies=cookies)
        if method=="DELETE":
            if body:
                response=requests.request(method=method,url=url,data=body,headers=headers,cookies=cookies)
            else:
                response=requests.request(method=method,url=url,headers=headers,cookies=cookies)
        code=response.status_code
        statusFlag=not str(code).startswith('4') and not str(code).startswith('5')
        result=response.text
        if len(result.strip())>0 and statusFlag:
            try:
                result=json.loads(result)
                return code,result,url,params,response.cookies
            except:
                return code,result,url,params,response.cookies
        else:
            return code,result,url,params,response.cookies


    def excuteHttpRequest_v1(self,method,url,params=None,headers=None,request_type='json'):
        """
        执行http请求是\n
        :param method: 请求方法\n
        :param url: 请求地址\n
        :param params: 参数\n
        :param headers: 请求头\n
        :param request_type:  请求参数类型，暂时支持form，json\n
        :return:
        """
        strArray=url.split("/")
        if len(strArray)>2:
            domainName=strArray[2] #拿到域名
        else:
            print('url is invalid')
        if isinstance(params,(list,dict,tuple)):
              # 将数据转成json格式
            params=json.dumps(params,ensure_ascii=False)
        if headers:
            headers=eval(headers)
        method = method.upper()
        body = params
        if body and (request_type.upper()=='FORM' or method=='GET' ):
            # 因为form时必须传字典格式
            body = eval(params)
        if self.isExistsZh(url):
            url=urllib.parse.quote(url,':=&/?') #如果url中含有中文那就将中文转为url格式编码，排除:=&/?
        if method == "POST":
            response=requests.request(method=method,url=url,data=body,headers=headers)
        if method== "PATCH" or method=="PUT":
            response=requests.request(method=method,url=url,data=body,headers=headers)
        if method=="GET":
            response=requests.request(method=method,url=url,params=body,headers=headers)
        if method=="DELETE":
            if body:
                response=requests.request(method=method,url=url,data=body,headers=headers)
            else:
                response=requests.request(method=method,url=url,headers=headers)
        code=response.status_code
        statusFlag=not str(code).startswith('4') and not str(code).startswith('5')
        result=response.text
        if len(result.strip())>0 and statusFlag:
            try:
                result=json.loads(result)
                return code,result,url,params,response.cookies
            except:
                return code,result,url,params,response.cookies
        else:
            return code,result,url,params,response.cookies



if __name__ == '__main__':
    test=HttpRequestProcessing()
    # url="https://blog.csdn.net/dbdd_cf/article/details/84326152"
    # url = 'http://test.lemonban.com/futureloan/mvc/api//member/register'
    # data = {"mobilephone": "13242407555", "pwd": "123456"}
    url = "http://test.lemonban.com/futureloan/mvc/api/member/login"
    data = {"mobilephone": "18825287005", "pwd": "123456"}
    code, re,url,param = test.excuteHttpRequest('get',url,params=data,request_type='form')
    print re
    print re.get('msg')



