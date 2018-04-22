package com.workspace.server.controller

import com.workspace.server.dao.CommoditiesEntityDao
import com.workspace.server.dao.CommoditiesLogEntityDao
import com.workspace.server.dao.UserCommoditiesLogEntityDao
import com.workspace.server.interceptor.ContentFormatInterceptor
import com.workspace.server.model.CommoditiesEntity
import com.workspace.server.model.CommoditiesLogEntity
import com.workspace.server.model.UsercommoditylogEntity
import com.workspace.server.model.UsersEntity
import com.workspace.server.service.CommditiesEntityService
import com.workspace.server.service.CommditiesLogEntityService
import com.workspace.server.service.UserCommditiesEntityService
import com.workspace.server.util.ContentFormatter
import groovy.json.JsonOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CommodiesController {

    @Autowired CommoditiesEntityDao useCommoditiesEntityDaorDao
    @Autowired CommditiesEntityService CommditiesEntityService
//    @Autowired CommoditiesLogEntityDao commDao
//     @Autowired CommditiesLogEntityService commService


    @ResponseBody
    @RequestMapping("/goodsLog/search") //前端搜索功能Service ，返回json list ,在前端显示所有搜索数据(数据量多会分页)
    String getSearchResultViaAjax(@RequestBody CommoditiesEntity InputSearch, @RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter) {
        println InputSearch.getcLink()
        def input = InputSearch.getcLink()
        def jsonOutput = new JsonOutput()
        //搜索框传进来的数据可能是链接，也有可能是关键字，首先要进行判断是否为链接，链接则直接查询链接，不是链接的话就进行商品名称的模糊搜索
        def result =null
        if(input.contains(".jd")||input.contains(".com")||input.contains(".html")){
            //判断链接是否已存在
            if(CommditiesEntityService.exists(input)){
                def outputList = CommditiesEntityService.findCommoditiesEntityByCLink(input)
                result = jsonOutput.toJson(outputList)
                println("连接存在返回结果："+result)
            }else{
                try {
                    def ff = input//"https://item.jd.com/4613593.html"
                    Process proc =Runtime.getRuntime().exec("D:/A_GIT_MANTI/Scraping-master1/Scraping-master/pp2.bat  "+ff);
                    def jj=proc.waitFor(); //得到进程运行结束后的返回状态，如果进程未运行完毕则等待知道执行完毕
                    println proc.exitValue() //输出进程返回状态 0 为成功， 1 为失败
//                    Reader reader = new InputStreamReader(proc.getInputStream());
//                    BufferedReader bf = new BufferedReader(reader);
//                    String line = null;
//                        while((line=bf.readLine())!=null) {
//                            System.out.println(line);
//                       } not used
                    //成功则根据链接查找数据库，并将数据存到商品实时记录表 ,返回相应数据存到json 对象 ，返回给前端
                    if(jj==0) {
                        def outputList = CommditiesEntityService.findCommoditiesEntityByCLink(input)
                        //实例化对象
                        CommoditiesLogEntity log =  new CommoditiesLogEntity()
                        log.setCid(outputList[0].getCid())
                        log.setClCount(Integer.parseInt(outputList[0].getcCount()))
                        log.setClPrice(outputList[0].getcLowestPrice())
                        log.setClName(outputList[0].getcName())
                        //将数据存在数据库（商品实时记录表），并返回结果， 0 表示 成功 ， 1 表示 失败
                        def insertLogResult = commDao.save(log)
                        if(insertLogResult == 0){
                            println "success"
                        }
                        result = jsonOutput.toJson(outputList)
                        println("hello "+result)
                    }else{
                        result = jsonOutput.toJson("{'result':'-1'}")
                        println result
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result = jsonOutput.toJson("{'result':'-2'}")
                    System.out.println("执行命令：" + "notepad" + "错误");
                }
            }

        }
        return result.toString()//contentFormatter.toString()
    }



}
