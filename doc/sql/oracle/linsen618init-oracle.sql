//create tablespaces
create tablespace tbs_linsen618 datafile 'D:\Oracle11gR2\oradata\ORCL\tbs_linsen618.dbf' size 10m autoextend on extent management local uniform segment space management manual;
create tablespace tbs_linsen618_index datafile 'D:\Oracle11gR2\oradata\ORCL\tbs_linsen618_index.dbf' size 10m autoextend on extent management local uniform segment space management manual;

//create user
create user linsen618 identified by LinSen618 default tablespace tbs_linsen618 temporary tablespace temp;
grant connect,resource to linsen618;
grant create view to linsen618;

