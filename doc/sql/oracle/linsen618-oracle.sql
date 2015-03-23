
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
  appsummary VARCHAR2(200),
  appimg VARCHAR2(20)
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
comment on column APP.appimg
  is '应用图片';
alter table APP
  add constraint PK_APP primary key (APPID)
  using index tablespace tbs_linsen618_index;

create table APPLINK
(
  applinkid   VARCHAR2(32) not null,
  appid       VARCHAR2(32),
  applinkaddr VARCHAR2(50),
  applinkimg  VARCHAR2(20),
  applinkodr  VARCHAR2(1),
  applinkname VARCHAR2(20)
)
;
comment on table APPLINK
  is '应用链接(在不同应用市场的详情地址)';
comment on column APPLINK.applinkid
  is '应用链接id';
comment on column APPLINK.appid
  is '应用id';
comment on column APPLINK.applinkaddr
  is '应用链接地址';
comment on column APPLINK.applinkimg
  is '应用链接图片';
comment on column APPLINK.applinkodr
  is '应用链接顺序';
comment on column APPLINK.applinkname
  is '应用链接名称';
alter table APPLINK
  add constraint PK_APPLINK primary key (APPLINKID)
  using index tablespace tbs_linsen618_index;

create or replace view vw_articlelist as
select articleid,articletitle,substr(articlecontent,0,200) articlesummary,
to_char(articledate,'yyyy-mm-dd') articledate from article;

--数据初始化：loginuser
