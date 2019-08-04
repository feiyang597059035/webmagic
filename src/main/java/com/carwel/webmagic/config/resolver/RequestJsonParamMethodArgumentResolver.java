/**
 * XinGuang
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.carwel.webmagic.config.resolver;

import com.alibaba.fastjson.JSONObject;

import com.carwel.webmagic.config.annotation.JsonParams;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;


public class RequestJsonParamMethodArgumentResolver extends RequestParamMethodArgumentResolver {

    /**
     * @param useDefaultResolution
     */
    public RequestJsonParamMethodArgumentResolver(boolean useDefaultResolution) {
        super(useDefaultResolution);
    }

    /** 
     * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#supportsParameter(MethodParameter)
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getMethod().getAnnotation(JsonParams.class) != null;
    }

    /** 
     * @see RequestParamMethodArgumentResolver#resolveName(String, MethodParameter, NativeWebRequest)
     */
    @Override
    protected Object resolveName(String name, MethodParameter parameter,
                                 NativeWebRequest request) throws Exception {
        HttpServletRequest servletRequest = request.getNativeRequest(HttpServletRequest.class);
        HttpInputMessage inputMessage = new ServletServerHttpRequest(servletRequest);
        inputMessage = new EmptyBodyCheckingHttpInputMessage(inputMessage);

        InputStream is = inputMessage.getBody();
        if (is != null && is.available() != 0) {
            JSONObject json = JSONObject.parseObject(is, JSONObject.class);
            JsonParamsHolder.setJsonParams(json);
        }

        String paramKey = parameter.getParameterName();
        if (JsonParamsHolder.getJsonParams() == null || JsonParamsHolder.getJsonParams().isEmpty()) {
            return null;
        }
        Object value = JsonParamsHolder.getJsonParams().get(paramKey);
        return value;
    }
    

    private static class EmptyBodyCheckingHttpInputMessage implements HttpInputMessage {

        private final HttpHeaders headers;

        private final InputStream body;

        private final HttpMethod method;


        public EmptyBodyCheckingHttpInputMessage(HttpInputMessage inputMessage) throws IOException {
            this.headers = inputMessage.getHeaders();
            InputStream inputStream = inputMessage.getBody();
            if (inputStream == null) {
                this.body = null;
            }
            else if (inputStream.markSupported()) {
                inputStream.mark(1);
                this.body = (inputStream.read() != -1 ? inputStream : null);
                inputStream.reset();
            }
            else {
                PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream);
                int b = pushbackInputStream.read();
                if (b == -1) {
                    this.body = null;
                }
                else {
                    this.body = pushbackInputStream;
                    pushbackInputStream.unread(b);
                }
            }
            this.method = ((HttpRequest) inputMessage).getMethod();
        }

        @Override
        public HttpHeaders getHeaders() {
            return this.headers;
        }

        @Override
        public InputStream getBody() throws IOException {
            return this.body;
        }

        public HttpMethod getMethod() {
            return this.method;
        }
    }
}
