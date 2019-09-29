package com.itbounds.dev.apiinvokelog.aspect;

import com.itbounds.dev.apiinvokelog.common.enums.InvokeSourceEnum;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiInvokeRecord {

  /**
   * 接口请求源头。例如  InvokeSourceEnum.MICRO_SERVICE_A
   *
   * @see com.itbounds.dev.apiinvokelog.common.enums.InvokeSourceEnum
   */
  InvokeSourceEnum caller();

  /**
   * 接口请求目的地。例如 InvokeSourceEnum.MICRO_SERVICE_B
   *
   * @see com.itbounds.dev.apiinvokelog.common.enums.InvokeSourceEnum
   */
  InvokeSourceEnum callee() default InvokeSourceEnum.MICRO_SERVICE_B;

}
