/*!
 * 文件用途：加载文章列表组件
 * 作者姓名：liuxiaojing
 * 联系方式：302568556@qq.com
 * 制作日期；2014-11-19
 */

/* =======================================
 * 加载文章列表
 * 参数：(文章列表容器, 查询文章列表请求action, 文章的查询条件)
 * ======================================= */
function loadArticleList(articleListContainer, queryActileListAction, articleListParam) {
  $.get(
    queryActileListAction,
    articleListParam,
    function(data) {
      if(data.exception) {
        window.location.assign("exception.html?" + data.exception);
      } else {
        var $articleTemplate = $(articleTemplate());
        var articleListData = JSON.parse(data).articleList;
        articleListContainer.empty();
        for(var i in articleListData) {
          var articleData = articleListData[i];
          var article = $articleTemplate.clone();
          article.find(".articleList-article-id").text(articleData.articleid);
          article.find(".articleList-article-title").text(articleData.articletitle);
          article.find(".articleList-article-summary").append(articleData.articlesummary);
          article.find(".articleList-article-date").text(articleData.articledate);
          article.find(".articleList-article-summary").click(articleTitleSummaryClick);
          article.find(".articleList-article-title").click(articleTitleSummaryClick);
          articleListContainer.append(article);
        }
      }
    }
  );
}

/* ===============================================
 * 文章摘要点击事件
 * ============================================= */
function articleTitleSummaryClick() {
  var articleid = $($(this).parents(".articleList-article")).find(".articleList-article-id").text();
  window.location.assign("article.html?" + articleid);
}

/* ================================================
 * 文章列表中单个文章模板
 * ================================================ */
function articleTemplate() {
  var template = "";
  template += '<article class="articleList-article">                        ';
  template += '  <h3 class="articleList-article-id hidden"></h3>              ';
  template += '  <div class="row">                                          ';
  template += '    <div class="col-sm-0 col-md-1 col-lg-1 pdr2 visible-md visible-lg">                 ';
  template += '      <h3>                                                   ';
  template += '        <a href="#" onclick="return false;">                 ';
  template += '          <span class="glyphicon glyphicon-bookmark"></span> ';
  template += '        </a>                                                 ';
  template += '      </h3>                                                  ';
  template += '    </div>                                                   ';
  template += '    <div class="col-sm-12 col-md-11 col-lg-11 pdl2">              ';
  template += '      <h3 class="articleList-article-title"></h3>     ';
  template += '      <small class="articleList-article-date"></small>           ';
  template += '    </div>                                                   ';
  template += '  </div>                                                     ';
  template += '  <div class="row">                                          ';
  template += '    <div class="col-sm-0 col-md-1 col-lg-1 pdr2">                 ';
  template += '    </div>                                                   ';
  template += '    <div class="col-sm-12 col-md-11 col-lg-11 pdl2">              ';
  template += '      <pre class="articleList-article-summary"></pre>           ';
  template += '    </div>                                                   ';
  template += '  </div>                                                     ';
  template += '</article>                                                   ';
  return template;
}

/* ========================================
 * 日期格式化，该方法目前没用
 * ======================================= */
function formatDate(date, type){
  if(null == date) {
    return;
  }
  var result = "";
  var yyyy = date.substr(0,4);
  var mm = date.substr(5,2);
  var dd = date.substr(8,2);
  mm = mm<10 ? mm.substr(1,1) : mm;
  dd = dd<10 ? dd.substr(1,1) : dd;
  switch (type.toLocaleLowerCase()) {
    case "yyyy":
      result = yyyy + "年";
      break;
    case "mmdd":
      result = mm + "月" + dd + "日";
      break;
    case "yyyymmdd":
      result = yyyy + "年" + mm + "月" + dd + "日";
      break;
    default:
      result = date;
  }
  return result;
}