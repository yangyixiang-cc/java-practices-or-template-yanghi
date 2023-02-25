package com.yanghi.study.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class LoginUser implements UserDetails {

    private User user;

    private List<String> permissions;


    @JSONField(serialize = false) //不序列化
    private List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();

    public LoginUser() {
    }

    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    //获取权限信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(simpleGrantedAuthorities.size() > 0){
            return simpleGrantedAuthorities;
        }

        for(String permission: permissions){
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(permission));
        }
        return simpleGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
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
