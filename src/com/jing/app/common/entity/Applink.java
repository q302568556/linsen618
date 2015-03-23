package com.jing.app.common.entity;

/**
 * Created by Jing on 2015/3/22.
 */
public class Applink {
  private String applinkid;
  private String appid;
  private String applinkaddr;
  private String applinkimg;
  private String applinkodr;
  private String applinkname;

  public String getApplinkid() {
    return applinkid;
  }

  public void setApplinkid(String applinkid) {
    this.applinkid = applinkid;
  }

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public String getApplinkaddr() {
    return applinkaddr;
  }

  public void setApplinkaddr(String applinkaddr) {
    this.applinkaddr = applinkaddr;
  }

  public String getApplinkimg() {
    return applinkimg;
  }

  public void setApplinkimg(String applinkimg) {
    this.applinkimg = applinkimg;
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) {
      return true;
    }
    if(o == null || getClass() != o.getClass()) {
      return false;
    }

    Applink applink = (Applink) o;

    if(appid != null ? !appid.equals(applink.appid) : applink.appid != null) {
      return false;
    }
    if(applinkaddr != null ? !applinkaddr.equals(applink.applinkaddr) : applink.applinkaddr != null) {
      return false;
    }
    if(applinkid != null ? !applinkid.equals(applink.applinkid) : applink.applinkid != null) {
      return false;
    }
    if(applinkimg != null ? !applinkimg.equals(applink.applinkimg) : applink.applinkimg != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = applinkid != null ? applinkid.hashCode() : 0;
    result = 31 * result + (appid != null ? appid.hashCode() : 0);
    result = 31 * result + (applinkaddr != null ? applinkaddr.hashCode() : 0);
    result = 31 * result + (applinkimg != null ? applinkimg.hashCode() : 0);
    return result;
  }

  public String getApplinkodr() {
    return applinkodr;
  }

  public void setApplinkodr(String applinkodr) {
    this.applinkodr = applinkodr;
  }

  public String getApplinkname() {
    return applinkname;
  }

  public void setApplinkname(String applinkname) {
    this.applinkname = applinkname;
  }
}
