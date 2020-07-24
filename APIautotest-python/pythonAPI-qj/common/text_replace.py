# -*- coding: utf-8 -*-
# @Time     :2019/09/03  21:46
# @Author   :qingjia
# @File     text_replace.py
import  re
from  common.config import conf
from common.logger import logger
class ConText:
    # 用来存储临时变量
    pass
def replace(data):
    """将传入数据中需要替换的变量替换
    :param data 需要替换变量的数据
    """
    p="#(.+?)#" #定义一个匹配模板
    while re.search(p,data):#从data中匹配数据
        key=re.search(p,data).group(1) #搜索到要替换的数据作为取值的key
        try:
            value=conf.get('test_data',key) #获取到变量的值
        except: #如果配置文件中没有就从临时变量中拿 ConText 这个类中
            try:
                value=getattr(ConText,key)
            except:
                logger.error("这个【{}】变量没有设置值，请检查".format(key))
        data=re.sub(p,value,data,count=1) #完成替换
    logger.info("数据替换完成【{}】".format(data))
    return data

# if __name__ == '__main__':
#     str4 = "sadas#python#oioew#python#4123123#python#"
#     # p = "(#.+?#)"
#     key = replace(str4)  #<_sre.SRE_Match object; span=(5, 13), match='#python#'>
#     print(key)
#     # res = re.sub(p, "-*java*-", str4)
#     # print(res)