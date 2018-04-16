package com.workspace.server.controller

import com.workspace.server.dao.CommoditiesEntityDao
import com.workspace.server.interceptor.ContentFormatInterceptor
import com.workspace.server.model.CommoditiesEntity
import com.workspace.server.service.CommditiesEntityService
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

    @ResponseBody
    @RequestMapping("/goodsLog/search") //前端搜索功能Service ，返回json list ,在前端显示所有搜索数据(数据量多会分页)
    String getSearchResultViaAjax(@RequestBody CommoditiesEntity InputSearch, @RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter) {
        println InputSearch.getcLink()
        def input = InputSearch.getcLink()
        //搜索框传进来的数据可能是链接，也有可能是关键字，首先要进行判断是否为链接，链接则直接查询链接，不是链接的话就进行商品名称的模糊搜索
        def result =null
        if(input.contains(".com")){
            //判断链接是否已存在
            if(CommditiesEntityService.exists(input)){
                def outputList = CommditiesEntityService.findCommoditiesEntityByCLink(input)
                def jsonOutput = new JsonOutput()
                result = jsonOutput.toJson(outputList)
                println(result)
                contentFormatter.content().'content' {
                    'test' 'test123'
                }
            }else{

            }

        }

        contentFormatter.content().'content' {
            'test' 'test123'
        }

        println 'jj'

        return result.toString()//contentFormatter.toString()
    }

}
