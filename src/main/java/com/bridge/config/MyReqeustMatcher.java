package com.bridge.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

@Service
public class MyReqeustMatcher implements RequestMatcher {

    @Override
    public boolean matches(HttpServletRequest request) {
        // TODO Auto-generated method stub
        return true;
    }
    
}
