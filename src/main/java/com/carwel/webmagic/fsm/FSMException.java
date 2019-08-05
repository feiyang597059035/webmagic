package com.carwel.webmagic.fsm;


import com.carwel.webmagic.config.resultcode.CodeMsg;

public class FSMException extends RuntimeException{

    private CodeMsg codeMsg;

    public FSMException(String message) {
        super(message);
    }

    public FSMException(CodeMsg codeMsg) {
        super(codeMsg.getMsg());
        this.codeMsg = codeMsg;
    }

    public FSMException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodeMsg getErrorCode() {
        return codeMsg;
    }
}
