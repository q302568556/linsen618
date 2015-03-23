-- cmd（如有设置安装目录下bin文件夹的环境变量就无需进入bin文件夹执行命令）
-- net start mysql
-- mysql -u root -p
-- drop database if exists linsen618;
create database linsen618;
grant select,insert,update,delete,show view on linsen618.* to linsen618@localhost identified by 'LinSen618';

use linsen618;

create table ARTICLE
(
  articleid      VARCHAR(32) primary key comment '文章ID',
  articletitle   VARCHAR(60)             comment '文章标题',
  articlecontent text                    comment '文章内容（存html）',
  articledate    datetime                comment '文章日期'
) comment='文章';  

create table LOGINUSER
(
  loginuserid   VARCHAR(32) primary key comment '登录用户ID',
  loginusername text                    comment '登录用户名',
  loginuserpwd  VARCHAR(20)             comment '登录用户密码'
) comment='登录用户';

create table APP
(
  appid      VARCHAR(32) primary key comment '应用ID',
  apptitle   VARCHAR(20)             comment '应用标题',
  appsummary VARCHAR(200)            comment '应用简介',
  appimg     VARCHAR(20)             comment '应用图片'
) comment='应用';

create table APPLINK
(
  applinkid   VARCHAR(32) primary key comment '应用链接ID',
  appid       VARCHAR(32)             comment '应用ID',
  applinkaddr VARCHAR(50)             comment '应用链接地址',
  applinkimg  VARCHAR(20)             comment '应用链接图片',
  applinkodr  VARCHAR(1)              comment '应用链接顺序',
  applinkname VARCHAR(20)             comment '应用链接名称'
) comment='应用链接(在不同应用市场的详情地址)';

create or replace view vw_articlelist as
select articleid,articletitle,substr(articlecontent,1,200) articlesummary,
date_format(articledate, '%Y-%m-%d') articledate from article;

--数据初始化：loginuser
