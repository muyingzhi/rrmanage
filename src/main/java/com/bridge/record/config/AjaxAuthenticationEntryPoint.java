package com.bridge.record.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridge.common.AjaxResult;

import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
            if(authException instanceof InsufficientAuthenticationException){
                if(AjaxResult.isAjaxRequest(request)){
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(500);
                    PrintWriter out = response.getWriter();
                    out.write(AjaxResult.error("未登录").toString());
                    out.flush();
                    out.close();
                }else{
                    request.getPathInfo();
                    response.sendRedirect("/rr/login/login");
                }
            }        
    }

    
}
