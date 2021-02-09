package com.bridge.record.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class myPasswordEncoder implements PasswordEncoder {

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
