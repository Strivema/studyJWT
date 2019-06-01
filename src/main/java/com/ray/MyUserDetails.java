package com.ray;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Ray.Ma
 * @date 2019/5/30 9:52
 */
public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    //登录用户名
    private String username;
    //登录密码
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }


    public String getPassword() {
        return this.password;
    }


    public String getUsername() {
        return this.username;
    }


    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}