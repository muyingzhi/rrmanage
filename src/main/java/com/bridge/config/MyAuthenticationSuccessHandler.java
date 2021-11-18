package com.bridge.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridge.common.AjaxResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        if(AjaxResult.isAjaxRequest(request)){
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            AjaxResult<String> respBean = AjaxResult.success("登录成功");
            
            out.write(new ObjectMapper().writeValueAsString(respBean));
            out.flush();
            out.close();
        }else{
            
            System.out.println("登录成功");
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
