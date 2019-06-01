package com.ray;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Ray.Ma
 * @date 2019/5/30 9:49
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {

    public String encode(CharSequence charSequence) {
        //加密方法可以根据自己的需要修改
        return charSequence.toString();
    }

    public boolean matches(CharSequence charSequence, String s) {
        return encode(charSequence).equals(s);
    }
}