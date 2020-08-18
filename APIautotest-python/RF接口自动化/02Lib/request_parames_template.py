# -*- coding: utf-8 -*-
# @Time     :2020/08/17  20:52
# @Author   :qingjia
# @Email   :834789582@qq.com
# @File     request_parames_template

class request_parames_template:
    createVolume = '''
        {
        "data":{
            "name":"${volumeName}",
            "snapshotId":"${snapshotId}",
            "size":${volumeSize},
            "type":"${volumeType}"
            }
        }
    '''
