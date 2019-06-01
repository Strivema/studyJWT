package com.ray;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ray.Ma
 * @date 2019/5/30 9:53
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {

        this.authenticationManager = authenticationManager;

    }

    /**
     * 接收并解析用户凭证，出現错误时，返回json数据前端
     */

    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res){
        try {
            User user =new ObjectMapper().readValue(req.getInputStream(), User.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayList())
            );
        } catch (Exception e) {
            try {
                //未登錄出現賬號或密碼錯誤時，使用json進行提示
                res.setContentType("application/json;charset=utf-8");
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                PrintWriter out = res.getWriter();
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("code",HttpServletResponse.SC_UNAUTHORIZED);
                map.put("message","账号或密码错误！");
                out.write(new ObjectMapper().writeValueAsString(map));
                out.flush();
                out.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }

    /**
     * 用户登录成功后，生成token,并且返回json数据给前端
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth){

        //json web token构建
        String token = Jwts.builder()
                //此处为自定义的、实现org.springframework.security.core.userdetails.UserDetails的类，需要和配置中设置的保持一致
                //此处的subject可以用一个用户名，也可以是多个信息的组合，根据需要来定
                .setSubject(((MyUserDetails) auth.getPrincipal()).getUsername())
                //设置token过期时间，24小時
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))

                //设置token签名、密钥
                .signWith(SignatureAlgorithm.HS512, "MyJwtSecret")

                .compact();

        //返回token
        res.addHeader("Authorization", "Bearer " + token);

        try {
            //登录成功時，返回json格式进行提示
            res.setContentType("application/json;charset=utf-8");
            res.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = res.getWriter();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("code",HttpServletResponse.SC_OK);
            map.put("message","登陆成功！");
            out.write(new ObjectMapper().writeValueAsString(map));
            out.flush();
            out.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}