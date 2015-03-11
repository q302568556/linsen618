-- cmd（如有设置安装目录下bin文件夹的环境变量就无需进入bin文件夹执行命令）
-- net start mysql
-- mysql -u root -p
-- drop database if exists linsen618;
create database linsen618;
grant select,insert,update,delete,show view on linsen618.* to linsen618@localhost identified by 'LinSen618';

use linsen618;

create table article
(
  articleid      varchar(32) primary key comment '文章ID',
  articletitle   varchar(60)             comment '文章标题',
  articlecontent text                    comment '文章内容（存html）',
  articledate    datetime                comment '文章日期'
) comment='文章';  

create table loginuser
(
  loginuserid   varchar(32) primary key comment '登录用户ID',
  loginusername text                    comment '登录用户名',
  loginuserpwd  varchar(20)             comment '登录用户密码'
) comment='登录用户';

create or replace view vw_articlelist as
select articleid,articletitle,substr(articlecontent,1,200) articlesummary,
date_format(articledate, '%Y-%m-%d') articledate from article;

--数据初始化：loginuser
