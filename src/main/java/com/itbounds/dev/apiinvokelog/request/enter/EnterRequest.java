package com.itbounds.dev.apiinvokelog.request.enter;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 入参
 * @Author blake
 * @Date 2019/9/29 3:33 下午
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class EnterRequest {

  // 功能特性
  private String features;

  // 测试员
  private String tester;

}
