/*!
 * 文件用途：应用列表脚本文件
 * 作者姓名：liuxiaojing
 * 联系方式：302568556@qq.com
 * 制作日期：2015-03-19
 */

/* ========================================================
 * 文档就绪事件
 * =======================================================*/
$(function() {
  //加载导航条
  loadNav($("#navContainer"), "appList");
  //加载应用列表
  loadAppList($("#appListContainer"), "appList_queryAppList.action");
});

/* ==========================================================
 * 加载应用列表
 * 参数：(应用列表容器, 查询文章列表请求action)
 * ========================================================= */
function loadAppList(appListContainer, queryAppListAction) {
  $.get(
    queryAppListAction,
    function(data) {
      if(data.exception) {
        window.location.assign("exception.html?" + data.exception);
      } else {
        var $appTemplate = $(appTemplate());
        var appListData = JSON.parse(data).appList;
        var appImgPath = JSON.parse(data).appImgPath;
        appListContainer.empty();
        for(var i in appListData) {
          var appData = appListData[i];
          var app = $appTemplate.clone();
          app.find(".appList-app-img").attr("src", appImgPath + "/" + appData.appimgname);
          app.find(".appList-app-title").text(appData.apptitle);
          app.find(".appList-app-summary").text(appData.appsummary);
          appListContainer.append(app);
        }
      }
    }
  )
}

/* ===================================================
 * 应用列表中单个应用模板
 * ================================================== */
function appTemplate() {
  var template = "";
  template += '<article class="appList-app well">                             ';
  template += '  <div class="row">                                            ';
  template += '    <div class="col-xs-3 col-sm-3 col-md-2 col-lg-2">          ';
  template += '      <img class="appList-app-img img-responsive">             ';
  template += '    </div>                                                     ';
  template += '    <div class="col-xs-9 col-sm-9 col-md-10 col-lg-10">          ';
  template += '      <h4 class="appList-app-title"></h4>                      ';
  template += '      <p class="appList-app-summary"></p>                      ';
  template += '    </div>                                                     ';
  template += '  </div>                                                       ';
  template += '</article>                                                     ';
  return template;
}