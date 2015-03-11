package com.jing.app.common.entity;

/**
 * Created by Jing on 2014/12/29.
 */
public class Loginuser {
  private String loginuserid;
  private String loginusername;
  private String loginuserpwd;

  public String getLoginuserid() {
    return loginuserid;
  }

  public void setLoginuserid(String loginuserid) {
    this.loginuserid = loginuserid;
  }

  public String getLoginusername() {
    return loginusername;
  }

  public void setLoginusername(String loginusername) {
    this.loginusername = loginusername;
  }

  public String getLoginuserpwd() {
    return loginuserpwd;
  }

  public void setLoginuserpwd(String loginuserpwd) {
    this.loginuserpwd = loginuserpwd;
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) {
      return true;
    }
    if(o == null || getClass() != o.getClass()) {
      return false;
    }

    Loginuser loginuser = (Loginuser) o;

    if(loginuserid != null ? !loginuserid.equals(loginuser.loginuserid) : loginuser.loginuserid != null) {
      return false;
    }
    if(loginusername != null ? !loginusername.equals(loginuser.loginusername) : loginuser.loginusername != null) {
      return false;
    }
    if(loginuserpwd != null ? !loginuserpwd.equals(loginuser.loginuserpwd) : loginuser.loginuserpwd != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = loginuserid != null ? loginuserid.hashCode() : 0;
    result = 31 * result + (loginusername != null ? loginusername.hashCode() : 0);
    result = 31 * result + (loginuserpwd != null ? loginuserpwd.hashCode() : 0);
    return result;
  }
}
