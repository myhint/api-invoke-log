package com.itbounds.dev.apiinvokelog.utils.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

/**
 * Created by Blake on 2018/8/14
 */
public class JacksonUtil {

  private static ObjectMapper objectMapper;

  static {
    objectMapper = new ObjectMapper();
  }

  /**
   * JSON字符串转换成Object
   */
  public static <T> T toObject(String json, Class<T> clazz) {

    try {
      if (json == null || "".equals(json)) {
        return null;
      }
      return objectMapper.readValue(json, clazz);

    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }


  /**
   * JSON字符串转换成List
   */
  public static <T> T toList(String json, TypeReference<T> valueTypeRef) {

    try {
      if (json == null || "".equals(json)) {
        return null;
      }
      return objectMapper.readValue(json, valueTypeRef);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * JavaBean转换成JSON字符串
   */
  public static String toJSon(Object object) {

    if (objectMapper == null) {
      objectMapper = new ObjectMapper();
    }

    try {
      return objectMapper.writeValueAsString(object);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void main(String[] args) {
    // json 转 list
    String json = "[1,2]";
    List<Long> ids = JacksonUtil.toList(json, new TypeReference<List<Long>>() {
    });

    if (ids != null) {
      System.out.println(ids.size());
      System.out.println(ids);
    }
  }

}

