package com.bridge.record.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

public class VerifyCodeFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        if("/rrlogin".equals(request.getServletPath())){
            String requestCode = request.getParameter("verifyCode");
            String genCode = (String)request.getSession().getAttribute("verify");
            String contextPath = (request.getContextPath());
            String url = contextPath + "/login/login";
            if(!StringUtils.hasLength(requestCode)){
                response.sendRedirect(url+"?verifyEmpty");
                return;
            }
            if(!genCode.equals(requestCode)){
                response.sendRedirect(url+"?verifyError");
                return;
            }
        }
        chain.doFilter(req, response);
    }
    
}
