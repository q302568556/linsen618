/*!
 * 文件用途：加载文章编辑框
 * 作者姓名：liuxiaojing
 * 联系方式：302568556@qq.com
 * 制作日期：2014-12-8
 */

/* ==================================
 * 在文档就绪之前判断是否有cookie
 * =============================== */
var loginuserCookie = $.cookie("loginuserCookie");
if(!loginuserCookie) {
  window.location.assign("login.html");
}

/* ==================================
 * 加载文章编辑框
 * =============================== */
$(function() {
  //加载导航条
  loadNav($("#navContainer"), "articleEditor");
  //加载文章内容编辑框
  $("#articleEditor-contentContainer").redactor({
    lang: "zh_cn",
    buttonSource: true, //显示源代码按钮
    imageUpload: "article_saveArticleImg.action", //图片上传请求url
    imageManagerJson: "img/redactorchoose/images.json", //默认供选择的图片，imagemanager插件支持
    plugins: ["imagemanager"]
  });
  //读取url：?add-新增 ?update&articleid-修改
  var urlParams = window.location.search.substr(1).split("&");
  var operate = urlParams[0];
  //如果是修改，将文章加载到编辑器中
  if("update" == operate) {
    var articleid = urlParams[1];
    var articleParam = {"articleid":articleid};
    $.get(
      "article_queryArticle.action",
      articleParam,
      function(data) {
        if(data.exception) {
          window.location.assign("exception.html?" + data.exception);
        } else {
          var articleData = JSON.parse(data).article;
          $("#articleEditor-id").text(articleData.articleid);
          $("#articleEditor-title").val(articleData.articletitle);
          $("#articleEditor-contentContainer").redactor("code.set", articleData.articlecontent);
        }
      }
    );
  }
});

/* =======================================
 * 保存文章
 * ======================================*/
function saveArticle() {
  var articleTitleValue = $("#articleEditor-title").val(); //文章标题
  var articleContentCode = $("#articleEditor-contentContainer").redactor("code.get"); //文章html
  //去除html标签之间的空格，防止用pre标签显示时会出现多余的空格
  var articleContentValue = $("#articleEditor-contentContainer").redactor("clean.removeSpaces", articleContentCode);
  //去除首尾的空格，解决redactor.js中removeSpaces给首尾添加空格导致换行的问题
  articleContentValue = articleContentValue.replace(/^[\s\n]*/g, '');
  articleContentValue = articleContentValue.replace(/[\s\n]*$/g, '');
  //读取url：?add-新增，?update&articleid-更新
  var urlParams = window.location.search.substr(1).split("&");
  var operate = urlParams[0];
  var articleAction = "";
  var articleParam = null;
  //新增
  if("add" == operate) {
    articleAction = "article_addArticle.action";
    articleParam = {"article.articletitle":articleTitleValue,
                    "article.articlecontent":articleContentValue};
  }
  //修改
  if("update" == operate) {
    articleAction = "article_updateArticle.action";
    var articleIdValue = $("#articleEditor-id").text(); //文章id
    articleParam = {"article.articleid":articleIdValue,
                    "article.articletitle":articleTitleValue,
                    "article.articlecontent":articleContentValue};
  }
  $.post(
    articleAction,
    articleParam,
    function(data) {
      if(data.exception) {
        window.location.assign("exception.html?" + data.exception);
      } else {
        bootbox.dialog({
          local: "zh_CN",
          size: "small",
          className: "bootboxFont bootboxPosition",
          message: "保存成功！",
          buttons: {
            ok: {
              label: "确&nbsp;&nbsp;定",
              callback: function() {
                var articleidData = JSON.parse(data).article.articleid;
                window.location.assign("article.html?" + articleidData);
              }
            }
          }
        });
      }
    }
  );
}