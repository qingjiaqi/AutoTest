# -*- coding: utf-8 -*-
# @Time     :2019/09/18  23:36
# @Author   :qingjia
# @File     python_ic
# 变量集合的三种方法
# colors = ['red', 'green', 'blue', 'yellow']
# # 变量集合方法一
# for i in colors:
#     print(i)
# print("第一种变量方法结束")
# #  方法二变量并拿出索引值
# for i in range(len(colors)):
#     print("{}----{}".format(i,colors[i]))
# print("第二种变量方法结束")
# #方法三：enumerate()循环时可以同时列出数据和数据下标
# for i ,color in enumerate(colors):
#     print("{}----{}".format(i, color))
# print("第三种变量方法结束")
#
# # 拼接字符串的两种方法
# names = ['raymond', 'rachel', 'matthew', 'roger',
# 'betty', 'melissa', 'judith', 'charlie']
# # 方法一
# s = names[0]
# for name in names[1:]:
#     s += ','+ name
# print (s)
# # 方法二将列表拼接成字符串以"，"隔开
# print(",".join(names))
# # 拼接字典，只会拼接key
# dic={"name":"zhang","sex":"nan"}
# print("-".join(dic))
#
# with open('data.txt',encoding="utf8") as f:
#     data = f.read()
# print(data)

# 列表推到式,生成一个列表
# 方法一
# result = []
# for i in range(10):
#     s = i*2
#     result.append(s)
# print(result)
# # 方法二
# a=[i*2   for i in range(10)]
# 创建一个二维数组
a=[[i*2  if i%2!=0 else 'q'for i in range(10)] for j in range(2) ]
print(a)

# # 遍历字典的 key 和 value
# d={"name":"zhang","sex":"nan"}
# # 方法一速度没那么快，因为每次迭代的时候还要重新进行hash查找 key 对应的 value。
# for k in d:
#     print (k, '--->', d[k])
# # 方法二 遇到字典非常大的时候，会导致内存的消耗增加一倍以上
# for k, v in d.items():
#     print (k, '--->', v)
