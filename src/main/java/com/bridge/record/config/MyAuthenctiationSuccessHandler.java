package com.bridge.record.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenctiationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        if(isAjaxRequest(request)){
            response.setContentType("application/json;charset=utf-8");

        }else{
            RequestCache cache = new HttpSessionRequestCache();
            SavedRequest savedRequest = cache.getRequest(request, response);
            String contextPath = (request.getContextPath());
            // 如果来源请求为空则跳转到首页
            String url = contextPath + "/";
            // if((savedRequest==null)){
            //     url = "/blog/"+ SecurityUtil.getLoginUser();
            // }else{
                // url = savedRequest.getRedirectUrl();
            // }
    
            System.out.println("登录成功，跳转到："+url);
    
            response.sendRedirect(url);
        }
    }
    public boolean isAjaxRequest(HttpServletRequest request){  
        String header = request.getHeader("X-Requested-With");  
        boolean isAjax = "XMLHttpRequest".equals(header) ? true:false;  
        return isAjax;  
    } 
}
