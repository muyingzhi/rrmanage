package com.bridge.record.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridge.common.AjaxResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    public MyAuthenticationFailureHandler(){
        this.setDefaultFailureUrl("/login/login?error");
    }
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        
                // 异步请求的返回json
        if(AjaxResult.isAjaxRequest(request)){
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            AjaxResult<String> respBean = AjaxResult.error("登录失败："+exception.getMessage());
            
            out.write(new ObjectMapper().writeValueAsString(respBean));
            out.flush();
            out.close();        
        }else{
            super.onAuthenticationFailure(request, response, exception);
        }
    }
    
}
