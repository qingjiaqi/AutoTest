"""
============================
author:MuSen
time:2019/6/10
E-mail:3247119728@qq.com
============================
"""
import requests
# 注册的地址
# register_url = "http://test.lemonban.com/futureloan/mvc/api/member/register"

# 构建参数
# data = {"mobilephone": "15567678989", "pwd": "123qwe", "regname": "木森"}

# 登录
login_data = {"mobilephone": "15567678989", "pwd": "123qwe"}
login_url="http://test.lemonban.com/futureloan/mvc/api/member/login"



res1 = requests.post(url=login_url,data=login_data)
print(res1.text)
cookies= res1.cookies




# 充值的地址
rech_url = "http://test.lemonban.com/futureloan/mvc/api/member/recharge"
# 构建充值的参数
data = {"mobilephone":"15567678989","amount":100}
# 发起请求进行充值
response = requests.post(url=rech_url,data=data,cookies=cookies)

print(response.url)
print(response.request.url)
print(response.request.headers)

from requests.models import PreparedRequest