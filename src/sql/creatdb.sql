use RecordReport;
DROP TABLE IF EXISTS rr_baseinfo_record;
create TABLE rr_baseinfo_record (
    id int not null auto_increment,
    primary key (id),
    patientid  varchar(20) not null,
    fullname varchar(20),
    sex  varchar(1),
    birthday   date,
    nation   varchar(10),
    create_date    date,
    cardno   varchar(18),
    address   varchar(100),
    marriage   varchar(10),
    height   Double,
    weight   Double,
    bmi   Double,
    phone   varchar(20),
    temperature   float,
    bloodpressure   varchar(10),
    heartrate   int,
    breathe   int,
    symptom   varchar(200),
    symptom_other   varchar(100),
    family_history   varchar(200),
    ai01   varchar(10),
    ai02   varchar(60),
    ai03   date,
    ai04   varchar(60),
    ai11   varchar(10),
    ai12   varchar(60),
    ai13   date,
    ai14   varchar(60),
    p1   varchar(4),
    p1_date   varchar(20),
    p1_one   varchar(4),
    p1_one_date   varchar(20),
    p2   varchar(4),
    p201_diagnose   varchar(60),
    p201_date   varchar(20),
    p201_status   varchar(60),
    p202_diagnose   varchar(60),
    p202_date   varchar(20),
    p202_status   varchar(60),
    p3   varchar(4),
    p301_diagnose   varchar(60),
    p301_date   varchar(20),
    p301_name   varchar(60),
    p302_diagnose   varchar(60),
    p302_date   varchar(20),
    p302_name   varchar(60),
    p4   varchar(4),
    p401_name   varchar(60),
    p401_date   varchar(20),
    p401_usage   varchar(60),
    p402_name   varchar(60),
    p402_date   varchar(20),
    p402_usage   varchar(60),
    p5   varchar(4),
    p5_name   varchar(60),
    h1   varchar(10),
    h1_years   int,
    h1_few   int,
    h2   varchar(10),
    h2_text   varchar(60),
    h3   varchar(10),
    h3_time   varchar(60),
    h4   varchar(4),
    h4_many  varchar(20),
    h5   varchar(20),
    h6   varchar(4),
    h6_date   date,
    h6_days   int,
    others   varchar(60),
    treat   varchar(60),
    nurse_name   varchar(10),
    nurse_fullname varchar(20),
    status    varchar(2)
);

DROP TABLE IF EXISTS rr_followup_record;
create TABLE rr_followup_record (
    id int not null auto_increment,
    primary key (id),
    patientid  varchar(20) not null,
    visit_date  date,
    nurse_name  varchar(10),
    nurse_fullname varchar(20),
    temperature  FLOAT ,
    self_reported  varchar(200),
    publicity  varchar(200),
    create_date date,
    status   varchar(2)
);

DROP TABLE IF EXISTS rr_plan_record;
create TABLE rr_plan_record (
    id int not null auto_increment,
    primary key (id),
    patientid  varchar(20) not null,
    exam_date  date,
    exam_items varchar(200),
    explanation  varchar(500),
    suggestion  varchar(500),
    plan1  varchar(100),
    plan2  varchar(100),
    plan3  varchar(100),
    plan1_date  date,
    plan2_date  date,
    plan3_date  date,
    nurse_name  varchar(10),
    nurse_fullname varchar(20),
    expert_name  varchar(20),
    explant_date  date,
    create_date   date,
    status   varchar(2)
);
DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色表主键',
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
);
INSERT INTO `sys_role` VALUES (1, 'SUPER_ADMIN');
INSERT INTO `sys_role` VALUES (2, 'ADMIN');
INSERT INTO `sys_role` VALUES (3, 'USER');
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `username` varchar(10) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `fullname` varchar(20) NOT NULL COMMENT '姓名',
  `role_id` int(11) DEFAULT NULL COMMENT '与role角色表联系的外键',
  PRIMARY KEY (`id`),
  CONSTRAINT `user_role_on_role_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
);

INSERT INTO `sys_user` VALUES (1, 'muyz', '123','穆英智', 1);
INSERT INTO `sys_user` VALUES (2, 'admin', '666666','管理员', 2);
INSERT INTO `sys_user` VALUES (3, 'zhs', '666666', '张护士',3);
DROP TABLE IF EXISTS sys_permission;

CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限表主键',
  `permission_name` varchar(50) NOT NULL COMMENT '权限名',
  `role_id` int(11) DEFAULT NULL COMMENT '与role角色表联系的外键',
  PRIMARY KEY (`id`),
  CONSTRAINT `permission_role_on_role_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
);
INSERT INTO `sys_permission` VALUES (1, 'user:*', 1);
INSERT INTO `sys_permission` VALUES (2, 'user:*', 2);
INSERT INTO `sys_permission` VALUES (3, 'user:queryAll', 3);