package com.workspace.server.controller

import com.workspace.server.dao.CommoditiesEntityDao
import com.workspace.server.dao.CommoditiesLogEntityDao
import com.workspace.server.dao.UserCommoditiesLogEntityDao
import com.workspace.server.interceptor.ContentFormatInterceptor
import com.workspace.server.model.CommoditiesEntity
import com.workspace.server.model.CommoditiesLogEntity
import com.workspace.server.model.UsercommoditylogEntity
import com.workspace.server.service.CommditiesEntityService
import com.workspace.server.service.CommditiesLogEntityService
import com.workspace.server.service.UserCommditiesEntityService
import com.workspace.server.util.ContentFormatter
import groovy.json.JsonOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class UsersCommodiesController {

     @Autowired UserCommoditiesLogEntityDao commDao
     @Autowired UserCommditiesEntityService commService

    @ResponseBody
    @RequestMapping("/api/InsertSelfGoods") //已经登陆的用户进行订阅新的商品
    String InsertSelfGoodsViaAjax(@RequestBody UsercommoditylogEntity InputSearch, @RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter) {
        println InputSearch.getuId()
        def input = InputSearch.getuId()
        def jsonOutput = new JsonOutput()
        //搜索框传进来的数据可能是链接，也有可能是关键字，首先要进行判断是否为链接，链接则直接查询链接，不是链接的话就进行商品名称的模糊搜索
        def result =null
       if(input!= 0 &&input!= ''){
           //查找所有该用户已经订阅的商品，看是否已存在该商品，
           // 若存在，则不再插入数据库，返回已订阅标识，否则执行插入操作
           def usersGoodsByUID= commService.findUsercommoditylogEntityByuId(input)
           def existCID = false
           usersGoodsByUID.each{current ->
               if(current.getcId()==InputSearch.getcId()){
                   existCID = true
               }
           }
           if(!existCID ){
               UsercommoditylogEntity usercommodity  = new UsercommoditylogEntity()
               usercommodity.setcId(InputSearch.getcId())
               usercommodity.setuId(InputSearch.getuId())
               usercommodity.setInFocus(0)
               def insertResult = commDao.save(usercommodity)
               if(insertResult == 0){
                   contentFormatter.content().'content' {
                       'outputMess' '新增成功'
                   }

               }else{
                   contentFormatter.content().'content' {
                       'outputMess' '新增失败，请检查网络'
                   }

               }
           }else{
               contentFormatter.content().'content' {
                   'outputMess' '已存在该商品，请到个人库中查找'
               }

           }
       }else{
           contentFormatter.content().'content' {
               'outputMess' '\'用户未登录，请登录再进行订阅'
           }


       }
        return contentFormatter.toString()
    }


    @ResponseBody
    @RequestMapping("/api/SearchSelfGoods") //已经登陆的用户查看所有的已订阅商品
    String SearchSelfGoodsViaAjax(@RequestBody UsercommoditylogEntity InputSearch, @RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter) {
        println InputSearch.getuId()
        def input = InputSearch.getuId()
        def jsonOutput = new JsonOutput()
        //搜索框传进来的数据可能是链接，也有可能是关键字，首先要进行判断是否为链接，链接则直接查询链接，不是链接的话就进行商品名称的模糊搜索
        def result =null
        if(input!= ''){
            //查找所有该用户已经订阅的商品
            def usersGoodsByUID= commService.findUsercommoditylogEntityByuId(input)
            result = jsonOutput.toJson(usersGoodsByUID)
        }else{
            result = jsonOutput.toJson("{'result':'用户未登录，请登录再进行订阅'}")
            println("连接存在返回结果："+result)

        }

        return result.toString()//contentFormatter.toString()
    }



}
