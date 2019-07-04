package com.qf.springboot_security_demo.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * 自定义权限验证方法
 *
 * @version 1.0
 * @user ken
 * @date 2019/7/4 14:01
 */
@Component
public class PerssionHandler {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 判断登陆者是否有这个权限
     * 参数一：当前请求的url
     * 参数二：当前登录者拥有的url
     * @return
     */
    public boolean hasPerssion(HttpServletRequest request, Authentication authentication){

        //判断当前发送的请求的url
        String requestURI = request.getRequestURI();

        //判断是否通过了身份认证
        Object principal = authentication.getPrincipal();//该方法可以获得通过了security身份认证的UserDetails对象

        if(principal instanceof UserDetails){
            //说明当前已经通过了身份认证

            //进行权限的判定
            UserDetails user = (UserDetails) principal;

            //获得当前登录用户的所有权限
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

            for (GrantedAuthority authority : authorities) {
                System.out.println("请求的url:" + requestURI + " 拥有的url:" + authority.getAuthority());

                if (antPathMatcher.match(requestURI, authority.getAuthority())){
                    System.out.println("拥有该权限！");
                    //拥有权限
                    return true;
                }
            }

            System.out.println("权限不足！");
        }

        return false;
    }

}
