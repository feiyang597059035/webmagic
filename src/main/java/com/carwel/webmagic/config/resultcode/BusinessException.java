package com.carwel.webmagic.config.resultcode;



public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 4005854589880923284L;

    public BusinessException(ErrorCode codeMsg) {
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMessage();
    }

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
