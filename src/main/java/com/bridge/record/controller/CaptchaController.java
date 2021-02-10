package com.bridge.record.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;

@Controller
@RequestMapping("/login")
public class CaptchaController {

    @GetMapping("captcha.jpg")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(200, 60, 4, 20);
        request.getSession().setAttribute("verify", captcha.getCode());
        ServletOutputStream out = response.getOutputStream();
        captcha.write(out);
        out.flush();
    }
}
