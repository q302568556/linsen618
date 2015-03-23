/*!
 * 文件用途：加载导航条
 * 作者姓名：liuxiaojing
 * 联系方式：302568556@qq.com
 * 制作日期：2014-12-03
 */

/* ==========================================================
 * 加载导航条
 * 参数：加载导航条的容器，所在的页面名称
 * ========================================================= */
function loadNav(navContainer, pageName) {
  var $navTemplate = $(navTemplate());
  var navbarMain = $navTemplate.find("#navbar-main");
  //设置active类
  //登录页面（login）/异常页面（exception）不设置active类
  if("home" == pageName) { //首页
    findActiveTarget(navbarMain, "1");
  }
  if("article" == pageName) { //文章内容
    findActiveTarget(navbarMain, "1");
  }
  if("articleEditor" == pageName) { //文章编辑
    findActiveTarget(navbarMain, "1");
  }
  if("appList" == pageName) { //应用列表
    findActiveTarget(navbarMain, "2");
  }
  if("about" == pageName) { //关于我
    findActiveTarget(navbarMain, "3");
  }
  //判断是否登录，如果已登录，显示退出按钮
  var loginuserCookie = $.cookie("loginuserCookie");
  if(loginuserCookie) {
    var signOut = $navTemplate.find("#signOut");
    signOut.removeClass("hidden");
    signOut.click(
      function() {
        $.removeCookie("loginuserCookie"); //删除cookie
      }
    );
  }
  //加载导航条
  navContainer.append($navTemplate);
}

/* ================================================
 * 遍历导航条，设置active类
 * 导航条结构为：ulParent-ul-li,li,li-ul-li,li,li...
 * 参数：ul标签的父节点，
 *      active项的索引（从1开始，如1.2.3）
 * ============================================= */
function findActiveTarget(ulParent, activeIndex) {
  //定义activeIndex索引的级别，从0开始，递归调用时用，
  // 如1.2.3，1的级别为0，2的级别为1，一个级别调用一次函数
  var level = arguments[2]?arguments[2]:0;
  if(0 == level) { //设置active项前，将之前的active类清除
    ulParent.find(".active").each(
      function() {
        $(this).removeClass("active");
      }
    )
  }
  var activeIndexArray = activeIndex.split(".");
  var ulList = ulParent.children("ul"); //ul标签项的集合
  var liList = new Array(); //所有ul中li标签项的集合
  var liListIndex = 0; //liList的索引，用来一个个添加li标签项
  for(var i=0; i<ulList.length; i++) {
    var liList_i = ulList.eq(i).children("li"); //其中一个ul的li子集
    for(var j=0; j<liList_i.length; j++) {
      liList[liListIndex] = liList_i.eq(j);
      liListIndex ++;
    }
  }
  //根据索引级别取得该级别active项的索引值
  var activeTargetIndex = activeIndexArray[level]-1;
  //校验当前级别的索引是否超出范围
  if(activeTargetIndex < 0 || activeTargetIndex >= liListIndex) {
    console.log("加载导航条时active项的索引出错！！");
  }
  //根据索引值取得active项
  var activeTarget = liList[activeTargetIndex];
  activeTarget.addClass("active");
  if(activeIndexArray.length > level+1) { //进入下一级别的递归
    level++;
    findActiveTarget(activeTarget, activeIndex, level);
  }
}

/* ==========================================
 * 导航条模板
 * ======================================== */
function navTemplate() {
  var template = "";
  template += '<div>                                                                  ';
  template += '  <div class="navbar navbar-default navbar-fixed-top" role="navigation"> ';
  template += '    <div class="container">                                              ';
  template += '      <div class="navbar-header">                                        ';
  template += '        <button class="navbar-toggle" type="button"                      ';
  template += '                data-toggle="collapse" data-target="#navbar-main">       ';
  template += '          <span class="icon-bar"></span>                                 ';
  template += '          <span class="icon-bar"></span>                                 ';
  template += '          <span class="icon-bar"></span>                                 ';
  template += '        </button>                                                        ';
  template += '        <a class="navbar-brand" href="home.html">林森的博客</a>            ';
  template += '      </div>                                                             ';
  template += '      <div class="collapse navbar-collapse" id="navbar-main">            ';
  template += '        <ul class="nav navbar-nav">                                      ';
  template += '          <li><a href="home.html">首页</a></li>                           ';
  template += '          <li><a href="appList.html">应用</a></li>                        ';
  template += '        </ul>                                                            ';
  template += '        <ul class="nav navbar-nav navbar-right">                         ';
  template += '          <li><a href="about.html">关于我</a></li>                        ';
  template += '        </ul>                                                            ';
  template += '      </div>                                                             ';
  template += '    </div>                                                               ';
  template += '  </div>                                                                 ';
  template += '  <div class="text-right hidden" id="signOut">                           ';
  template += '    <a href="login.html">退出</a>                                         ';
  template += '  </div>                                                                 ';
  template += '</div>                                                                   ';
  return template;
}