
create table ARTICLE
(
  articleid      VARCHAR2(32) not null,
  articletitle   VARCHAR2(60),
  articlecontent CLOB,
  articledate    DATE
)
;
comment on table ARTICLE
  is '文章';
comment on column ARTICLE.articleid
  is '文章ID';
comment on column ARTICLE.articletitle
  is '文章标题';
comment on column ARTICLE.articlecontent
  is '文章内容（存html）';
comment on column ARTICLE.articledate
  is '文章日期';
alter table ARTICLE
  add constraint PK_ARTICLE primary key (ARTICLEID)
  using index tablespace tbs_linsen618_index;

create table LOGINUSER
(
  loginuserid   VARCHAR2(32) not null,
  loginusername VARCHAR2(20),
  loginuserpwd  VARCHAR2(20)
)
;
comment on table LOGINUSER
  is '登录用户';
comment on column LOGINUSER.loginuserid
  is '登录用户ID';
comment on column LOGINUSER.loginusername
  is '登录用户名';
comment on column LOGINUSER.loginuserpwd
  is '登录用户密码';
alter table LOGINUSER
  add constraint PK_LOGINUSER primary key (LOGINUSERID)
  using index tablespace tbs_linsen618_index;

create table APP
(
  appid      VARCHAR2(32) not null,
  apptitle   VARCHAR2(20),
  appsummary VARCHAR2(100),
  appimgname VARCHAR2(20)
)
;
comment on table APP
  is '应用';
comment on column APP.appid
  is '应用ID';
comment on column APP.apptitle
  is '应用标题';
comment on column APP.appsummary
  is '应用简介';
comment on column APP.appimgname
  is '应用图片名称';
alter table APP
  add constraint PK_APP primary key (APPID)
  using index tablespace tbs_linsen618_index;

create or replace view vw_articlelist as
select articleid,articletitle,substr(articlecontent,0,200) articlesummary,
to_char(articledate,'yyyy-mm-dd') articledate from article;

--数据初始化：loginuser
