"""
============================
author:MuSen
time:2019/6/10
E-mail:3247119728@qq.com
============================
"""

"""
requests安装： pip install requests


get 请求：requests.get()

参数：url:请求地址  ，params:请求参数（字典类型）



post请求: requests.post()
参数：url:请求地址  data:请求参数（字典类型）   json:请求参数（json类型）


"""

import requests

register_url = "http://test.lemonban.com/futureloan/mvc/api/member/register"
# baidu_url = "https://www.baidu.com/"

# 构建参数
data = {"mobilephone": "15567678989", "pwd": "123qwe", "regname": "木森"}

# 发起get请求，获取响应
# response = requests.get(url=register_url,params=data)

# 查看响应内容：
# text属性
# print(response.text)
# content属性，字节形式的需要使用decode解码
# print(response.content.decode('utf8'))

# 获取响应码
# status_code属性:获取响应码
# print(response.status_code)

# response.headers:查看响应头
# print(response.headers)

# 查看cookie信息
# print(response.cookies)

# print(response.request)

import json



headers = {
    "Content-Type":"application/x-www-form-urlencoded"
}

# post请求
response = requests.post(url=register_url,data=data)

# response = requests.post(url=register_url,json=data)
# 打印响应结果
print(response.json())
