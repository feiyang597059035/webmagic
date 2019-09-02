package com.carwel.webmagic.config.resultcode;

public final class Results {

    /**
     * 成功
     *
     * @return Result<Void>
     */
    public static Result<Void> success() {
        return new DefaultResult<Void>()
                .setCode(ErrorCode.SUCCESS.getCode())
                .setMessage(ErrorCode.SUCCESS.getMessage());
    }

    /**
     * 成功
     *
     * @param data 并设置data参数
     * @param <T>  data的泛型
     * @return Result<T>
     */
    public static <T> Result<T> success(T data) {
        return new DefaultResult<T>()
                .setCode(ErrorCode.SUCCESS.getCode())
                .setMessage(ErrorCode.SUCCESS.getMessage()).setData(data);
    }

   /* public static <T> Result<T> invalid() {
        return new DefaultResult<T>()
                .setCode(CommonCode.INVALID_ARGS.code())
                .setMessage(CommonCode.INVALID_ARGS.message());
    }

    public static <T> Result<T> invalid(String message) {
        return new DefaultResult<T>().setCode(CommonCode.INVALID_ARGS.code()).setMessage(message);
    }

    public static <T> Result<T> invalid(String message, List<Result.ViolationItem> violationItems) {
        return new DefaultResult<T>().setCode(CommonCode.INVALID_ARGS.code()).setMessage(message)
                .setViolationItems(violationItems);
    }

    public static <T> Result<T> invalid(List<Result.ViolationItem> violationItems) {
        return new DefaultResult<T>().setCode(CommonCode.INVALID_ARGS.code())
                .setMessage(CommonCode.INVALID_ARGS.message())
                .setViolationItems(violationItems);
    }*/

    /**
     * 服务异常，即业务逻辑异常
     * 是一种分支条件，或一种不能处理的状态，比如余额不足支付失败导致的异常对应的错误编号
     * ResponseCode 的实现参考 CommonCode
     *
     * @param errorCode 用BaseResponseCode的好处就是强制让大家去继承并实现BaseResponseCode
     * @param <T>         对应data字段的数据类型
     * @return Result<T>
     */
    public static <T> Result<T> failure(ErrorCode errorCode) {
        return new DefaultResult<T>().setCode(errorCode.getCode())
                .setMessage(errorCode.getMessage());
    }



/*    *//**
     * 返回带异常信息的响应结果，当成系统错误（bug）来处理
     *
     * @param e 异常
     *//*
    public static <T> Result<T> error(ServiceErrorException e) {
        return new DefaultResult<T>()
                .setCode(e.getCode())
                .setMessage(e.getMessage())
                .setErrorClass(e.getClass().getName())
                .setErrorStack(ThrowableUtil.getStackTrace(e));
    }*/
/*
    *//**
     * 返回带异常信息的响应结果，当成系统错误（bug）来处理
     *
     * @param throwable 异常
     *//*
    public static <T> Result<T> error(Throwable throwable) {
        return new DefaultResult<T>()
                .setCode(ErrorCode.UNKNOWN_ERROR.code())
                .setMessage(ErrorCode.UNKNOWN_ERROR.message())
                .setErrorClass(throwable.getClass().getName())
                .setErrorStack(ThrowableUtil.getStackTrace(throwable));
    }

    *//**
     * 返回带异常信息的响应结果，可以自己明确的系统错误
     *
     * @param errorCode 错误码
     * @param throwable 异常
     * @param <T>       对应data字段的数据类型
     * @return result 对象
     *//*
    public static <T> Result<T> error(ErrorCode errorCode, Throwable throwable) {
        return new DefaultResult<T>()
                .setCode(errorCode.code())
                .setMessage(errorCode.code())
                .setErrorClass(throwable.getClass().getName())
                .setErrorStack(ThrowableUtil.getStackTrace(throwable));
    }*/

    /**
     * 返回带异常信息的响应结果，可以自己明确的系统错误
     *
     * @param code       错误编号
     * @param message    错误信息
     * @param errorClass 错误类名
     * @param <T>        对应data字段的数据类型
     * @return result 对象
     */
    public static <T> Result<T> error(String code, String message, String errorClass) {
        return new DefaultResult<T>()
                .setCode(code)
                .setMessage(message)
                .setErrorClass(errorClass);
    }

    /**
     * 返回带异常信息的响应结果，可以自己明确的系统错误
     *
     * @param code       错误编号
     * @param message    错误信息
     * @param <T>        对应data字段的数据类型
     * @return result 对象
     */
    public static <T> Result<T> error(String code, String message ){
        return new DefaultResult<T>()
                .setCode(code)
                .setMessage(message);
    }

    /**
     * 构建参数验证失败的项目
     *
     * @param field   字段名称
     * @param message 信息
     * @return 参数验证失败的项目
     */
    public static Result.ViolationItem buildViolationItem(String field, String message) {
        return new DefaultResult.DefaultVioationItem(field, message);
    }
}