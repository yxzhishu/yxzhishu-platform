---------------------------------------------------
---------------------------------------------------
-------- !!后续语句需切换数据库后执行!! ----------------
---------------------------------------------------
---------------------------------------------------
-- 只包含系统相关语句
-- 定义规则说明：
-- 1.所有主键使用int8
-- 2.全部字段不允许null
-- 3.字段尽量明确，说明信息用 description
-- 4.物理删除字段：deleted_flag char(1) 
-- 5.状态字段：status char(1) 状态(0正常,1停用)
-- 6.sort_order int2 排序号(数字越小越优先)
-- 7.system_flag char(1) 系统标志 ,不允许页面修改
-- 
-- 菜单限制，请预留1-19\100-199\1000-1999


-- 系统架构
create schema sys;
-- 字典架构
create schema dict;


-- 用户表
drop table if exists sys.user;
create table sys.user(
user_id int8 primary key,
user_name varchar(64) not null default(''),
id_card varchar(18) not null default(''),
phone varchar(11) not null default(''),
birth_date timestamp(6) not null default('0001-01-01'),
description varchar(500) not null default(''),
province_id int8 not null default(-1),
city_id int8 not null default(-1),
town_id int8 not null default(-1),
address varchar(100) not null default('')
);
comment on table sys.user is '用户表';
comment on column sys.user.user_id is '用户ID';
comment on column sys.user.user_name is '用户姓名';
comment on column sys.user.id_card is '身份证号';
comment on column sys.user.phone is '手机号';
comment on column sys.user.birth_date is '出生日期';
comment on column sys.user.description is '描述';
comment on column sys.user.province_id is '省份id';
comment on column sys.user.city_id is '城市ID';
comment on column sys.user.town_id is '城镇ID';
comment on column sys.user.address is '具体地址';


-- 创建账户表
drop table if exists sys.account;
create table sys.account(
account_id int8 primary key,
account_code varchar(20) unique not null,
password char(32) not null,
salt char(16) not null,
nickname varchar(30) not null default('未命名'),
status char(1) not null default('0'),
system_flag char(1) not null default('0'),
phone varchar(11) not null default(''),
email varchar(50) not null default(''),
description varchar(300) not null default(''),
avatar varchar(500) not null default(''),
user_id int8 not null default(-1),
creater_id int8 not null default(-1),
create_time timestamp not null default(now()),
last_login_time timestamp not null default CURRENT_TIMESTAMP,
last_login_ip varchar(15) not null default('0.0.0.0'),
updater_id int8 not null default(-1),
update_time timestamp not null default(now())
);
comment on table sys.account is '账户表';
comment on column sys.account.account_id is '账户ID';
comment on column sys.account.account_code is '账号(唯一,不能包含汉字和符号，首位不能为数字)';
comment on column sys.account.password is '密码(初始密码123456)';
comment on column sys.account.salt is '盐值';
comment on column sys.account.nickname is '昵称';
comment on column sys.account.status is '账户状态(0正常、1禁用)';
comment on column sys.account.system_flag is '系统标识';
comment on column sys.account.phone is '手机号';
comment on column sys.account.email is '邮箱';
comment on column sys.account.description is '说明';
comment on column sys.account.avatar is '头像';
comment on column sys.account.user_id is '用户信息ID';
comment on column sys.account.creater_id is '创建者账号ID';
comment on column sys.account.create_time is '创建时间';
comment on column sys.account.last_login_time is '最后登陆时间';
comment on column sys.account.last_login_ip is '最后登陆IP';
comment on column sys.account.updater_id is '修改者账号ID';
comment on column sys.account.update_time is '修改时间';

insert into sys.account values(1,'root','a0888a8a03f2c9c9638bbb08651d69f0','9dea443201196b8f','root','0','1','','','最高管理者权限，不允许修改','',-1,-1,now(),now(),'0.0.0.0',-1,now());


-- 创建角色表
drop table if exists sys.role;
create table sys.role(
role_id int8 primary key,
role_name varchar(20) not null,
role_key varchar(30) unique ,
role_type char(1) not null default('1'),
sort_order int2 not null default(999),
status char(1) default('0'),
system_flag char(1) not null default('0'),
description varchar(300) default(''),
creater_id int8 not null  default(-1),
create_time timestamp not null default(now()),
updater_id int8 not null default(-1),
update_time timestamp not null default(now())
);
comment on table sys.role is '角色表';
comment on column sys.role.role_id is '角色ID';
comment on column sys.role.role_name is '角色名称';
comment on column sys.role.role_key is '角色权限字符';
comment on column sys.role.role_type is '角色类型(0:管理,1:普通用户)';
comment on column sys.role.sort_order is '排序号(越小越优先)';
comment on column sys.role.status is '状态(0正常、1禁用)';
comment on column sys.role.system_flag is '系统标识';
comment on column sys.role.description is '备注';
comment on column sys.role.creater_id is '创建者ID';
comment on column sys.role.create_time is '创建时间';
comment on column sys.role.updater_id is '修改者账号ID';
comment on column sys.role.update_time is '修改时间';


-- 初始化角色
insert into sys.role values(0,'最高权限者','root','0',1,'0','1','',-1,now(),-1,now());
insert into sys.role values(1,'管理员','admin','0',2,'0','1','',-1,now(),-1,now());
insert into sys.role values(2,'普通用户','user','1',3,'0','1','',-1,now(),-1,now());
insert into sys.role values(9,'游客','tourist','1',9,'0','1','',-1,now(),-1,now());


-- 账号角色对应表
drop table if exists sys.account_vs_role;
create table sys.account_vs_role(
account_id int8,
role_id int8,
primary key(account_id,role_id)
);
comment on table sys.role_vs_menu is '账号角色对应表';
comment on column sys.role_vs_menu.role_id is '账号ID';
comment on column sys.role_vs_menu.menu_id is '角色ID';

insert into sys.account_vs_role values(1,0);


-- 创建菜单表
drop table if exists sys.menu;
create table sys.menu(
menu_id int8 primary key,
menu_name varchar(50) unique not null,
menu_type char(1) default('C') not null,
menu_path varchar(100) not null,
component varchar(100) not null,
parent_id int8 default(0),
system_flag char(1) not null default('0'),
title varchar(20) not null,
icon varchar(50) not null,
hidden char(1) default('1'),
keep_alive char(1) default('0'),
sort_order int2 not null default(999),
status char(1) default('0'),
description varchar(200) default('')
);
comment on table sys.menu is '菜单表';
comment on column sys.menu.menu_id is '菜单ID';
comment on column sys.menu.menu_name is '菜单名称(权限字符)';
comment on column sys.menu.menu_type is '菜单类型（M目录 C菜单 F权限）';
comment on column sys.menu.menu_path is '路径及外链';
comment on column sys.menu.component is '组件';
comment on column sys.menu.parent_id is '父菜单ID';
comment on column sys.menu.system_flag is '系统标识';
comment on column sys.menu.title is '菜单标题';
comment on column sys.menu.icon is '图标';
comment on column sys.menu.hidden is '是否隐藏菜单';
comment on column sys.menu.keep_alive is '是否缓存';
comment on column sys.menu.sort_order is '排序号(越小越优先)';
comment on column sys.menu.status is '状态';
comment on column sys.menu.description is '备注';

--初始化菜单
insert into sys.menu values(1,'home','C','/home','home/index.vue',-1,'1','主页','HomeOutlined','0','0',1,'0','');
insert into sys.menu values(2,'sys','M','/sys','',-1,'1','设置','SettingOutlined','0','0',97,'0','');
insert into sys.menu values(3,'log','M','/log','',-1,'1','日志','ExceptionOutlined','0','0',98,'0','');
insert into sys.menu values(4,'test','M','/test','',-1,'1','测试','BugOutlined','0','0',99,'0','');

insert into sys.menu values(101,'test.ui','C','/testUi','test/ui.vue',4,'1','UI测试','','0','0',1,'0','');
insert into sys.menu values(102,'sys.account','C','/sysAccount','sys/account.vue',2,'1','账号管理','','0','1',1,'0','');
insert into sys.menu values(103,'sys.role','C','/sysRole','sys/role.vue',2,'1','角色管理','','0','1',2,'0','');
insert into sys.menu values(104,'sys.menu','C','/sysMenu','sys/menu.vue',2,'1','菜单管理','','0','1',3,'0','');
insert into sys.menu values(105,'log.updateLog','C','/logUpdateLog','log/updateLog.vue',3,'1','更新日志','','0','1',1,'0','');
insert into sys.menu values(106,'log.operationLog','C','/logOperationLog','log/operationLog.vue',3,'1','操作日志','','0','1',2,'0','');
insert into sys.menu values(107,'log.problemLog','C','/problemLog','log/problemLog.vue',3,'1','问题单','','0','1',3,'0','');

insert into sys.menu values(1001,'sys.account.list','F','/','',102,'1','账号查看','','0','0',1,'0','');
insert into sys.menu values(1002,'sys.account.add','F','/','',102,'1','账号新增','','0','0',2,'0','');
insert into sys.menu values(1003,'sys.account.edit','F','/','',102,'1','账号修改','','0','0',3,'0','');
insert into sys.menu values(1004,'sys.role.list','F','/','',103,'1','角色查看','','0','0',1,'0','');
insert into sys.menu values(1005,'sys.role.add','F','/','',103,'1','角色新增','','0','0',2,'0','');
insert into sys.menu values(1006,'sys.role.edit','F','/','',103,'1','角色修改','','0','0',3,'0','');
insert into sys.menu values(1007,'sys.role.delete','F','/','',103,'1','角色删除','','0','0',4,'0','');
insert into sys.menu values(1008,'log.problemLog.list','F','/','',107,'1','问题查看','','0','0',1,'0','');
insert into sys.menu values(1009,'log.problemLog.edit','F','/','',107,'1','问题编辑','','0','0',2,'0','');
insert into sys.menu values(1010,'log.problemLog.delete','F','/','',107,'1','问题删除','','0','0',3,'0','');



--创建角色对应权限表
drop table if exists sys.role_vs_menu;
create table sys.role_vs_menu(
role_id int8,
menu_id int8,
primary key(role_id,menu_id)
);
comment on table sys.role_vs_menu is '角色对应权限表';
comment on column sys.role_vs_menu.role_id is '角色ID';
comment on column sys.role_vs_menu.menu_id is '菜单ID';


--初始化角色权限对应
insert into sys.role_vs_menu values(9,1),(9,105);
insert into sys.role_vs_menu values(2,1),(2,3),(2,105),(2,106),(2,107),(2,1007),(2,1008);
insert into sys.role_vs_menu values(1,1),(1,3),(1,102),(1,104),(1,105),(1,106),(1,107),(1,10002),(1,10003),(1,10004),(1,10007),(1,10008),(1,10009);

-- 创建通用字典表
drop table if exists dict.comm_dict;
create table dict.comm_dict(
dict_id int8,
dict_type varchar(30),
dict_code varchar(80),
dict_label varchar(200),
creater_id int8 not null  default(-1),
create_time timestamp not null default(now()),
updater_id int8 not null default(-1),
update_time timestamp not null default(now())
);
comment on table dict.comm_dict is '通用字典表';
comment on column dict.comm_dict.dict_id is '字典主键';
comment on column dict.comm_dict.dict_type is '字典类型';
comment on column dict.comm_dict.dict_code is '字典编码';
comment on column dict.comm_dict.dict_label is '字典名称';
comment on column dict.comm_dict.creater_id is '创建者ID';
comment on column dict.comm_dict.create_time is '创建时间';
comment on column dict.comm_dict.updater_id is '修改者账号ID';
comment on column dict.comm_dict.update_time is '修改时间';

insert into dict.comm_dict values
(1,'sex','1','男',-1,now(),-1,now()),
(2,'sex','2','女',-1,now(),-1,now()),
(3,'sex','0','未知',-1,now(),-1,now());


drop table if exists dict.comm_dict_type;
create table dict.comm_dict_type(
dict_type_id int8,
dict_type varchar(30),
dict_type_label varchar(80),
creater_id int8 not null  default(-1),
create_time timestamp not null default(now()),
updater_id int8 not null default(-1),
update_time timestamp not null default(now())
);
comment on table dict.comm_dict_type is '通用字典类型表';
comment on column dict.comm_dict_type.dict_type_id is '通用字典类型表主键';
comment on column dict.comm_dict_type.dict_type is '字典类型';
comment on column dict.comm_dict_type.dict_type_label is '字段类型说明';
comment on column dict.comm_dict_type.creater_id is '创建者ID';
comment on column dict.comm_dict_type.create_time is '创建时间';
comment on column dict.comm_dict_type.updater_id is '修改者账号ID';
comment on column dict.comm_dict_type.update_time is '修改时间';

insert into dict.comm_dict_type values(1,'sex','性别',-1,now(),-1,now());



drop table if exists sys.log_problem;
create table sys.log_problem(
problem_id int8,
problem_title varchar(50),
problem_value varchar(300),
answer_value varchar(300),
creater_id int8 not null  default(-1),
create_time timestamp not null default(now()),
answer_id int8 not null default(-1),
answer_time timestamp not null default(now())
);
comment on table sys.log_problem is '问题记录';
comment on column sys.log_problem.problem_id is '问题记录ID';
comment on column sys.log_problem.problem_title is '问题标题';
comment on column sys.log_problem.problem_value is '问题详情';
comment on column sys.log_problem.answer_value is '解答内容';
comment on column sys.log_problem.creater_id is '创建者ID';
comment on column sys.log_problem.create_time is '创建时间';
comment on column sys.log_problem.answer_id is '回答者ID';
comment on column sys.log_problem.answer_time is '回答时间';