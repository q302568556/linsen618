package com.jing.app.common.exception;

/**
 * 文件说明：业务逻辑异常，一般在service层抛出
 * Created by Jing on 2015/1/8.
 */
public class BusinessException extends Exception {
  public BusinessException(String msg) {
    super(msg);
  }
}
