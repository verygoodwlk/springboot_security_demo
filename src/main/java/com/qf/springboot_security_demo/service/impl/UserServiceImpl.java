package com.qf.springboot_security_demo.service.impl;

import com.qf.springboot_security_demo.entity.User;
import com.qf.springboot_security_demo.service.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @user ken
 * @date 2019/7/4 9:28
 */
@Service
public class UserServiceImpl implements IUserService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //按道理应该调用dao层查询数据库
        User user = null;
        if(username.equals("admin")){
            user = new User(1, username, new BCryptPasswordEncoder().encode("123456"), "小明");
        } else if (username.equals("root")) {
            user = new User(2, username, new BCryptPasswordEncoder().encode("123456"), "小红");
        } else {
            throw new UsernameNotFoundException("用户不存在！");
        }

        return user;
    }

//    @Override
//    public User login(String username, String password) {
//
//        //按道理应该调用dao层查询数据库
//        User user = null;
//        if(username.equals("admin") && password.equals("123456")){
//            user = new User(1, username, password, "小明");
//        } else if (username.equals("root") && password.equals("123456")) {
//            user = new User(2, username, password, "小红");
//        }
//
//        return user;
//    }


}
