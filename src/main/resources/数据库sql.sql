CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;



CREATE TABLE `webmagic_category`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `category_type` int(11) NULL DEFAULT NULL,
  `category_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gmt_created` datetime(0) NULL DEFAULT NULL,
  `gmt_modifiled` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '抓取的类型' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of webmagic_category
-- ----------------------------
INSERT INTO `webmagic_category` VALUES (1, 1, '小说', '2019-07-07 16:59:44', '2019-07-07 16:59:47');



CREATE TABLE `webmagic_chapter` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `chapter_num` int(11) DEFAULT NULL COMMENT '章节编号',
  `chapter_name` varchar(64) DEFAULT NULL COMMENT '章节名称',
  `chapter_context` varchar(4096) DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modifild` datetime DEFAULT NULL,
  `content_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='抓取章节信息';


CREATE TABLE `webmagic_content`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '抓取内容id',
  `category_type` int(11) NULL DEFAULT NULL COMMENT '抓取内容 类型',
  `content_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `finish_flag` int(11) NULL DEFAULT NULL COMMENT '内容 完结标志',
  `gmt_created` datetime(0) NULL DEFAULT NULL,
  `gmt_modifiled` datetime(0) NULL DEFAULT NULL,
  `url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '抓取url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '抓取信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of webmagic_content
-- ----------------------------
INSERT INTO `webmagic_content` VALUES (1, 1, '剑来', 0, '2019-07-07 17:00:10', '2019-07-07 17:00:12', 'http://www.jianlaixiaoshuo.com/');

CREATE TABLE `webmagic_lastest_update_chapter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content_id` int(11) DEFAULT NULL,
  `chapter_num` int(11) DEFAULT NULL,
  `chapter_name` varchar(64) DEFAULT NULL,
  `lastest_update_time` datetime DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modifiled` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='抓取最新章节信息表';


CREATE TABLE `webmagic_message_check` (
  `message_id` varchar(64) NOT NULL COMMENT 'rocketmq messageId',
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modifiled` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='mq 消息回查表';

CREATE TABLE `webmagic_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) DEFAULT NULL,
  `user_id` varchar(64) DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modifiled` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';


CREATE TABLE `webmagic_user_interest` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) DEFAULT NULL,
  `content_id` int(11) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT NULL,
  `category_type` int(11) DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modifiled` datetime(3) DEFAULT NULL,
  `status` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户关注';