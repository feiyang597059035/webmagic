package com.carwel.webmagic.config.resultcode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String IMOOC_ERROR_VIEW = "error";

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest reqest, HttpServletResponse response, Exception e) throws Exception {
        e.printStackTrace();
        if (e instanceof BusinessException) {
            /*if (((BusinessException)e).getCode() == 500210) {
                ModelAndView mav = new ModelAndView();
                mav.setViewName("login");
                return mav;
            }*/
            BusinessException biz=(BusinessException)e;
            return Result.write(biz.getCode(),biz.getMsg());
        } else {
            if (isAjax(reqest)) {
                return Result.write(CodeMsg.SERVER_ERROR, e.getMessage());
            } else {
                ModelAndView mav = new ModelAndView();
                mav.addObject("exception", e);
                mav.addObject("url", reqest.getRequestURL());
                if (response.getStatus() == 0) {
                    mav.setViewName("hello");
                } else {
                    mav.setViewName("hello");
                }

                return mav;
            }
        }

    }

    // 判断是否是ajax请求
    private static boolean isAjax(HttpServletRequest httpRequest) {
        return (httpRequest.getHeader("X-Requested-With") != null
            && "XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With").toString()));
    }

}
