package com.workspace.server.controller

import com.workspace.server.dao.UserfeedbackEntityDao
import com.workspace.server.dao.UsersEntityDao
import com.workspace.server.interceptor.ContentFormatInterceptor
import com.workspace.server.model.AdministratorEntity
import com.workspace.server.model.UserfeedbackEntity
import com.workspace.server.model.UsersEntity
import com.workspace.server.service.AdministratorEntityService
import com.workspace.server.service.UserfeedbackEntityService
import com.workspace.server.service.UsersEntityService
import com.workspace.server.util.ContentFormatter
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * Created by CHENMA11 on 12/29/2017.
 */

@RestController
public class AdminController {

    @Autowired UsersEntityDao userDao
    @Autowired UsersEntityService usersService
    @Autowired UserfeedbackEntityService userfeedbackService
    @Autowired UserfeedbackEntityDao userfeedbackDao
    @Autowired AdministratorEntityService AdministratorEntityService



    @ResponseBody
    @RequestMapping("/api/AdministratorLoginCheck")
     String loginCheck(@RequestBody   AdministratorEntity search, @RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter) {
        println search.getaNumber()
            try {
                if (AdministratorEntityService.exists(search.getaNumber())) {
                    List<AdministratorEntity> AdministratorList = AdministratorEntityService.findAdministratorEntityByaNumber(search.getaNumber())
                    if (AdministratorList[0].getaPassword().equals(search.getaPassword())) {
                        contentFormatter.content().'content' {
                            'outputMess' AdministratorList[0].getAid()
                        }
                    } else {
                        contentFormatter.content().'content' {
                            'outputMess' '0'
                        }
                    }
                } else {
                    contentFormatter.content().'content' {
                        'outputMess' '-1'
                    }
                }
            }
            catch (Exception ex ) {
                println '读取数据库失误 ~' + ex.printStackTrace()
                contentFormatter.content().'content' {
                    'outputMess' '-2'
                }
            }
            return contentFormatter.toString()
      //  }
    }




    @ResponseBody
    @RequestMapping("api/searchAllUsersMess") //用户个人信息查询
    public String searchMessByUid(@RequestBody UsersEntity inputParamer,@RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter ) {
        def var_uid= inputParamer.getUid()
        def jsonOutput = new JsonOutput()
        def result =null
        try {
            if (var_uid!=''&&var_uid!=0) {
                List<UsersEntity> userMess = userDao.findAll();

                ((JsonBuilder) contentFormatter.content()) {
                    'CommoditiesEntitys' userMess, {UsersEntity usersEntity ->
                        'uid' usersEntity.uid
                        'uname' usersEntity.uname
                        'uNumber'  usersEntity.uNumber
                    }
                }
                println contentFormatter.toString()
                result =  contentFormatter.toString()//jsonOutput.toJson(userMess)
            }else{
                result = jsonOutput.toJson("{'result':'-1'}")
            }

        }
        catch (Exception ex) {
            println ex.printStackTrace();
            result = jsonOutput.toJson("{'result':'-1'}")
        }
        return result.toString()
    }


    @ResponseBody
    @RequestMapping("api/SeeAllfeedbackMsg") //管理员查看用户信息反馈
    public String SeeAllfeedbackMsg(@RequestBody UserfeedbackEntity inputParamer, @RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter ) {
        def jsonOutput = new JsonOutput()
        def result =null
        try {
            def ouputList = userfeedbackDao.findAll()
            if(ouputList){
                result = jsonOutput.toJson(ouputList)
            }else {
                result = jsonOutput.toJson("{'result':'-1'}")
            }
        }
        catch (Exception ex) {
            println ex.printStackTrace();
            result = jsonOutput.toJson("{'result':'-1'}")
        }
        return result.toString()
    }
    @ResponseBody
    @RequestMapping("api/DelSelectedfeedbackMsg") //管理员查看用户信息反馈
    public String DelSelectedfeedbackMsg(@RequestBody List<UserfeedbackEntity> inputParamerList, @RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter ) {
        def jsonOutput = new JsonOutput()
        def result =null
        def map= []
        try {
        if(inputParamerList != null){
            inputParamerList.each{current_feedback->
                def ouputList = userfeedbackDao.delete(current_feedback.getUfId())
                map.add(ouputList)
            }
            if(!map.contains(1)){
                contentFormatter.content().'content' {
                    'outputMess' '删除成功'
                }
            }else {
                contentFormatter.content().'content' {
                    'outputMess' '删除失败'
                }
            }
        }
        }
        catch (Exception ex) {
            println ex.printStackTrace();
            contentFormatter.content().'content' {
                'outputMess' '删除失败,检查网络连接'
            }
        }

        return contentFormatter.content().toString()
    }


}

