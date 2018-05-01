package com.workspace.server.controller

import com.sun.deploy.net.HttpRequest
import com.workspace.server.dao.UserfeedbackEntityDao
import com.workspace.server.dao.UsersEntityDao
import com.workspace.server.interceptor.ContentFormatInterceptor
import com.workspace.server.model.CommoditiesEntity
import com.workspace.server.model.UserfeedbackEntity
import com.workspace.server.model.UsersEntity
import com.workspace.server.service.UserfeedbackEntityService
import com.workspace.server.service.UsersEntityService
import com.workspace.server.util.ContentFormatter
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import javax.validation.Valid
import java.util.stream.Collectors


/**
 * Created by CHENMA11 on 12/29/2017.
 */

@RestController
public class UsersController {

    @Autowired UsersEntityDao userDao
    @Autowired UsersEntityService usersService
    @Autowired UserfeedbackEntityService userfeedbackService
    @Autowired UserfeedbackEntityDao userfeedbackDao
    @ResponseBody
    @RequestMapping("api/register")
    public String greetingSubmit(@RequestBody UsersEntity inputParamer,@RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter ) {
        def  usernamesignup = inputParamer.getUname()
        def  emailsignup =  inputParamer.getuNumber()
        def  passwordsignup = inputParamer.getuPassWord()
            try {
                if (usersService.exists(emailsignup)) {
                    contentFormatter.content().'content' {
                        'outputMess' '-1'
                    }
                    println '用户已存在'
                } else {
                    UsersEntity Users = new UsersEntity()
                    Users.setuNumber(emailsignup)
                    Users.setUname(usernamesignup)
                    Users.setuPassWord(passwordsignup)
                    userDao.save(Users);
                    contentFormatter.content().'content' {
                        'outputMess' '1'
                    }
                    //return 'loginOrReg.html'
                }

            }
            catch (Exception ex) {
                println ex.printStackTrace();
                contentFormatter.content().'content' {
                    'outputMess' '0'
                }
                //return 'loginOrReg.html'
            }
            return contentFormatter.toString()
    }



    @ResponseBody
    @RequestMapping("/api/loginCheck")
     String loginCheck(@RequestBody   UsersEntity search, @RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter) {
        println search.getuNumber()
        println search.getuPassWord()
            try {
                if (usersService.exists(search.getuNumber())) {
                    List<UsersEntity> usesList = usersService.findUsersEntityByuNumber(search.getuNumber())
                    if (usesList[0].getuPassWord().equals(search.getuPassWord())) {
                        contentFormatter.content().'content' {
                            'outputMess' '1'
                            'username' usesList[0].getUname()
                            'userId' usesList[0].getUid()
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
    @RequestMapping("/api/search65435453456454")
    String getSearchResultViaAjax(@RequestBody @Validated UsersEntity search,@RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter) {
            contentFormatter.content().'content' {
                'test' 'test123'
                'passsss'  'jjj'
                'nasme' '用户存在么么'
            }
        println 'jj'
        return contentFormatter.toString()

    }


    @ResponseBody
    @RequestMapping("api/updatePwd")
    public String updatePwdSubmit(@RequestBody UsersEntity inputParamer,@RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter ) {
        def uid = inputParamer.getUid()
        def  passwordsignup = inputParamer.getuPassWord()
        println uid +"  "+ passwordsignup
        try {
            if (usersService.existUid(uid)) {
                UsersEntity  userMess = usersService.findUsersEntityByuid(uid)
                def UName= userMess.getUname()
                UsersEntity Users = new UsersEntity()
                Users.setUid(uid)
                Users.setuNumber(userMess.getuNumber())
                Users.setUname(UName)
                Users.setuPassWord(passwordsignup)
                def resll = userDao.save(Users)
                if(resll){
                    contentFormatter.content().'content' {
                        'outputMess' '修改成功，请重新登录'
                    }
                }

                //return 'loginOrReg.html'
            }else{
                contentFormatter.content().'content' {
                    'outputMess' '修改失败，请重试'
                }
            }

        }
        catch (Exception ex) {
            println ex.printStackTrace();
            contentFormatter.content().'content' {
                'outputMess' '修改失败，请重试'
            }
            //return 'loginOrReg.html'
        }
        return contentFormatter.toString()
    }

    @ResponseBody
    @RequestMapping("api/searchMessByUid") //用户个人信息查询
    public String searchMessByUid(@RequestBody UsersEntity inputParamer,@RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter ) {
        def var_uid= inputParamer.getUid()
        println var_uid
        def jsonOutput = new JsonOutput()
        def result =null
        try {
            if (var_uid!=''&&var_uid!=0) {
                UsersEntity userMess = userDao.findOne(var_uid);
                ((JsonBuilder) contentFormatter.content()) {
                    'CommoditiesEntitys' {
                        'uid' userMess.uid
                        'uname' userMess.uname
                        'uNumber'  userMess.uNumber
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
    @RequestMapping("api/feedbackMsgToSystem") //用户信息反馈信息插入
    public String feedbackMsg(@RequestBody UserfeedbackEntity inputParamer, @RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter ) {
        def uid = inputParamer.getuId()
        def jsonOutput = new JsonOutput()
        def result =null
        try {
            if (uid!=''&&uid!=0) {
                def res = userfeedbackDao.save(inputParamer)
                if(res == 0){
                    result = jsonOutput.toJson("{'result':'0'}")
                }else {
                    result = jsonOutput.toJson("{'result':'-1'}")
                }
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





}

