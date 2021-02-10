package com.bridge.record.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

public class VerifyCodeFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest)req;
        if("/rrlogin".equals(request.getServletPath())){
            String requestCode = request.getParameter("verifyCode");
            String genCode = (String)request.getSession().getAttribute("verify");
            if(StringUtils.isEmpty(requestCode)){
                throw new AuthenticationServiceException("验证码不能为空");
            }
            if(!genCode.equals(requestCode)){
                throw new AuthenticationServiceException("验证码错误");
            }
        }
        chain.doFilter(req, response);
    }
    
}
