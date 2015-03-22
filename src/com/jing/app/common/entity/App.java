package com.jing.app.common.entity;

/**
 * Created by Jing on 2015/3/19.
 */
public class App {
  private String appid;
  private String apptitle;
  private String appsummary;
  private String appimg;

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public String getApptitle() {
    return apptitle;
  }

  public void setApptitle(String apptitle) {
    this.apptitle = apptitle;
  }

  public String getAppsummary() {
    return appsummary;
  }

  public void setAppsummary(String appsummary) {
    this.appsummary = appsummary;
  }

  public String getAppimg() {
    return appimg;
  }

  public void setAppimg(String appimg) {
    this.appimg = appimg;
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) {
      return true;
    }
    if(o == null || getClass() != o.getClass()) {
      return false;
    }

    App app = (App) o;

    if(appid != null ? !appid.equals(app.appid) : app.appid != null) {
      return false;
    }
    if(appimg != null ? !appimg.equals(app.appimg) : app.appimg != null) {
      return false;
    }
    if(appsummary != null ? !appsummary.equals(app.appsummary) : app.appsummary != null) {
      return false;
    }
    if(apptitle != null ? !apptitle.equals(app.apptitle) : app.apptitle != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = appid != null ? appid.hashCode() : 0;
    result = 31 * result + (apptitle != null ? apptitle.hashCode() : 0);
    result = 31 * result + (appsummary != null ? appsummary.hashCode() : 0);
    result = 31 * result + (appimg != null ? appimg.hashCode() : 0);
    return result;
  }
}
