package com.workspace.server.controller

import com.fasterxml.jackson.annotation.JsonBackReference
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
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class UsersCommodiesController {

     @Autowired UserCommoditiesLogEntityDao commDao
     @Autowired UserCommditiesEntityService commService
     @Autowired CommditiesEntityService  goodsService

    @ResponseBody
    @RequestMapping("/api/InsertSelfGoods") //已经登陆的用户进行订阅新的商品
    String InsertSelfGoodsViaAjax(@RequestBody UsercommoditylogEntity InputSearch, @RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter) {
        println InputSearch.getuId()
        def input = InputSearch.getuId()
        def jsonOutput = new JsonOutput()
        //搜索框传进来的数据可能是链接，也有可能是关键字，首先要进行判断是否为链接，链接则直接查询链接，不是链接的话就进行商品名称的模糊搜索
        def result =null
        try{
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
                    if(insertResult){
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
                    'outputMess' '用户未登录，请登录再进行订阅'
                }
            }
        }catch(Exception e){
            e.printStackTrace()
            contentFormatter.content().'content' {
                'outputMess' '新增失败，请检查网络'
            }
        }
        return contentFormatter.toString()
    }


    @ResponseBody
    @RequestMapping("/api/SearchSelfGoods") //已经登陆的用户查看所有的已订阅商品(分页版)
    String SearchSelfGoodsViaAjax(@RequestBody SearchCriteria InputSearch, @RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter) {
        def input = InputSearch.getUid()
        def currentpage = 1
        //搜索框传进来的数据可能是链接，也有可能是关键字，首先要进行判断是否为链接，链接则直接查询链接，不是链接的话就进行商品名称的模糊搜索
        def result =null
        if(InputSearch.getCurrentpage()>0){
            currentpage = InputSearch.getCurrentpage()
        }
        List<CommoditiesEntity> List = new ArrayList<CommoditiesEntity>()
        if(input!= ''){
            //查找所有该用户已经订阅的商品 ,并查询到商品的具体信息（CommoditiesEntity）
            def listcount= commService.findUsercommoditylogEntityByuId(input).size()
            def usersGoodsByUID= commService.getList(currentpage,3,input)
            if(usersGoodsByUID){
                usersGoodsByUID?.each{current_Goods->
                    CommoditiesEntity out = goodsService.findCommoditiesEntityBycid(current_Goods.getcId())
                    if(out != null) {
                        List.add(out)
                    }
                }
            }
            ((JsonBuilder) contentFormatter.content()) {
                'CommoditiesEntitys' List, { CommoditiesEntity commoditiesEntity ->
                    'cid' commoditiesEntity.cid
                    'cPicture' commoditiesEntity.cPicture
                    'cLink'  commoditiesEntity.cLink
                    'cName'  commoditiesEntity.cName
                    'cLowestPrice'  commoditiesEntity.cLowestPrice
                }
                'listcounts' listcount
            }
            result = contentFormatter.content()
        }else{ //UID 为空，则用户未登录 ,将提示信息存在result,返回json
            contentFormatter.content().'content' {
                'outputMess' '用户未登录，请登录再进行订阅'
            }
            result = contentFormatter//jsonOutput.toJson("{'result':'用户未登录，请登录再进行订阅'}")
            println("连接返回结果："+result)
        }
        return result.toString()//contentFormatter.toString()
    }


    @ResponseBody
    @RequestMapping("/api/SearchSelfGoodsNoPage") //已经登陆的用户查看所有的已订阅商品(不分页，用来管理数据)
    String SearchSelfGoodsNoPage(@RequestBody SearchCriteria InputSearch, @RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter) {
        def input= InputSearch.getUid()
        def jsonOutput = new JsonOutput()
        def result = null
        def map =[:]
        def mmm=[]
        List<CommoditiesEntity> List = new ArrayList<CommoditiesEntity>()
        if(input!= ''){
            //查找所有该用户已经订阅的商品 ,并查询到商品的具体信息（CommoditiesEntity）
            def usersGoodsByUID= commService.findUsercommoditylogEntityByuId(input)
            if(usersGoodsByUID){
                usersGoodsByUID?.each{current_Goods->
                    CommoditiesEntity out = goodsService.findCommoditiesEntityBycid(current_Goods.getcId())
                    if(out != null) {
                        //map[current_Goods.getUcId()]= out //.put(current_Goods.getUcId())
                        List.add(out)
                        mmm.add(current_Goods.getUcId())
                    }
                }
            }
            map.each{
                println it?.key
                println it?.value
            }
            def i = 0
           ((JsonBuilder) contentFormatter.content()) {
                'CommoditiesEntitys' List, { CommoditiesEntity commoditiesEntity ->
                    'ucId' mmm[i]
                    'cid' commoditiesEntity.cid
                    'cPicture' commoditiesEntity.cPicture
                    'cLink'  commoditiesEntity.cLink
                    'cName'  commoditiesEntity.cName
                    'cLowestPrice'  commoditiesEntity.cLowestPrice
                    i++
                }
                //'listcounts' listcount
            }
            println  contentFormatter.content()
            result = contentFormatter.content()//jsonOutput.toJson(List)
        }else{
            result = jsonOutput.toJson("{'result':'用户未登录，请登录再进行订阅'}")
        }
        return result.toString()//contentFormatter.toString()
    }

    @ResponseBody
    @RequestMapping("/api/deloneGood") //已经登陆的用户查看所有的已订阅商品(不分页，用来管理数据,删除单个)
    String delSelfOneGood(@RequestBody UsercommoditylogEntity InputSearch, @RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter) {
        def input= InputSearch.getUcId()
        if(input!= ''){
            def delResult=commDao.delete(input);
            if(delResult){
                contentFormatter.content().'content' {
                    'outputMess' '成功删除'
                }
            }else{
                contentFormatter.content().'content' {
                    'outputMess' '删除结果：失败'
                }
            }
        }else{
            contentFormatter.content().'content' {
                'outputMess' '用户未登录，请登录再进行订阅'
            }
        }
        return contentFormatter.toString()
    }

    @ResponseBody
    @RequestMapping("/api/delGoods") //已经登陆的用户查看所有的已订阅商品(不分页，用来管理数据,同时删除多条记录)
    String delSelfGoods(@RequestBody List<UsercommoditylogEntity> InputSearchList, @RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter) {
        try{
            if(InputSearchList!= null) {
                    InputSearchList?.each { current_comm ->
                        println current_comm?.getUcId()
                        if (current_comm?.getUcId() != 0) {
                            def delResult = commDao.delete(current_comm?.getUcId());
                            contentFormatter.content().'content' {
                                'outputMess' '成功删除选中数据'
                            }
                        }
                }
            }else{
                contentFormatter.content().'content' {
                    'outputMess' '未选择数据，请重试'
                }
            }
        }catch (Exception ex){
            ex.printStackTrace()
            contentFormatter.content().'content' {
                'outputMess' '系统出错，请重试'
            }
        }
        return contentFormatter.toString()
    }


}
