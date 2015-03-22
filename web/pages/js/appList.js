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
        var appListData = JSON.parse(data).appList; //应用列表
        var appImgPathData = JSON.parse(data).appImgPath; //应用图片地址
        var applinkMapData = JSON.parse(data).applinkMap; //应用链接列表（与应用列表是一对多的关系）
        var applinkImgPathData = JSON.parse(data).applinkImgPath; //应用链接图片地址
        appListContainer.empty();
        for(var i in appListData) {
          //生成一个应用
          var appData = appListData[i];
          var app = $appTemplate.clone();
          app.find(".appList-app-img").attr("src", appImgPathData + "/" + appData.appimg);
          app.find(".appList-app-title").text(appData.apptitle);
          app.find(".appList-app-summary").text(appData.appsummary);
          //生成应用链接
          var applinkList = applinkMapData[appData.appid];
          for(var j in applinkList) {
            var applink = applinkList[j];
            var applinkHtml = "<a href='" + applink.applinkaddr + "' target='_blank'>&nbsp;" +
                              "<img src='" + applinkImgPathData + "/" + applink.applinkimg +
                              "'" + " height='20px' width='20px'>&nbsp;</a>";
            app.find(".appList-app-applink").append(applinkHtml);
          }
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
  template += '    <div class="col-xs-4 col-sm-3 col-md-2 col-lg-2">          ';
  template += '      <img class="appList-app-img img-thumbnail center-block" width="100px" height="100px"> ';
  template += '    </div>                                                     ';
  template += '    <div class="col-xs-8 col-sm-9 col-md-10 col-lg-10 pdl0">   ';
  template += '      <h3 class="appList-app-title"></h3>                      ';
  template += '      <p class="appList-app-summary"></p>                      ';
  template += '      <div class="appList-app-applink pull-right">             ';
  template += '        <span class="glyphicon glyphicon-hand-right fs18"></span>';
  template += '      </div>                                                   ';
  template += '    </div>                                                     ';
  template += '  </div>                                                       ';
  template += '</article>                                                     ';
  return template;
}