package com.ray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Ray.Ma
 * @date 2019/5/30 9:47
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService myCustomUserService;

    @Autowired
    private MyPasswordEncoder myPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭跨站请求防护
                .cors().and().csrf().disable()
                //允许不登陆就可以访问的方法，多个用逗号分隔
                .authorizeRequests().antMatchers("/test").permitAll()
                //其他的需要授权后访问
                .anyRequest().authenticated()

                .and()
                //增加登录拦截
                .addFilter(new JWTLoginFilter(authenticationManager()))
                //增加是否登陸过滤
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                // 前后端分离是无状态的，所以暫時不用session，將登陆信息保存在token中。
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //覆盖UserDetailsService类
        auth.userDetailsService(myCustomUserService)
                //覆盖默认的密码验证类
                .passwordEncoder(myPasswordEncoder);
    }
}