# -*- coding: utf-8 -*-
# @Time     :2019/09/01  16:20
# @Author   :qingjia
# @File     constant.py
"""
封装一些动态获取常量
项目中使用的各种目录绝对路径

"""
import os
# 获取项目的根路径 __file__表示获取当前文件绝对路径
# os.path.dirname(__file__)表示返回上级目录可以多次返回
BASE_DIR=os.path.dirname(os.path.dirname(__file__))

# 获取配置文件存放的位置
CONFIG_DIR=os.path.join(BASE_DIR,"conf")
# 获取日志目录存放位置
LOG_DIR=os.path.join(BASE_DIR,"logs")
# 获取测试数据存放位置
CASE_DATA=os.path.join(BASE_DIR,"data")

# 测试用例存放的目录路径
CASE_DIR = os.path.join(BASE_DIR, 'testcase')

# 测试报告存放的目录路径
REPORT_DIR = os.path.join(BASE_DIR, 'reports')

