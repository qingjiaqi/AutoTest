# -*- coding: utf-8 -*-
# @Time     :2019/08/23  22:11
# @Author   :qingjia
# @File     test.py
# 将两个元组转为字典
zip_objct=zip(("key1","key2","key3","key4"),("value1","value2","value3"))
print("这是压缩后的对象",zip_objct)
list_data=list(zip_objct)
print("这是转换成list后的结果",list_data)
dic_data=dict(list_data)
print("这是转换成字典后的结果",dic_data)
