package com.itbounds.dev.apiinvokelog.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description ThreadLocal用法演示
 * @Author blake
 * @Date 2019/9/29 4:54 下午
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ThreadLocalTests {

  private ThreadLocal<String> threadLocal = new ThreadLocal<>();

  @Test
  public void threadLocalTypeString() throws InterruptedException {

    // ##### 1 主线程设置值
    Thread.currentThread().setName("mainThread");
    threadLocal.set("value of mainThread's threadLocal");
    log.info(" ======== 主线程 " + Thread.currentThread().getName() + " 首次获取threadLocal的value值:{}",
        threadLocal.get());

    // ##### 2 子线程设置值
    new Thread(() -> {
      threadLocal.set("value of subThreadNO1's threadLocal");
      log.info(" ======== 子线程 " + Thread.currentThread().getName() + " 获取threadLocal的value值:{}",
          threadLocal.get());

    }, "subThreadNO1").start();

    // ##### 3 主线程再次获取值
    Thread.sleep(5000L); // 主线程休眠5秒
    log.info(" ======== 主线程 " + Thread.currentThread().getName() + " 再次获取threadLocal的value值:{}",
        threadLocal.get());

  }

}
