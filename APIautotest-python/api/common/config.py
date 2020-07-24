"""
============================
author:MuSen
time:2019/6/3
E-mail:3247119728@qq.com
============================
"""

import os
import configparser
from common.constant import CONF_DIR
"""
为什么要封装？
    封装是为了使用起来更加方便，
    
封装的需求？
    封装成什么样子才能达到我们的目的
    
封装的原则：
    写死的固定数据(变量)，可以封装成类属性
    实现某个功能的代码封装成方法
    在各个方法中都要用到的数据，抽离出来作为实例属性
    
"""


# 封装前读使用，读取数据
# conf-qj = configparser.ConfigParser()
# conf-qj.read('config.ini',encoding='utf8')
# conf-qj.get('excel','file_name')


class ReadConfig(configparser.ConfigParser):
    """创建实例时会得到一个加载文件后的对象"""
    def __init__(self):
        # 实例化对象，完成超继承可以使用父类的方法
        super().__init__()
        # 创建加载配置文件的对象
        r = configparser.ConfigParser()
        # 读取开关文件,
        # 加载文件
        r.read(os.path.join(CONF_DIR, 'env.ini'), encoding='utf-8')
        #传入区域和key值，获取到文件中的值
        switch = r.get('env','switch')
        # 判断开关的值，选择加载环境的配置文件
        if switch == "1":
            self.read(os.path.join(CONF_DIR, 'config1.ini'), encoding='utf-8')
        else:
            self.read(os.path.join(CONF_DIR, 'config.ini'), encoding='utf-8')
#创建一个对象，这个别的模块需要用的时候就直接导入这个对象不需要创建
conf = ReadConfig()

if __name__ == '__main__':
    print(conf.get("env","url"))
