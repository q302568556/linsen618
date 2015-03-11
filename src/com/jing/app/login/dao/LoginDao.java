package com.jing.app.login.dao;

import com.jing.app.common.entity.Loginuser;

/**
 * Created by Jing on 2014/12/29.
 */
public interface LoginDao {
  //查询用户是否存在
  public Boolean queryLoginuser(Loginuser loginuser);
}
