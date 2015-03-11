package com.jing.app.login.service.impl;

import com.jing.app.common.entity.Loginuser;
import com.jing.app.common.exception.BusinessException;
import com.jing.app.login.dao.LoginDao;
import com.jing.app.login.service.LoginService;

/**
 * Created by Jing on 2014/12/29.
 */
public class LoginServiceImpl implements LoginService {
  //DAO组件
  private LoginDao loginDao;
  //依赖注入DAO组件所需的setter方法
  public void setLoginDao(LoginDao loginDao) {
    this.loginDao = loginDao;
  }

  //查询用户是否存在
  public Boolean queryLoginuser(Loginuser loginuser) throws BusinessException {
    //校验用户名和密码不能为空
    String loginusername = loginuser.getLoginusername();
    String loginuserpwd = loginuser.getLoginuserpwd();
    if(null == loginusername || "".equals(loginusername)) {
      throw new BusinessException("用户名不能为空！");
    }
    if(null == loginuserpwd || "".equals(loginuserpwd)) {
      throw new BusinessException("密码不能为空！");
    }
    //调用Dao组件的方法来完成业务逻辑
    boolean isExist = loginDao.queryLoginuser(loginuser);
    return isExist;
  }
}
