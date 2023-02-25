CREATE TABLE `admin`  (
  `id` varchar(255)  ,
  `name` varchar(255)  ,
  `password` varchar(255)  ,
  PRIMARY KEY (`id`) USING BTREE
) ;

INSERT INTO `admin` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e');

CREATE TABLE `course`  (
  `id` varchar(255),
  `course_name` varchar(255),
  `class_hours` int,  
  `create_time` datetime,
  `people_num` int   ,
  `teacher_id` varchar(255),
  PRIMARY KEY (`id`) USING BTREE
) ;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('908105810897008', '网页设计', 32, '2022-09-09 00:00:00', 98, '908093220151940');
INSERT INTO `course` VALUES ('908120239734471', '高等数学', 64, '2022-09-06 00:00:00', 120, '908120040334668');

-- ----------------------------
-- Table structure for paper
-- ----------------------------
CREATE TABLE `paper`  (
  `id` varchar(255)  ,
  `paper_name` varchar(255)    ,
  `pub_time` datetime   ,
  `pub_journal` varchar(255)    ,
  `journal_level` varchar(255)    ,
  `index_situation` varchar(255)    ,
  `teacher_id` varchar(255)  ,
  `review_time` datetime   ,
  `review_shar` varchar(255)    ,
  `flog` int,
  PRIMARY KEY (`id`) USING BTREE
) ;

-- ----------------------------
-- Records of paper
-- ----------------------------
INSERT INTO `paper` VALUES ('908103700774756', '基于大数据的情感分析平台', '2020-09-20 00:00:00', '自然', '顶会', '好', '908093220151940', '2022-09-08 00:00:00', '好', 1);

-- ----------------------------
-- Table structure for project
-- ----------------------------
CREATE TABLE `project`  (
  `id` varchar(255)  ,
  `project_name` varchar(255)    ,
  `category` varchar(255)    , 
   `awards` varchar(255)    ,
  `create_time` datetime   ,
  `end_time` datetime   ,
  `review_time` datetime   ,
  `flog` int   ,
  `review_shar` varchar(255)   ,
  `teacher_id` varchar(255) ,
  PRIMARY KEY (`id`) USING BTREE
) ;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('908105042509058', '教师档案管理系统', '数据库项目', '暂无', '2022-09-07 00:00:00', '2022-09-09 00:00:00', '1949-10-01 00:00:00', 1, NULL, '908093220151940');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
CREATE TABLE `teacher`  (
  `id` varchar(255)  ,
  `name` varchar(255)  ,
  `username` varchar(255)    ,
  `password` varchar(255)    ,  `gender` char(1),      `work_time` datetime ,
  `major` varchar(255)    ,
  `education` varchar(255)      ,`title` varchar(255)      ,PRIMARY KEY (`id`) USING BTREE
) ;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('908040908134428', NULL, 'test3', 'e10adc3949ba59abbe56e057f20f883e', NULL, '2022-09-08 00:00:00', NULL, NULL, NULL);
INSERT INTO `teacher` VALUES ('908093220151940', '王五', 'test', 'e10adc3949ba59abbe56e057f20f883e', '男', '2002-08-07 00:00:00', '软件工程', '博士', '讲师');
INSERT INTO `teacher` VALUES ('908120040334668', '张三', 'test1', 'e10adc3949ba59abbe56e057f20f883e', '男', '2022-09-08 00:00:00', '电子信息', '硕士', '副教授');

