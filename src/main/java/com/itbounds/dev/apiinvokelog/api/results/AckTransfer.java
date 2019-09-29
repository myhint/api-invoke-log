package com.itbounds.dev.apiinvokelog.api.results;

/**
 * @Description 接口响应高层bean
 * @Author blake
 * @Date 2019/9/29 3:02 下午
 * @Version 1.0
 */
public class AckTransfer {

  private static final int EXCEPTION_CODE = 0;

  public static ApiResult fail(String message, int code) {
    return new ApiResult(message, code);
  }

  public static ApiResult fail(String message) {
    return new ApiResult(message, EXCEPTION_CODE);
  }

}
