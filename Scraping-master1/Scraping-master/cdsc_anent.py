# -*- coding: UTF-8 -*-
from urllib import request
import socket

if __name__ == "__main__":
    socket.setdefaulttimeout(3)
    #以CSDN为例，CSDN不更改User Agent是无法访问的
    url = 'https://p.3.cn/prices/mgets?skuIds=J_4388882&&pdtk=&pduid'
    head = {}
    #写入User Agent信息
    head['User-Agent'] = 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit 537.36 (KHTML, like Gecko) Chrome'
    proxy ={"http":'171.38.188.52'}
 #创建Request对象
    req = request.Request(url, proxies=proxy)
    #传入创建好的Request对象
    response = request.urlopen(req)
    #读取响应信息并解码
    html = response.read().decode('utf-8')
    #打印信息
    print(html)