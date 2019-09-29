package com.itbounds.dev.apiinvokelog.api.results;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 接口响应结构封装
 * @Author blake
 * @Date 2019/9/29 3:02 下午
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class ApiResult {

  private static final String SUCCESS = "success";

  private static final String FAILED = "failed";

  // 状态描述
  private String status;
  // 具体响应内容
  private Object data;
  // 提示信息描述
  private String message;
  // 状态码
  private int code;

  public ApiResult(Object data) {
    this.status = SUCCESS;
    this.data = data;
    this.message = "";
    this.code = 200;
  }

  public ApiResult(String message, int code) {
    this.status = FAILED;
    this.data = null;
    this.message = message;
    this.code = code;
  }

}
