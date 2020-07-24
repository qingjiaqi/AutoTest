# -*- coding: utf-8 -*-
# @Time     :2019/09/01  16:01
# @Author   :qingjia
# @File     logger.py
import os
import logging
from common.config import conf
from common.constant import LOG_DIR
# 获取到日志文件配置信息
logger_name=conf.get("logs","logger_name")
level=conf.get("logs","level")
sh_level=conf.get("logs","sh_level")
fh_level=conf.get("logs","fh_level")
log_file=conf.get("logs","log_file")
# 获取日志文件路径
file=os.path.join(LOG_DIR,"log.log")
class MyLogging(object):
    """自定义日志类"""
    #这个方法是直接调用父类的
    def __new__(cls, *args, **kwargs):#这种写法可以直接返回一个新的对象
        # 创建自己的日志收集器
        my_log=logging.getLogger(logger_name)
        #设置日志收集等级
        my_log.setLevel(level)
        # 创建输出渠道(控制台)
        l_s=logging.StreamHandler()
        l_s.setLevel(sh_level)
        # 创建一个输出渠道(文件)
        f_s=logging.FileHandler(file,encoding="utf-8")
        f_s.setLevel(fh_level)
        #将日志收集器与输出渠道关联
        my_log.addHandler(l_s)
        my_log.addHandler(f_s)
        #定义日志输出格式
        ft = '%(asctime)s - [%(filename)s-->line:%(lineno)d] - %(levelname)s: %(message)s'
        ft=logging.Formatter(ft)
        # 设置日志输出格式
        l_s.setFormatter(ft)
        f_s.setFormatter(ft)
        return my_log  #返回日志收集器
logger=MyLogging()
if __name__ == '__main__':
    logger.info("111")
    logger.warning("2222")
    logger.error("3333")