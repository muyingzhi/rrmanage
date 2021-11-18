package com.bridge.config;

import com.bridge.common.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 全局异常处理
 */
@RestControllerAdvice
@Component
@Slf4j
public class ExceptionHandle {

    /**
     * 功能描述：自定义异常和全局异常捕捉处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public AjaxResult handle(Exception e) {
            log.error("系统异常[{}]", e);
            return AjaxResult.error("系统异常",e.getMessage());
    }

    /**
     * 功能描述：from数据校验异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public AjaxResult valid(BindException e, HttpServletRequest request) {
        printlnException(request, e);
        AjaxResult j = new AjaxResult();
        j.setMsg("error");
        j.setCode(-406);
        // j.put("data", e.getLocalizedMessage());
        if (!e.getBindingResult().hasErrors()) {
            j.setMsg( "http状态码406，不接受From数据校验异常!");
            return j;
        }
        j.setMsg( e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return j;
    }


    /**
     * 功能描述：Json数据校验异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public AjaxResult valid(MethodArgumentNotValidException e, HttpServletRequest request) {
        printlnException(request, e);
        AjaxResult j = new AjaxResult();
        j.setMsg("error");
        j.setCode(-406);
        // j.put("data", e.getLocalizedMessage());
        if (!e.getBindingResult().hasErrors()) {
            j.setMsg("http状态码406，不接受Json数据校验异常!");
            return j;
        }
        j.setMsg( e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return j;
    }


    /**
     * 功能描述：异常数据的打印
     *
     * @param request
     * @param e
     * @throws IOException
     */
    private void printlnException(HttpServletRequest request, Throwable e) {
        // 相关记录
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        // 日志打印
        log.error("******************************");
        log.error("出错详细日志：url:[{}],method:[{}],uri:[{}],params:[{}]", url, method, uri, queryString);
        log.error("出错异常:", e);
        log.error("******************************");
    }
}