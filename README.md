# rain-class-v3

雨选课，自由的选课系统

![image-20210622155523358](https://gitee.com/faro/images/raw/master/img/20210622155523.png)

## 在线访问

项目部署在阿里云上

可以使用下面链接去访问：

http://47.117.129.89/



**提供一下学生账号和老师账号供功能展示使用：**

**学生：**

> 账号：student
>
> 密码：123

**老师：**

> 账号：dio
>
> 密码：123



## 项目介绍

本项目，是使用 SpringBoot + antd 搭建的，前后端分离选课系统

实现了学生选课、成绩查询、教师成绩修改、课程编辑、课程新增等功能

登录信息，使用 Redis 存储

实现课程图片的上传功能



**登录：**

![image-20210622155735523](https://gitee.com/faro/images/raw/master/img/20210622155735.png)

**首页：**

![image-20210622155751602](https://gitee.com/faro/images/raw/master/img/20210622155751.png)

**课程信息：**

![image-20210622155801737](https://gitee.com/faro/images/raw/master/img/20210622155801.png)

**课程管理：**

![image-20210622155810877](https://gitee.com/faro/images/raw/master/img/20210622155810.png)

**学生选课：**

![image-20210622155823875](https://gitee.com/faro/images/raw/master/img/20210622155823.png)



## 项目运行

### 数据库

**数据表的 sql 如下：**

```sql
# test 表
# drop table if exists `test`;
# create table `test` (
#                            `id` bigint not null auto_increment comment 'id',
#                            `name` varchar(255) comment '接口测试',
#                            primary key (`id`)
# ) engine=innodb default charset=utf8mb4 comment='测试表';



# 学生表
drop table if exists `student`;
create table `student` (
                         `id` bigint not null auto_increment comment 'id',
                         `loginname` varchar(50) comment '登录名',
                         `realname` varchar(50) comment '真实姓名',
                         `password` varchar(255) comment '密码',
                         `email` varchar(255) comment '邮箱',
                         `createtime` datetime comment '创建时间',
                         `logintime` datetime comment '最近一次登录时间',
                         primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='学生表';


# 老师表
drop table if exists `teacher`;
create table `teacher` (
                       `id` bigint not null auto_increment comment 'id',
                       `loginname` varchar(50) comment '登录名',
                       `realname` varchar(50) comment '真实姓名',
                       `password` varchar(255) comment '密码',
                       `email` varchar(255) comment '邮箱',
                       `createtime` datetime comment '创建时间',
                       `logintime` datetime comment '最近一次登录时间',
                       primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='老师表';



# # 学生老师表
# drop table if exists `stusta`;
# create table `stusta` (
#                        `id` bigint not null comment 'id',
#                        `stuid` bigint comment 'stuid',
#                        `staid` bigint comment 'staid',
#                        primary key (`id`)
# ) engine=innodb default charset=utf8mb4 comment='学生老师表';


# # 权限表
# drop table if exists `quality`;
# create table `quality` (
#                        `id` bigint not null comment 'id',
#                        `role` varchar(50) comment '角色名称',
#                        `menu` varchar(255) comment '菜单名称',
#                        `url` varchar(255) comment '权限url',
#                        `parentid` bigint comment '父类菜单id',
#                        primary key (`id`)
# ) engine=innodb default charset=utf8mb4 comment='权限表';



# 课程表
drop table if exists `class`;
create table `class` (
                           `id` bigint not null  auto_increment comment 'id',
                           `classname` varchar(50) comment '课程名称',
                           `teacherid` bigint comment '教师id',
                           `starttime` varchar(255)  comment '上课时间',
                           `code` varchar(50)  comment '课程编号',
                           `place` varchar(50)  comment '上课地点',
                           `notice` varchar(255) comment '注意事项',
                           `type` varchar(50) comment '课程类型',
                           `credit` int comment '学分',
                           `desc` varchar(255) comment '课程介绍',
                           `createtime` datetime comment '创建时间',
                           `path` varchar(255) comment '封面图片名称',
                           primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='权限表';



# 成绩表
drop table if exists `score`;
create table `score` (
                         `id` bigint not null  auto_increment comment 'id',
                         `studentid` bigint comment '学生id',
                         `classid` bigint comment '课程id',
                         `scorenum` float comment '成绩大小',
                         `createtime` datetime comment '创建时间',
                         primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='成绩表';


# 评论表
drop table if exists `comments`;
create table `comments` (
                         `id` bigint not null auto_increment comment 'id',
                         `userid` bigint comment '评论者id',
                         `usertype` varchar(50) comment '评论者身份',
                         `content` varchar(255) comment '评论内容',
                         `createtime` datetime comment '创建时间',
                         primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='评论表';


# 管理员表
drop table if exists `admin`;
create table `admin` (
                           `id` bigint not null auto_increment comment 'id',
                           `loginname` varchar(50) comment '登录名',
                           `realname` varchar(50) comment '真实姓名',
                           `password` varchar(255) comment '密码',
                           `email` varchar(255) comment '邮箱',
                           `createtime` datetime comment '创建时间',
                           `logintime` datetime comment '最近一次登录时间',
                           primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='管理员表';

# 下载表
drop table if exists `download`;
create table `download` (
                            `id` bigint not null auto_increment comment 'id',
                            `name` varchar(255) comment '文件名称',
                            `path` varchar(255) comment '文件路径',
                            `count` int comment '下载次数',
                            primary key (`id`)
)engine=innodb default charset=utf8mb4 comment='下载表';
```



### yml 配置

**先将 `application-prod.yml` 改为 `application.yml`：**

![image-20210622160018247](https://gitee.com/faro/images/raw/master/img/20210622160018.png)



**再将数据库，Redis 等信息，填上自己的：**

![image-20210622160101401](https://gitee.com/faro/images/raw/master/img/20210622160101.png)



### 图片存储

**.yml 配置的最后一行，要修改图片的存储和访问路径：**

`file-path`是图片在服务器上的存储路径

`web-path` 是图片在网络上的访问路径

![image-20210622160308864](https://gitee.com/faro/images/raw/master/img/20210622160308.png)



**`web-path`最后一定要带上 `/disPic/`，因为资源拦截器对访问的拦截，就是判断有没有带上 `/disPic`:**

```java
@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        LOG.info("外部尝试访问磁盘图片");
        LOG.info("拦截器中设置的路径为:{}","file:"+FILE_PATH);
        registry.addResourceHandler("/disPic/**").addResourceLocations("file:"+FILE_PATH+"/");

        // http://127.0.0.1:9000/disPic/1.jpg
    }
```

