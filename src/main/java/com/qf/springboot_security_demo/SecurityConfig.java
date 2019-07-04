package com.qf.springboot_security_demo;

import com.qf.springboot_security_demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @version 1.0
 * @user ken
 * @date 2019/7/4 9:57
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IUserService userService;

    /**
     * 重写Security提供的核心配置方法
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/tologin")
                    .loginProcessingUrl("/gologin")
                    .failureUrl("/tologin?error=1").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .authorizeRequests()
                    .mvcMatchers("/static/**", "*.js", "*.css").permitAll()
                    .mvcMatchers("/").authenticated()
                    .anyRequest().access("@perssionHandler.hasPerssion(request, authentication)")
                .and()
                .csrf().disable()
                .exceptionHandling().accessDeniedPage("/noperssion");
    }

    /**
     * 配置自定义的身份认证（登录）过程
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
