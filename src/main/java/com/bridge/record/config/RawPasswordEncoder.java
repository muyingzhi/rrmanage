package com.bridge.record.config;

import org.springframework.security.crypto.password.PasswordEncoder;
/**
 * 原始密码比较
 */
public class RawPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        // 不加密了
        return String.valueOf(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // 直接用原始密码比较
        return String.valueOf(rawPassword).equals(encodedPassword);
    }
    
}
