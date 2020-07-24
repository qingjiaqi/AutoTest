# -*- coding: utf-8 -*-
# @Time     :2019/08/31  16:44
# @Author   :qingjia
# @File     deco.py 装饰器

"""
装饰器，如果被装饰的方法有参数那么装饰器要在内层定义对应的参数传给内层的方法
如果有返回值需要将被装饰的方法的返回值返回
"""
# 第二个装饰器
def  func_1(fun1):
    def wrapper_1(a):
        print("第二个装饰器新增的功能")
        res=fun1(a)
        return res
    return wrapper_1

# 第一个装饰器 (装饰器就是闭包)
@func_1
def  func(fun):
    def wrapper():
        print("第一个装饰器新增的功能")
        fun()
    return wrapper

 # 最开始的方法，希望用个装饰器来装饰
@func #通过上面的方法来装饰 @func 等价于add=func(add)
def add():
     print("这是最开始的方法")
add()

