
# -*- coding: UTF-8 -*-  
#爬取京东商品详情页信息
#2017/7/30

import requests
import sys
from bs4 import BeautifulSoup
import os
import csv
import re
import json
import time
import pymysql
import urllib.request

#爬取页面链接
class JDMonitoringEngine():
    def make_a_link(keyword,page):
        try:
                r = requests.get("https://search.jd.com/Search?keyword=" + keyword +'&enc=utf-8&page=' + str(2*page-1))
                r.raise_for_status
                #print('正在爬取第{}页...'.format(page))
                #print('---'*45)
                r.encoding = 'gbk'
                return r.text
        except:
                print('链接错误！！！')
                return ''


#爬取页面链接
    def find_only_link(html):
        soup = BeautifulSoup(html,'lxml')
        links = soup.find_all('div',class_='gl-i-wrap')
        return (link.find('div',class_='p-name p-name-type-2').a['href'] for link in links)#页面链接的生成表达式

#链接单页面
    def link_to_url(self,link):
        try:
                #time.sleep(1)
                r = requests.get(link)
                r.raise_for_status
                r.encoding = 'gbk'
                return r.text
        except:
                print('此页无法链接！！！')
                return ''


#爬取商品价格
    def getprice(self,purl,price_old):
        uid = re.match(r'.+?(\d+).+',purl).group(1)
        html = self.link_to_url(purl)
        price = re.findall('<del id="page_origin_price">(.*?)</del>', html)
        url_p = 'https://p.3.cn/prices/mgets?skuIds=J_' + uid+'&&pdtk=&pduid'
        #print(url_p)
        content = self.link_to_url('https://p.3.cn/prices/mgets?skuIds=J_4824733&&pdtk=&pduid')
        jd = json.loads(content.lstrip('[').rstrip(']\n'))#生成json数据格式
        #print(content)
        #print(content)
        if price:
            return price[0][1:]
        elif "id" in jd:
            return jd["p"]
        else:
            return price_old


#爬取商品评论
    def getcomment(self,purl):
        uid = re.match(r'.+?(\d+).+',purl).group(1)
        #print(uid)
        content = self.link_to_url('https://club.jd.com/comment/productCommentSummaries.action?referenceIds=' + uid)
        jd = json.loads(content)
        comment = []
        jds = jd['CommentsCount'][0]
        comment.append(jds['CommentCountStr'])#评论数
        comment.append(jds['GoodCountStr'])#好评数
        comment.append(jds['GoodRate'])#好评率
        return comment

#爬取商品名称
    def getname(self,purl):
        uid = re.match(r'.+?(\d+).+',purl).group(1)
        #print(uid)
        content = self.link_to_url('https://c.3.cn/recommend?&methods=accessories&sku=' + uid + '&cat=9987%2C653%2C655')
        content2 = self.link_to_url(purl)
        name_re = re.compile(r"name: '(.*?)',")
        name = re.findall(name_re, content2)[0]
        #print(name)#将其转换为中文
        #print(content)
        try:
            jd = json.loads(content)
            if jd['accessories']['data']['wName']:
                return jd['accessories']['data']['wName']
            else:
                return name
        except:
            return ''
        

#爬取卖家
    def getseller(self,purl):
        uid = re.match(r'.+?(\d+).+',purl).group(1)
        content = self.link_to_url('https://chat1.jd.com/api/checkChat?pid=' + uid + '&returnCharset=utf-8')
        try:
                jd = json.loads(content.lstrip('null(').rstrip(');'))
                try:
                        return jd['seller']
                except:
                        return ''
        except:
                ''

#保存到csv
    def save_to_csv(ulist,keyword):
        path = 'D:/数据/'
        if not os.path.exists(path):
                os.mkdir(path)
        with open(path + '京东' + keyword + '数据.csv','w+' ) as f:
                writer = csv.writer(f)
                writer.writerow(['商品','价格','店铺','链接','评论数','好评数','好评率'])
                for i in range(len(ulist)):
                        if ulist[i] and ulist[i][0]:
                                writer.writerow([ulist[i][0],ulist[i][1],ulist[i][2],ulist[i][3],ulist[i][4],ulist[i][5],ulist[i][6]])

    def conndb(self):  
        self.db = pymysql.connect(  
                host = '127.0.0.1',  
                port = 3306,  
                user = 'root',  
                password = '',  
                db = 'shiro',  
                charset = 'utf8',  
        )  
        cursor = self.db.cursor()  
        return cursor  
	
    def save(self,list):
        cursor = self.conndb()
        sql1 = "SELECT CId FROM commodities  WHERE CLink = '%s'" % (list[3])
        #print(sql1)
        # 执行SQL语句
        cursor.execute(sql1)
        # 获取所有记录列表
        results = cursor.fetchone()
        #print(results[0])
        sql = "INSERT INTO `commodities_log`( `CID`, `CLPrice`) values( %s,%s);"
        cursor.execute(sql,(results[0],list[1]))
	#.format(name,seller_name,default_price))  
        self.db.commit()  

 #主函数
    def relmain(keyword):#高阶函数
        def main(page):
                r = re.compile(r'.*?html')
                ulist = []
                for p in range(page):
                        p += 1
                        text = make_a_link(keyword,p)
                        for url in find_only_link(text):
                                ul = []
                                if r.match(url):
                                        if getname(url):
                                                ul.append(getname(url))#商品名称
                                                print(getname(url))
                                                ul.append(getprice(url))#价格
                                                ul.append(getseller(url))#店铺
                                                ul.append('https:' + url)#链接
                                                print('https:' + url)
                                                ul.extend(getcomment(url))#评论
                                                print('*-*' * 45)
                                                
                                
                                ulist.append(ul)
                
                save_to_csv(ulist,keyword)
        return main


if __name__ == '__main__':
    j = JDMonitoringEngine()
    cursor = j.conndb()
    sql1 = "SELECT CLink,CLowestPrice FROM commodities "
    # 执行SQL语句
    cursor.execute(sql1)
    # 获取所有记录列表
    results = cursor.fetchall()
    print(results)
    for url in results:
        ul = []
        print(j.getname(url[0]))
        ul.append(j.getname(url[0]))#商品名称
        ul.append(j.getprice(url[0],url[1]))#价格
        ul.append(j.getseller(url[0]))#店铺
        ul.append(url[0])#链接
        #print('https:' + url)
        #print(j.getprice(url))
        #print(j.getcomment(url))
        ul.extend(j.getcomment(url[0]))#评论
        j.save(ul)