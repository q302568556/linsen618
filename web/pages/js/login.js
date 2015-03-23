/*!
 * 文件用途：登录页面脚本文件
 * 作者姓名：liuxiaojing
 * 联系方式：302568556@qq.com
 * 制作日期：2014-12-27
 */

/* ==================================
 * 文档就绪事件
 * ================================= */
$(function() {
  //加载导航条
  loadNav($("#navContainer"), "login");
  //回车键等效于登录按钮
  $(window).keydown(
    function(event) {
      if(event.keyCode == 13) {
        $("#login-signIn").click();
      }
    }
  );
  //登录按钮点击事件
  $("#login-signIn").click(
    function() {
      //重置表单错误信息
      $("#login-errorMsg").removeClass("hidden");
      //表单校验
      var validateResult = loginValidate();
      if(!validateResult) {
        return;
      }
      //表单取值
      var loginValues = $("#login").serializeArray(); //JSON对象
      //将JSON对象转为JSON字符串
      var loginParam = {};
      $.each(
        loginValues,
        function(i, loginValue) {
          loginParam["loginuser." + loginValue.name] = loginValue.value;
        }
      );
      $.post(
        "login_queryLoginuser.action",
        loginParam,
        function(data) {
          if(data.exception) {
            window.location.assign("exception.html?" + data.exception);
          } else {
            var loginuserData = JSON.parse(data).loginuser;
            var isExistData = JSON.parse(data).isExist;
            if(isExistData) {
              var loginusernameData = loginuserData.loginusername;
              var loginuserpwdData = loginuserData.loginuserpwd;
              var loginuserCookie = md5(loginusernameData, loginuserpwdData);
              $.cookie("loginuserCookie", loginuserCookie);
              window.location.assign("home.html");
            } else {
              $("#login-errorMsg").text("您输入的用户名或密码有误，请重新输入！");
              $("#login-errorMsg").removeClass("hidden");
            }
          }
        }
      );
    }
  );
});

/* =============================================
 * 表单校验
 * =========================================== */
function loginValidate() {
  var loginusername = $("#login-username").val();
  var loginuserpwd = $("#login-userpwd").val();
  if(null == loginusername || "" == loginusername) {
    $("#login-errorMsg").text("请填写用户名！");
    $("#login-errorMsg").removeClass("hidden");
    return false;
  }
  if(null == loginuserpwd || "" == loginuserpwd) {
    $("#login-errorMsg").text("请填写密码！");
    $("#login-errorMsg").removeClass("hidden");
    return false;
  }
  return true;
}