package com.workspace.server.controller

import com.workspace.server.dao.CommoditiesEntityDao
import com.workspace.server.dao.CommoditiesLogEntityDao
import com.workspace.server.interceptor.ContentFormatInterceptor
import com.workspace.server.model.CommoditiesEntity
import com.workspace.server.model.CommoditiesLogEntity
import com.workspace.server.service.CommditiesEntityService
import com.workspace.server.service.CommditiesLogEntityService
import com.workspace.server.util.ContentFormatter
import groovy.json.JsonOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class CommodiesLogController {

    @Autowired CommoditiesLogEntityDao CommoditiesLogDao
    @Autowired CommditiesLogEntityService CommditiesLogService
//    @Autowired CommoditiesLogEntityDao commDao
//     @Autowired CommditiesLogEntityService commService


    @ResponseBody
    @RequestMapping("/goodsLog/FindOneGoodPriceLog") //前端搜索功能Service ，返回json list ,在前端显示所有搜索数据(数据量多会分页)
    String getSearchResultViaAjax(@RequestBody CommoditiesLogEntity InputSearch, @RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter) {
        def cid = InputSearch.getCid()
        def jsonOutput = new JsonOutput()
        //搜索框传进来的数据可能是链接，也有可能是关键字，首先要进行判断是否为链接，链接则直接查询链接，不是链接的话就进行商品名称的模糊搜索
        def result =null
        println cid
        println "ggg"+CommditiesLogService.exists(cid)
        if(cid) {
            //判断链接是否已存在

            if (CommditiesLogService.exists(cid)) {
                def outputList = CommditiesLogService.findCommoditiesLogEntityBycid(cid)
                result = jsonOutput.toJson(outputList)
                println result
            } else {
                result = jsonOutput.toJson("{'result':'-1'}")
                println result

            }

        }

        return result.toString()//contentFormatter.toString()
    }



}
