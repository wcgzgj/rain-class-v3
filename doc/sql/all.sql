# demo 表
drop table if exists `test`;
create table `test` (
                           `id` bigint not null comment 'id',
                           `name` varchar(255) comment '接口测试',
                           primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='测试表';



# 学生表
drop table if exists `student`;
create table `student` (
                         `id` bigint not null comment 'id',
                         `loginname` varchar(50) comment '登录名',
                         `realname` varchar(50) comment '真实姓名',
                         `password` varchar(255) comment '密码',
                         `phone` varchar(50) comment '学生电话',
                         `parentphone` varchar(50) comment '学生家长电话',
                         `createtime` datetime comment '创建时间',
                         `logintime` datetime comment '最近一次登录时间',
                         primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='学生表';


# 老师表
drop table if exists `teacher`;
create table `teacher` (
                       `id` bigint not null comment 'id',
                       `loginname` varchar(50) comment '登录名',
                       `realname` varchar(50) comment '真实姓名',
                       `password` varchar(255) comment '密码',
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
                           `id` bigint not null comment 'id',
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
                           primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='权限表';



# 成绩表
drop table if exists `score`;
create table `score` (
                         `id` bigint not null comment 'id',
                         `studentid` bigint comment '学生id',
                         `classid` bigint comment '课程id',
                         `scorenum` float comment '成绩大小',
                         primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='成绩表';


# 评论表
drop table if exists `comments`;
create table `comments` (
                         `id` bigint not null comment 'id',
                         `studentid` bigint comment '学生id',
                         `teacherid` bigint comment '教师id',
                         `content` varchar(255) comment '评论内容',
                         primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='评论表';


# 管理员表
drop table if exists `admin`;
create table `admin` (
                           `id` bigint not null comment 'id',
                           `loginname` varchar(50) comment '登录名',
                           `realname` varchar(50) comment '真实姓名',
                           `password` varchar(255) comment '密码',
                           `createtime` datetime comment '创建时间',
                           `logintime` datetime comment '最近一次登录时间',
                           primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='管理员表';

# 下载表
drop table if exists `download`;
create table `download` (
                            `id` bigint not null comment 'id',
                            `name` varchar(255) not null comment '文件名称',
                            `path` bigint not null comment '文件路径',
                            primary key (`id`)
)engine=innodb default charset=utf8mb4 comment='下载表';