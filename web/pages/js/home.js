/*!
 * 文件用途：首页脚本文件
 * 作者姓名：liuxiaojing
 * 联系方式：302568556@qq.com
 * 制作日期：2014-11-19
 */

/* =======================================
 * 文档就绪事件
 * ======================================= */
$(function() {
  //加载导航条
  loadNav($("#navContainer"), "home");
  //加载文章列表
  loadArticleList($("#articleListContainer"), "articleList_queryArticleList.action");
  $("#calendarContainer").datepicker(
    {format: "yyyy-mm-dd",
      weekStart: 1,
      todayBtn: "linked",
      language: "zh-CN",
      todayHighlight: true}
  ).on(
    "changeDate",
    function(e) {
      var articleListParam = {"selectedDate":e.format(0, "yyyy-mm-dd")};
      loadArticleList(
        $("#articleListContainer"),
        "articleList_queryArticleList.action",
        articleListParam
      );
    }
  );
  //判断是否加载编辑工具条
  var loginuserCookie = $.cookie("loginuserCookie");
  if(loginuserCookie) {
    loadEditToolbar();
  }
});

/* =======================================
 * 加载编辑工具条
 * ==================================== */
function loadEditToolbar() {
  //加载编辑工具条
  $("#editToolbar").removeClass("hidden");
  $("#editToolbar").toolbar({
    content: "#editToolbar-options",
    position: "top",
    hideOnClick: true
  });
  //激活编辑工具条上按钮的工具提示
  $("[data-toggle='tooltip']").tooltip();
  //编辑工具条点击事件
  $("#editToolbar").on(
    "toolbarItemClick",
    function(event, item) {
      switch(item.id) {
        case "editToolbar-option-add": //新增
          window.location.assign("articleEditor.html?add");
          break;
      }
    }
  );
}