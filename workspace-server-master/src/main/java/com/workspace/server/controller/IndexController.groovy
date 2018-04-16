package com.workspace.server.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class IndexController {


    @RequestMapping("/")
    public String loginOrRegister(Map<String,Object> map) {
        return 'loginOrREG.html'
    }


    @RequestMapping("/404")
    public String return404(Map<String,Object> map) {
        return 'template/moban/404.html'
    }
}
