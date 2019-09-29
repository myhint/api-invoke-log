package com.itbounds.dev.apiinvokelog.handler;

import com.itbounds.dev.apiinvokelog.api.results.AckTransfer;
import com.itbounds.dev.apiinvokelog.api.results.ApiResult;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @Description 全局异常处理器定义和私化定制
 * @Author blake
 * @Date 2019/9/29 3:00 下午
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseBody
  public ApiResult handleMissingServletRequestParameterException(
      MissingServletRequestParameterException e,
      HttpServletRequest request) {
    String message = "缺失请求参数" + e.getParameterName();
    return ackTransfer(request, message, HttpStatus.BAD_REQUEST.value(), e);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseBody
  public ApiResult handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException e,
      HttpServletRequest request) {
    String message = "请求参数" + e.getName() + "类型错误";
    return ackTransfer(request, message, HttpStatus.BAD_REQUEST.value(), e);
  }

  @ExceptionHandler(HttpMessageConversionException.class)
  @ResponseBody
  public ApiResult handleHttpMessageNotReadableException(HttpMessageConversionException e,
      HttpServletRequest request) {
    String message = "参数类型错误";
    return ackTransfer(request, message, HttpStatus.BAD_REQUEST.value(), e);
  }

  /**
   * Default Exception Handler
   */
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ApiResult handleException(Exception e, HttpServletRequest request) {
    return ackTransfer(request, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e, true);
  }

  private ApiResult ackTransfer(HttpServletRequest request, String message, int code,
      Exception e,
      boolean printStackTrace) {
    ApiResult response = AckTransfer.fail(message, code);
    if (printStackTrace) {
      logger.error(message, e);
    } else {
      logger.error(message);
    }
    return response;
  }

  /**
   * transfer ACK
   */
  private ApiResult ackTransfer(HttpServletRequest request, String message, int code,
      Exception e) {
    return ackTransfer(request, message, code, e, false);

  }

}
