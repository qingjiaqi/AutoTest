"""
============================
author:MuSen
time:2019/6/12
E-mail:3247119728@qq.com
============================
"""
import requests

from requests.sessions import Session

login_url = "http://test.lemonban.com/futureloan/mvc/api/member/login"

data = {"mobilephone": "15567678989", "pwd": "123qwe"}
# requests

response = requests.post(url=login_url, data=data)

cookies = response.cookies
print(dict(cookies))

headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.80 Safari/537.36",
    # "Token":"",
    "cookie": "JSESSIONID=B9D1BF483495DB94CCC0B740D314B2C9",
    "Content-Type": "application/x-www-form-urlencoded",
}

# 充值的地址
rech_url = "http://test.lemonban.com/futureloan/mvc/api/member/recharge"
# 构建充值的参数
data = {"mobilephone": "15567678989", "amount": 100}
# cookies参数：可以用来传递cookie信息
response = requests.post(url=rech_url, data=data, headers=headers)

print(response.text)
