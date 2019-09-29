package com.itbounds.dev.apiinvokelog.controller;

import com.itbounds.dev.apiinvokelog.api.results.ApiResult;
import com.itbounds.dev.apiinvokelog.aspect.ApiInvokeRecord;
import com.itbounds.dev.apiinvokelog.common.enums.InvokeSourceEnum;
import com.itbounds.dev.apiinvokelog.request.enter.EnterRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 记录接口调用信息Controller
 * @Author blake
 * @Date 2019/9/29 2:45 下午
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/invoke")
public class ApiInvokeLogController {

  @PostMapping("/success")
  @ApiInvokeRecord(caller = InvokeSourceEnum.MICRO_SERVICE_A)
  public ApiResult success(@RequestBody EnterRequest request) {

    return new ApiResult("success");
  }

  @PostMapping("/interrupt")
  @ApiInvokeRecord(caller = InvokeSourceEnum.MICRO_SERVICE_A)
  public Object interrupt(@RequestBody EnterRequest request)
      throws Exception {

    throw new Exception("业务异常");
  }

}
