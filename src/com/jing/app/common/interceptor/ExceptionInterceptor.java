package com.jing.app.common.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.StrutsStatics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件说明：由于内置的exception拦截器对应的ExceptionMappingInterceptor中没有对ajax请求进行处理，
 * 导致ajax请求出现异常时无法跳转到异常对应的视图资源，也就是说struts自带的声明式异常对ajax请求无效，
 * 所以自定义了此拦截器针对ajax请求的异常进行统一处理。
 * 目前暂不将异常分类处理，而把所有异常都跳转到同一个页面。
 * Created by Jing on 2015/1/7.
 */
public class ExceptionInterceptor extends AbstractInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);

  public String intercept(ActionInvocation actionInvocation) {
    String result = "";
    try {
      result = actionInvocation.invoke();
    } catch(Exception e) {
      //将异常信息记录日志
      logger.error(e.getMessage());
      logger.debug("调试信息：", e);
      //如果是ajax请求将错误信息传到页面，否则跳转到指定页面
      ActionContext actionContext = actionInvocation.getInvocationContext();
      HttpServletRequest httpServletRequest =
          (HttpServletRequest)actionContext.get(StrutsStatics.HTTP_REQUEST);
      if(isAjaxRequest(httpServletRequest)) {
        ValueStack valueStack = actionInvocation.getStack();
        Map<String, Object> returnResult = new HashMap<String, Object>();
        //向根对象加入1个MAP,KEY值与(<paramname="root">returnResult</param></result>)中root的值相同
        valueStack.set("returnResult", returnResult);
        returnResult.put("exception", "500");
        result = "ajaxException";
      }else{
        result = "exception";
      }
    }
    return result;
  }

  private boolean isAjaxRequest(HttpServletRequest httpServletRequest) {
    String header = httpServletRequest.getHeader("X-Requested-With");
    if(header != null && "XMLHttpRequest".equals(header)) {
      return true;
    } else {
      return false;
    }
  }
}
