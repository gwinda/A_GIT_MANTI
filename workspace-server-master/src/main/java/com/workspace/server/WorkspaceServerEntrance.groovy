package com.workspace.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class WorkspaceServerEntrance {

    static void main(String[] args){
        SpringApplication.run(WorkspaceServerEntrance.class, args)
    }

}
