"""
============================
author:MuSen
time:2019/6/19
E-mail:3247119728@qq.com
============================
"""

from common.http_requests import HTTPRequest2
r = HTTPRequest2()
method = 'POST'

#  1.注册   2.登录
url1 = "http://test.lemonban.com/futureloan/mvc/api/member/login"
data = {"mobilephone": "13120909378", "pwd": "lemonban"}
res0 = r.request(method=method, url=url1, data=data)
print(res0.text)
# 3.充值  4.取现
url2 = "http://test.lemonban.com/futureloan/mvc/api/member/recharge"
data2 = {"mobilephone": "13120909378", "amount": 50000}
res = r.request(method=method, url=url2, data=data2)
print(res.text)
# 5.添加项目
# 登录 准备数据，添加项目

# url5 = "http://test.lemonban.com/futureloan/mvc/api/loan/add"
# data5 = {'memberId': 828, 'title': '世界这么大，借钱去看看',
#         'amount': 50000, 'loanRate': 18.0, 'loanTerm': 6,
#         'loanDateType': 4, 'repaymemtWay': 10, 'biddingDays': 5}
# res5 = r.request(method=method, url=url5, data=data5)
# print(res5.text)

# 审核
# 登录  创建项目  -->审核
# url13 = "http://test.lemonban.com/futureloan/mvc/api/loan/audit"
# data13 = {'id':444,"status":5}
# res13 = r.request(method=method, url=url13, data=data13)
# print(res13.text)


# 6.投资
# 登录  投资
# url6 = "http://test.lemonban.com/futureloan/mvc/api//member/bidLoan"
# data6 = {'memberId':82,'password':"123456","loanId": 20936,'amount':200}
# res6 = r.request(method=method, url=url6, data=data6)
# print(res6.text)

# 7.获取用户列表（不用测，用户量太大，一次加载不出来）
# 登录 获取用户列表



# 8.获取所有的标列表
# 登录：获取所有标的计划
# url8 = "http://test.lemonban.com/futureloan/mvc/api/loan/getLoanList"
# res8 = r.request(method=method,url=url8)
# print('获取标列表\n',res8.json())


# 9.获取用户的投资计划
# 登录：获取用户投资计划
# url4 = "http://test.lemonban.com/futureloan/mvc/api/invest/getInvestsByMemberId"
# data4 = {"memberId":282}
# res4 = r.request(method=method,url=url4,data=data4)
# print(res4.text)


# 10.获取标的投资记录
# 登录  获取标的投资记录

# url10 = 'http://test.lemonban.com/futureloan/mvc/api/invest/getInvestsByLoanId'
# # 标的的id:20936
# loanId = 20936
# data10 = {"loanId": 20936}
# res10 = r.request(method=method, url=url10, data=data10)
# print(res10.text)

# 11 获取用户流水记录
# 用例设计： 登录  获取流水

# url11= "http://test.lemonban.com/futureloan/mvc/api/financelog/getFinanceLogList"
# data11 = {"memberId":282}
# res11 = r.request(method=method, url=url11, data=data11)
# print(res11.text)


# 12.生成回款计划
# 用例设计思路：
# 用户登录，创建项目，审核到核保审批或者终审状态，然后执行测试用例

# url12 = 'http://test.lemonban.com/futureloan/mvc/api/loan/generateRepayments'
# data12 = {"id": 444}
# res12 = r.request(method=method, url=url12, data=data12)
# print(res12.text)
