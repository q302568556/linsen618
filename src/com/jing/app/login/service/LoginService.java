package com.jing.app.login.service;

import com.jing.app.common.entity.Loginuser;
import com.jing.app.common.exception.BusinessException;

/**
 * Created by Jing on 2014/12/29.
 */
public interface LoginService {
  //查询用户是否存在
  public Boolean queryLoginuser(Loginuser loginuser) throws BusinessException;
}
