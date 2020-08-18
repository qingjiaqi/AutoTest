# -*- coding: utf-8 -*-
# @Time     :2020/02/24  17:02
# @Author   :qingjia
# @File     ParallelExecuteTest
import datetime
import os
import re
import sys
import threading
from random import randint
charsList='qwertyuiopasdfghjklzxcvbnm1234567890'


def execute(cmd):
    try:
        print("开始执行命令....",cmd)
        code=os.system(cmd)
        print("执行命令结束...",cmd)
        return code
    except Exception as e:
        print("执行命令：%s异常，错误信息为：%s"%(cmd,e))

def threadProcess(cmds,projectpath,summaryReport):
    # 线程池
    threads=[]
    for cmd in cmds:
        th=threading.Thread(target=execute,args=(cmd,))
        th.start()
        threads.append(th)
    # 获取开始执行用例时间
    executeStartTime=datetime.datetime.now()
    # 等待所有线程执行完毕
    for th in threads:
        th.join() #   等待子线程结束
    # 合并测试报告
    margeReport(projectpath,summaryReport,executeStartTime)

def margeReport(projectpath,summaryReport,executeStartTime):
    # 待合并的文件列表
    fileList=[]
    #用例执行结束时间
    executeEndTime = datetime.datetime.now()
    tempPath=os.path.join(projectpath,'05-Report')
    for dirpath,dirnames,filenames in os.walk(tempPath,followlinks=True):
        if str(dirpath).count(globalValue)>0:
            for filename in filenames:
                if str(filename).startswith('output.xml'):
                    if str(dirpath).count('pabot_results')<=0:
                        dirpath=str(dirpath).replace(projectpath,"")
                        fileList.append('%s\%s'%(dirpath[1:],filename))
    temp=' '.join(fileList)
    if summaryReport:
        outputdir=summaryReport
    else:
        outputdir='%s/05-Report/%s/summaryReport%s'%(projectpath,reportParent,executeEndTime.strftime('%Y%m%d%H%M%S'))
    titleName=os.path.basename(projectpath)
    if not os.path.exists(outputdir):
        os.mkdir(outputdir)
    # 合并报告
    combineCmd='python -m robot.rebot --name %s --output output.xml --reporttitle %s --logtitle %s --outputdir %s %s' %(titleName,titleName,titleName,outputdir,temp)
    print('合并报告的命令是：%s'%combineCmd)
    elapsedTime=(executeEndTime-executeStartTime).seconds
    reportTimeFormat=getReportTimeFormat(elapsedTime)
    execute(combineCmd)
    updatePeportTime(os.path.join(outputdir,'log.html'),reportTimeFormat)
    updatePeportTime(os.path.join(outputdir,'report.html'),reportTimeFormat)
    print('执行耗时：%s'%(reportTimeFormat))

def readFile(fileName):
    fileObject=open(fileName,'rb')
    try:
        global countNum
        content=fileObject.read().decode('utf-8')
        print('-----', content)
        countNum=len(open(fileName,'rb').readlines()) #读取到文件中内容的行数
    except Exception as e:
        print("读取文件异常:%s"%e)
    filePath=content.split('\n')
    return filePath

def clearFileData(reportPath):
    if not os.path.exists(reportPath):
        os.makedirs(reportPath)
    if os.path.exists(reportPath):
        deleteFile(reportPath)

def deleteFile(filepath):
    listObj=os.listdir(filepath)
    for subObj in listObj:
        subPath=os.path.join(filepath,subObj)
        if os.path.isdir(subPath):
            deleteFile(subPath)
            os.rmdir(subPath)
        else:
            os.remove(subPath)
def getReportTimeFormat(elapsedTime):
    mins,secs=divmod(elapsedTime,60)
    hours,mins=divmod(mins,60)
    return '%02d:%02d:%02d' %(hours,mins,secs)

def updatePeportTime(htmlReportPath,elapsedTime):
    with open(htmlReportPath,'rb') as readObj:
        content=readObj.read().decode('utf-8')
        target=re.findall("window.output\[\"stats\"\] = ([\s\S]*?);\n</script",content)
        if target:
            item=re.findall('"elapsed":"(.*?)"',target[0])
            newtarget=target[0].replace(item[0],elapsedTime,2)
        newcontent=content.replace(target[0],newtarget)
    with open(htmlReportPath,'wb') as writeObj:
        writeObj.write(newcontent)
def main():
    param=sys.argv[1:]
    testCaseFile=''
    if param:
        summaryReport=param[0]
        if len(param)>=2:
            testCaseFile=param[1]
    else:
        summaryReport=''
    # 需要执行的用例
    cmds=[]
    # 工程路径
    projectpath=os.getcwd()
    filepath=os.path.join(projectpath,testCaseFile if testCaseFile else 'testplan.txt')
    global  globalValue,reportParent
    globalValue=datetime.datetime.now().strftime('%Y%m%d%H%M%S')
    reportParent=''.join(charsList[randint(0,len(charsList) - 1)]for _ in range(5))+globalValue
    print('文件路径为：%s'%filepath)

    for line in readFile(filepath):
        if len(line)<=0:
            print('文件数据配置错误，可能存在换行符或行数据为空，请检查')
            return
        line =line.split(' ')
        length=line.__len__()
        if not re.match('pybot|pabot',line[0]):
            line.insert(0,'pybot')
            length=line.__len__()
        if not re.match('--extension txt',str(line)):
            line.insert(1,'--extension txt')
            length=line.__len__()
        if countNum>5:
            line.insert(1,'--variable isConcurrentExecute:Y')
            length = line.__len__()
        obj=line[length-1]
        tmpfilePath,tmpfilename=os.path.split(obj)
        testObjName,extension=os.path.splitext(tmpfilename)
        timeStamp=datetime.datetime.now().strftime('%Y%m%d%H%M%S')
        randomName=''.join(charsList[randint(0,len(charsList) - 1)]for _ in range(5))
        tempReport=os.path.join(projectpath,'05-Report',reportParent,
                                testObjName+timeStamp,randomName+'-'+globalValue)
        clearFileData(tempReport)
        testObj=obj if os.path.isabs(obj) else os.path.join(os.getcwd(),obj)
        line[length-1]=testObj
        line.insert(1,'--outputdir')
        line.insert(2,tempReport)
        line=' '.join(item for item in line)
        cmds.append(line)
    if len(cmds)>0:
        threadProcess(cmds,projectpath,summaryReport)

if __name__ =='__main__':
    main()
    # 手动合成报告
    # projectpath='d:/python/项目名'
