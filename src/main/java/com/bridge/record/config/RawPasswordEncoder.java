package com.bridge.record.config;

import org.springframework.security.crypto.password.PasswordEncoder;
/**
 * 原始密码比较
 */
public class RawPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        // TODO Auto-generated method stub
        return String.valueOf(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // TODO Auto-generated method stub
        return String.valueOf(rawPassword).equals(encodedPassword);
    }
    
}
