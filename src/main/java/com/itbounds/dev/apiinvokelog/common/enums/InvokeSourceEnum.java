package com.itbounds.dev.apiinvokelog.common.enums;

/**
 * @Description 接口请求流向源头和目的地Enum定义
 * @Author blake
 * @Date 2019/9/29 2:32 下午
 * @Version 1.0
 */
public enum InvokeSourceEnum {

  MICRO_SERVICE_A("微服务A"),
  MICRO_SERVICE_B("微服务B");

  private String desc;

  InvokeSourceEnum(String desc) {
    this.desc = desc;
  }

  public String getDesc() {
    return desc;
  }

}
