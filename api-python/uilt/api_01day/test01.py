"""
============================
author:MuSen
time:2019/6/10
E-mail:3247119728@qq.com
============================
"""
import requests

session = requests.sessions.Session()

# 注册
res_url = 'http://test.lemonban.com/futureloan/mvc/api/member/register?mobilephone=13122221212&pwd=123456&regname=2323'
re1 = session.get(res_url)
print('-------------1---------------')
print(re1.text)

# 登录
lo_url = 'http://test.lemonban.com/futureloan/mvc/api/member/login'
data = {"mobilephone": "13122221212", "pwd": "123456"}
re2 = session.post(url=lo_url, data=data)
print('-------------2---------------')
print(re2.text)

# 充值
re_url = "http://test.lemonban.com/futureloan/mvc/api/member/recharge"
re_data = {"mobilephone": "13122221212","amount":100}
re3 = session.post(url=re_url,data=re_data)
print('-------------3---------------')
print(re3.text)
