package com.carwel.webmagic.config.resultcode;



public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 4005854589880923284L;

    public BusinessException(CodeMsg codeMsg) {
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
