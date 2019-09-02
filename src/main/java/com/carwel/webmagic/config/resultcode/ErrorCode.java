package com.carwel.webmagic.config.resultcode;

public enum ErrorCode {
    // 通用的错误码
     SUCCESS ("0", "success"),
     SERVER_ERROR ("500100", "服务端异常"),
     BIND_ERROR("500101", "参数校验异常"),
     REQUEST_ILLEGAL("500102", "请求非法"),
     VERIFY_CODE_ILLEGAL ("500103", "验证码错误"),
     ACCESS_LIMIT_REACHED ("500104", "访问太频繁！"),
     DATO_NOT_EXIST  ("500104", "数据不存在！"),
     DUPLICATE_ERROR ("500105", "重复插入！"),
     STATUS_NULL_ERROR  ("500106", "状态为null！"),
     STATUS_ILLEGAL_ERROR ("500107", "状态非法！");


    /**
     * 响应码
     */
    private  String code;

    /**
     * 响应消息
     */
    private  String message;

    /**
     * 构造函数
     *
     * @param code 响应码
     * @param message 消息
     */
    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
