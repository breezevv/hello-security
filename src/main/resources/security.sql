create schema security collate utf8mb4_general_ci;

-- `security`.authority definition

CREATE TABLE `authority` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名',
  `url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类型为页面时，代表前端路由地址，类型为按钮时，代表后端接口地址',
  `type` int NOT NULL COMMENT '权限类型，页面-1，按钮-2',
  `permission` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限表达式',
  `method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '后端接口访问方式',
  `sort` int NOT NULL COMMENT '排序',
  `parent_id` bigint NOT NULL COMMENT '父级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='权限表';


-- `security`.`role` definition

CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `creator_id` bigint NOT NULL,
  `description` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- `security`.role_authority definition

CREATE TABLE `role_authority` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL,
  `authority_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- `security`.`user` definition

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `nickname` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(75) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_username_uk` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- `security`.user_role definition

CREATE TABLE `user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



INSERT INTO `security`.authority (name,url,`type`,permission,`method`,sort,parent_id) VALUES
	 ('test','/test',2,'btn:test','GET',0,0);
INSERT INTO `security`.`role` (name,creator_id,description,status,create_time,update_time) VALUES
	 ('超级管理员',1,'系统超级管理员的角色','enable','2022-06-11 21:03:26','2022-06-11 21:03:26');
INSERT INTO `security`.role_authority (role_id,authority_id) VALUES
	 (1,1);
INSERT INTO `security`.`user` (username,password,nickname,email) VALUES
	 ('admin','$2a$10$P7bhFo9shI7Sr3biVKB6aOMp.ZlhhU5zVv.D7L1MoAUi.xHJ7ibom',' 嘤嘤嘤','test@test.com'),
	 ('user','$2a$10$VkMpaXsEpGs06IicbFyvyOyBdTL6KtSMujVSPmNzfiI9YryWOCnYm','哈哈哈','user@test.com');
INSERT INTO `security`.user_role (user_id,role_id) VALUES
	 (1,1);
