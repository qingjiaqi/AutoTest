"""
============================
author:MuSen
time:2019/6/12
E-mail:3247119728@qq.com
============================
"""
from requests.sessions import Session

# 创建一个session对象(会话)
# 作用：自动保存上次请求的cookie信息
session = Session()

# 登录
login_data = {"mobilephone": "15567678989", "pwd": "123qwe"}
login_url = "http://test.lemonban.com/futureloan/mvc/api/member/login"

res1 = session.post(url=login_url, data=login_data)
print(res1.text)
print(dict(res1.cookies))

# 充值的地址
rech_url = "http://test.lemonban.com/futureloan/mvc/api/member/recharge"
# 构建充值的参数
data = {"mobilephone": "15567678989", "amount": 100}
response = session.post(url=rech_url, data=data)

print(response.text)

# 取现的接口
url = "http://test.lemonban.com/futureloan/mvc/api/member/withdraw"

withdraw_data = {"mobilephone": "13233334444", "amount": 200}

response = session.post(url=url,data=withdraw_data)

print(response.text)

print(response.cookies)

session.close()