package com.carwel.webmagic.config.resultcode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


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
            return Results.error(biz.getCode(),biz.getMsg());
        } else {
            if (isAjax(reqest)) {
                return Results.error(ErrorCode.SERVER_ERROR.getCode(), e.getMessage());
            } else {
               /* ModelAndView mav = new ModelAndView();
                mav.addObject("exception", e);
                mav.addObject("url", reqest.getRequestURL());
                if (response.getStatus() == 0) {
                    mav.setViewName("hello");
                } else {
                    mav.setViewName("hello");
                }

                return mav;*/
                return Results.error(ErrorCode.SERVER_ERROR.getCode(), e.getMessage());
            }
        }

    }

    // 判断是否是ajax请求
    private static boolean isAjax(HttpServletRequest httpRequest) {
        return (httpRequest.getHeader("X-Requested-With") != null
            && "XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With").toString()));
    }


  /*  @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result noHandlerFoundException(HttpServletRequest request,
                                          NoHandlerFoundException ex) {

        logThrowable(false, request, ex);
        return Results.failure(CommonCode.DATA_NOT_FOUND);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public Result constraintViolationException(HttpServletRequest request,
                                               ConstraintViolationException ex) {

        logThrowable(false, request, ex);

        List<Result.ViolationItem> violationItems = ValidationUtils.convertToResultViolationItems(
                ex.getConstraintViolations());

        if (violationItems != null) {
            return Results.invalid(violationItems);
        }
        return Results.invalid();
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Result methodArgumentNotValidException(HttpServletRequest request,
                                                  MethodArgumentNotValidException ex) {
        List<FieldError> errorList = ex.getBindingResult().getFieldErrors();
        Result result = invalid(errorList);
        if (logger.isInfoEnabled()) {
            logger.info("[" + request.getMethod() + "] " + request.getRequestURI() +
                    (StringUtils.isEmpty(request.getQueryString()) ? "" : "?" + request.getQueryString()) + " " +
                    ex.getClass().getName() + ":" + result.toString());
        }
        return result;
    }

    private Result invalid(List<FieldError> errors) {
        if (errors != null && errors.size() > 0) {
            return Results.invalid(errors.stream().map(x ->
                    Results.buildViolationItem(x.getField(),
                            x.getDefaultMessage())
            ).collect(Collectors.toList()));
        }
        return Results.invalid();
    }

    @ExceptionHandler(value = {BindException.class})
    public Result bindException(HttpServletRequest request,
                                BindException e) {

        List<FieldError> errorList = e.getBindingResult().getFieldErrors();
        Result result = invalid(errorList);

        if (logger.isInfoEnabled()) {
            logger.info("[" + request.getMethod() + "] " + request.getRequestURI() +
                    (StringUtils.isEmpty(request.getQueryString()) ? "" : "?" + request.getQueryString()) + " " +
                    e.getClass().getName() + ":" + result.toString());
        }
        return result;
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public Result missingServletRequestParameterException(
            HttpServletRequest request,
            MissingServletRequestParameterException e) {

        logThrowable(false, request, e);
        return Results.invalid(CommonCode.INVALID_ARGS.message()).addViolationItem(e.getParameterName(),
                "不能为空");
    }

    @ExceptionHandler(value = {InvalidPropertyException.class})
    public Result invalidPropertyException(HttpServletRequest request,
                                           InvalidPropertyException e) {
        logThrowable(false, request, e);
        return Results.invalid().addViolationItem(e.getPropertyName(), CommonCode.INVALID_ARGS.message());
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public Result httpRequestMethodNotSupportedException(
            HttpServletRequest request,
            HttpRequestMethodNotSupportedException ex) {

        logThrowable(false, request, ex);
        return Results.failure(CommonCode.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = {ServletRequestBindingException.class})
    public Result servletRequestBindingException(HttpServletRequest request,
                                                 ServletRequestBindingException ex) {

        logThrowable(false, request, ex);
        return Results.invalid(ex.getMessage());
    }

    @ExceptionHandler(value = {ServiceValidException.class})
    public Result serviceException(HttpServletRequest request,
                                   ServiceValidException ex) {
        logThrowable(false, request, ex);
        return Results.invalid(ex.getMessage(), ex.getViolationItems());
    }

    @ExceptionHandler(value = {ServiceException.class})
    public Result serviceException(HttpServletRequest request,
                                   ServiceException ex) {
        logThrowable(false, request, ex);
        return Results.failure(ex);
    }

    @ExceptionHandler(value = {ServiceErrorException.class})
    public Result serviceSysException(HttpServletRequest request,
                                      ServiceErrorException ex) {
        logThrowable(true, request, ex);
        return Results.error(ex);
    }

    @ExceptionHandler(value = Throwable.class)
    public Result defaultErrorHandler(HttpServletRequest request,
                                      Throwable throwable) {
        // 先行执行自定义异常增强
        Optional<Result> handleResult = this.customExceptionHandling(throwable);
        if (handleResult.isPresent()) {
            return handleResult.get();
        }
        logThrowable(true, request, throwable);
        return Results.error(throwable);
    }

    private Optional<Result> customExceptionHandling(Throwable e) {
        if (Objects.isNull(customExceptionHandler)) {
            return Optional.empty();
        }
        Result result = null;
        try {
            result = customExceptionHandler.handleGlobalRestControllerException(e);
        } catch (Throwable e1) {
            logger.error("Return result exception advice occurrence of user exception", e1);
        }
        return Optional.ofNullable(result);
    }

    private void logThrowable(boolean error, HttpServletRequest request, Throwable throwable) {
        if (error) {
            logger.error("[" + request.getMethod() + "] " + request.getRequestURI() +
                            (StringUtils.isEmpty(request.getQueryString()) ? "" : "?" + request.getQueryString()) + " ",
                    throwable);
        } else if (logger.isInfoEnabled()) {
            logger.info("[" + request.getMethod() + "] " + request.getRequestURI() +
                    (StringUtils.isEmpty(request.getQueryString()) ? "" : "?" + request.getQueryString()) + " " +
                    (throwable instanceof ServiceException ?
                            throwable.toString() :
                            (throwable.getClass().getName() + ":" + throwable.getMessage())));
        }
    }*/

}
