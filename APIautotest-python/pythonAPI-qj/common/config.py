# -*- coding: utf-8 -*-
# @Time     :2019/09/01  16:13
# @Author   :qingjia
# @File     config.py 读取配置文件类
import os
import configparser
from  common.constant import CONFIG_DIR



class ReadConfig(configparser.ConfigParser):
    """创建一个实例时会得到一个加载了文件后的对象"""
    def __init__(self):
        # 实例化对象，完成超继承可以使用父类的方法
        super().__init__()
        # 创建加载配置文件的对象
        # r=configparser.ConfigParser()
        # 读取配置文件
        self.read(os.path.join(CONFIG_DIR,"config.ini"),encoding="utf8")
#创建一个对象，当别的模块导入时直接使用这个对象
conf=ReadConfig()

if __name__ == '__main__':
    print(conf.get("logs","level"))