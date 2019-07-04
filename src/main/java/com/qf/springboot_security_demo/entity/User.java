package com.qf.springboot_security_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @version 1.0
 * @user ken
 * @date 2019/7/4 9:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable, UserDetails {

    private Integer id;
    private String username;
    private String password;
    private String name;

    /**
     * 该方法是用来告诉SpringSecurity当前用户有哪些权限
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //按道理该数据应该从数据库中查询出来

        List<GrantedAuthority> authorities = new ArrayList<>();

        if(username.equals("admin")){
            authorities.add(new SimpleGrantedAuthority("/user/insert"));
            authorities.add(new SimpleGrantedAuthority("/user/update"));
        } else if(username.equals("root")){
            authorities.add(new SimpleGrantedAuthority("/user/insert"));
            authorities.add(new SimpleGrantedAuthority("/user/delete"));
            authorities.add(new SimpleGrantedAuthority("/user/query"));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
