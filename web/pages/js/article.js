/*!
 * 文件用途：加载文章内容
 * 作者姓名：liuxiaojing
 * 联系方式：302568556@qq.com
 * 制作日期：2014-12-5
 */

/* ===================================
 * 加载文章内容
 * ================================ */
$(function() {
  //加载导航条
  loadNav($("#navContainer"), "article");
  //加载文章
  var articleid = window.location.search.substr(1);
  var articleParam = {"articleid":articleid};
  $.get(
    "article_queryArticle.action",
    articleParam,
    function(data) {
      if(data.exception) {
        window.location.assign("exception.html?" + data.exception);
      } else {
        var articleData = JSON.parse(data).article;
        $("title").text(articleData.articletitle);
        $("#article-id").text(articleData.articleid);
        $("#article-title").text(articleData.articletitle);
        $("#article-content").append(articleData.articlecontent);
      }
    }
  );
  //判断是否加载编辑工具条
  var loginuserCookie = $.cookie("loginuserCookie");
  if(loginuserCookie) {
    loadEditToolbar();
  }
});

/* =============================================
 * 加载编辑工具条
 * =========================================== */
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
  //设置bootbox的默认值
  bootbox.setDefaults({
    local: "zh_CN",
    size: "small",
    className: "bootboxFont bootboxPosition"
  });
  //编辑工具条点击事件
  $("#editToolbar").on(
    "toolbarItemClick",
    function(event, item) {
      var articleid = $("#article-id").text();
      var articleTitle = $("#article-title").text();
      var articleParam = {"articleid":articleid};
      switch(item.id) {
        case "editToolbar-option-add": //新增
          window.location.assign("articleEditor.html?add");
          break;
        case "editToolbar-option-update": //修改
          window.location.assign("articleEditor.html?update&" + articleid);
          break;
        case "editToolbar-option-delete": //删除
          bootbox.dialog({
            message: "确认删除文章“" + articleTitle + "”吗？",
            buttons: {
              no: {
                label: "取&nbsp;&nbsp消"
              },
              yes: {
                label: "确&nbsp;&nbsp;定",
                callback: function() {
                  deleteArticle(articleParam);
                }
              }
            }
          });
          break;
      }
    }
  );
}

/* ==============================================
 * 删除文章
 * =========================================== */
function deleteArticle(articleParam) {
  $.get(
    "article_deleteArticle.action",
    articleParam,
    function(data) {
      if(data.exception) {
        window.location.assign("exception.html?" + data.exception);
      } else {
        bootbox.dialog({
          message: "删除成功！",
          buttons: {
            ok: {
              label: "确&nbsp;&nbsp;认",
              callback: function() {
                window.location.assign("home.html");
              }
            }
          }
        });
      }
    }
  );
}