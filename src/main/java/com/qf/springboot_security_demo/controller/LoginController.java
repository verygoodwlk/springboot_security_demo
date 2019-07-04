package com.qf.springboot_security_demo.controller;

import com.qf.springboot_security_demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @version 1.0
 * @user ken
 * @date 2019/7/4 9:24
 */
@Controller
@SessionAttributes("loginUser")
public class LoginController {

    @Autowired
    private IUserService userService;

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/tologin")
    public String toLogin(){
        return "login";
    }

    /**
     * 权限不足时触发
     * @return
     */
    @RequestMapping("/noperssion")
    public String noPerssion(){

        return "noperssion";
    }

//    /**
//     * 去登录
//     * @return
//     */
//    @RequestMapping("/gologin")
//    public String goLogin(String username, String password, Model model){
//
//        System.out.println("开始去登录了！");
//
//        User user = userService.login(username, password);
//        if(user != null){
//            //登录成功
//            model.addAttribute("loginUser", user);
//            return "index";
//        }
//
//        return "redirect:/tologin?error=1";
//    }
}
