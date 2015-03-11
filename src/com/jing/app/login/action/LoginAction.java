package com.jing.app.login.action;

import com.jing.app.common.entity.Loginuser;
import com.jing.app.login.service.LoginService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Jing on 2014/12/27.
 */
public class LoginAction extends ActionSupport {
  //系统的业务逻辑组件
  private LoginService loginService;
  //设置注入业务逻辑组件的setter方法
  public void setLoginService(LoginService loginService) {
    this.loginService = loginService;
  }

  //登录用户
  private Loginuser loginuser;
  //传入登录用户的setter方法
  public void setLoginuser(Loginuser loginuser) {
    this.loginuser = loginuser;
  }
  //获取登录用户的getter方法
  public Loginuser getLoginuser() {
    return loginuser;
  }

  //用户存在标志
  private Boolean isExist;
  //获取用户存在标志的getter方法
  public Boolean getIsExist() {
    return isExist;
  }

  //处理请求：查询用户是否存在
  public String queryLoginuser() throws Exception{
    //调用业务逻辑组件
    isExist = loginService.queryLoginuser(loginuser);
    return SUCCESS;
  }
}
