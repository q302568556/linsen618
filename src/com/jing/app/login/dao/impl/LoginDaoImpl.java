package com.jing.app.login.dao.impl;

import com.jing.app.common.entity.Loginuser;
import com.jing.app.login.dao.LoginDao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by Jing on 2014/12/29.
 */
public class LoginDaoImpl extends HibernateDaoSupport implements LoginDao {

  //查询用户是否存在
  public Boolean queryLoginuser(Loginuser loginuser) {
    String queryLoginuserCountHql = "from Loginuser where loginusername='" +
        loginuser.getLoginusername() +
        "' and loginuserpwd='" + loginuser.getLoginuserpwd() + "'";
    List<Loginuser> loginuserList = getHibernateTemplate().find(queryLoginuserCountHql);
    if(loginuserList.size()>0) {
      return true;
    } else {
      return false;
    }
  }
}
