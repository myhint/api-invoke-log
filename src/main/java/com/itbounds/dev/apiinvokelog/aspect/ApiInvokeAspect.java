package com.itbounds.dev.apiinvokelog.aspect;

import com.itbounds.dev.apiinvokelog.utils.jackson.JacksonUtil;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Description 接口调用切面定义
 * @Author blake
 * @Date 2019/9/29 2:37 下午
 * @Version 1.0
 */
@Component
@Aspect
@Slf4j
public class ApiInvokeAspect {

  private ThreadLocal<Map<String, Object>> invokeBeanThreadLocal = new ThreadLocal<>();

  // 切点
  @Pointcut("@annotation(apiInvokeRecord)")
  public void interfaceMethodStatistics(ApiInvokeRecord apiInvokeRecord) {
  }

  // 前置通知
  @Before("interfaceMethodStatistics(apiInvokeRecord)")
  public void doBefore(JoinPoint joinPoint, ApiInvokeRecord apiInvokeRecord) {

    /**
     * 调用方、被调用方、接口URL、处理时间、参数列表
     */
    log.info(" ========== do before ========== caller:{},callee:{}", apiInvokeRecord.caller(),
        apiInvokeRecord.callee());
    log.info(" =========== 参数：{} ===========", Arrays.toString(joinPoint.getArgs()));

    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes sra = (ServletRequestAttributes) ra;
    HttpServletRequest request = Objects.requireNonNull(sra).getRequest();
    log.info(" ======= request uri : {} ====== ", request.getRequestURI());

    Map<String, Object> paramsMap = new ConcurrentHashMap<>();
    paramsMap.put("caller", apiInvokeRecord.caller().toString());
    paramsMap.put("callee", apiInvokeRecord.callee().toString());
    paramsMap.put("interfaceUrl", request.getRequestURI());
    paramsMap.put("solveTime", new Date(System.currentTimeMillis()));
    paramsMap.put("jsonParams", JacksonUtil.toJSon(joinPoint.getArgs()));
    log.info(" ======== joinPoint.getArgs() :{} ======== ", Arrays.toString(joinPoint.getArgs()));
    invokeBeanThreadLocal.set(paramsMap);

    log.info(" ======== invokeBeanThreadLocal:{} ======== ", invokeBeanThreadLocal.get().toString());
  }

  // @Around("interfaceMethodStatistics(interfaceInvokeRecord)")
  public void doAround(ApiInvokeRecord apiInvokeRecord) {
  }

  // 返回通知
  @AfterReturning("interfaceMethodStatistics(apiInvokeRecord)")
  public void doAfterReturning(ApiInvokeRecord apiInvokeRecord) {
    log.info(" ========== do afterReturning ========== ");
    invokeBeanThreadLocal.get().put("isSuccess", "true");
    invokeBeanThreadLocal.get().put("exceptionMsg", "");
    log.info(" ======== invokeBeanThreadLocal:{} ======== ", invokeBeanThreadLocal.get().toString());

    // TODO 数据封装成Bean，然后保存至DataBase
    log.info(" 返回通知处理结束 ");
  }

  // 异常通知
  @AfterThrowing(value = "interfaceMethodStatistics(apiInvokeRecord)", throwing = "ex")
  public void doAfterThrowing(ApiInvokeRecord apiInvokeRecord, Exception ex) {
    log.info(" ========== do afterThrowing ========== exceptionMsg: {} ", ex.getMessage());
    invokeBeanThreadLocal.get().put("isSuccess", "false");
    invokeBeanThreadLocal.get().put("exceptionMsg", ex.getMessage());
    log.info(" ======== invokeBeanThreadLocal:{} ======== ", invokeBeanThreadLocal.get().toString());

    // TODO 数据封装成Bean，然后保存至DataBase
    log.info(" 异常通知处理结束 ");
  }

}
