# -*- coding: utf-8 -*-
# @Time     :2019/09/05  23:11
# @Author   :qingjia
# @File     tool.py
"""存放各种工具方法"""
import random
def rand_phone():
    """随机生成一个188开头的手机号码"""
    item=""
    for i in range(8):
        i=random.randint(0,9)
        item+=str(i)
        phone="188{}".format(item)
    return phone


def rand_str(length,start="auto",str="qwertyuiopasdfghjklzxcvbnm"):
    """随机生成字符串
    :param  length 输出字符的长度
    :param  start  定义字符的开头
    :param str  字符的选择范围
    """
    item_str=start+"-"
    for i in range(length):
        i=random.choice(str)
        item_str+=i
    return item_str

if __name__ == '__main__':
    # b=""
    # for i in range(4):
    #     a=random.choice("qwertyuiopasdfghjkl")
    #     b+=a
    # print(b)
    print(rand_str(13))