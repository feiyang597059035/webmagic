package com.carwel.webmagic.fsm;


import com.carwel.webmagic.config.resultcode.ErrorCode;

public class FSMException extends RuntimeException{

    private ErrorCode codeMsg;

    public FSMException(String message) {
        super(message);
    }

    public FSMException(ErrorCode codeMsg) {
        super(codeMsg.getMessage());
        this.codeMsg = codeMsg;
    }

    public FSMException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorCode getErrorCode() {
        return codeMsg;
    }
}
