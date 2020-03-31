## 学习Spring Boot


基于Spring Boot和Bootstrap开发的个人博客


## 数据库脚本
```sql
create table user
(
    id           int auto_increment
        primary key not null ,
    account_id   varchar(100) null,
    token        char(36)     null,
    name         varchar(50)  null,
    gmt_create   bigint       null,
    gmt_modified bigint       null
);
```
