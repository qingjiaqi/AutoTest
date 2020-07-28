# -*- coding: utf-8 -*-
# @Time     :2019/08/31  15:41
# @Author   :qingjia
# @File     test_json.py
import json
# 一、json.dumps()   将python对象编码成json字符串
# dic_data={"name":"张三","sex":'男'}
# json_data=json.dumps(dic_data,ensure_ascii=False) #ensure_ascii=False不输出ascii码
# print(json_data)
# print(type(json_data)) #<class 'str'>

# 二、json.loads()        将已编码的json字符串为python对象字典
# json_data='{"age":"18","hight":"165"}' #json里面的数据必须是双引号，否则会报错
# dic_data=json.loads(json_data)
# print(dic_data)  #输出{'hight': '165', 'age': '18'}
# print(type(json_data)) #输出<class 'str'>

# 三、json.dump() 	写入json文件
dic_data={"name":"张三","sex":'男'}
with open('test.json',"a",encoding="utf8") as f:
    f.write("\n")
    json.dump(dic_data,f,ensure_ascii=False)

# 四、json.loads()  读取json文件 这个方法只能读取一行数据
# with open('test.json',"r") as f:  #读取json文件
#     res=json.load(f)
#     print(res)  #结果 {'name': 'qj', 'age': '18', 'gender': 'nan'}
#     print(type(res)) #结果 <class 'dict'>