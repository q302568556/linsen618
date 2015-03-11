/*!
 * 文件用途：加载异常信息
 * 作者姓名：liuxiaojing
 * 联系方式：302568556@qq.com
 * 制作日期：2015-1-8
 */

/* ======================================
 * 加载异常信息
 * ==================================== */
$(function() {
  //加载导航条
  loadNav($("#navContainer"), "exception");
  //读取url，根据异常代码加载异常信息
  var exceptionCode = window.location.search.substr(1);
  if("500" == exceptionCode) {
    $("#exception-msg").text("啊哦~~ 系统累了，休息一下吧！~(@^_^@)~");
  }
});