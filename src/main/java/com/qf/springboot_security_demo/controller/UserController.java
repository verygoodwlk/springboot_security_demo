package com.qf.springboot_security_demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @user ken
 * @date 2019/7/4 9:21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @ResponseBody
    @RequestMapping("/insert")
    public String insert(){
        System.out.println("--->insert");
        return "insert";
    }

    @RequestMapping("/update")
    public String update(){
        System.out.println("--->update");
        return "update";
    }

    @RequestMapping("/delete")
    public String delete(){
        System.out.println("--->delete");
        return "delete";
    }


    @RequestMapping("/query")
    public String query(){
        System.out.println("--->query");
        return "query";
    }
}
