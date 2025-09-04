/*
 Navicat Premium Dump SQL

 Source Server         : 172.22.0.100
 Source Server Type    : MySQL
 Source Server Version : 90300 (9.3.0)
 Source Host           : 172.22.0.100:3306
 Source Schema         : smart_task

 Target Server Type    : MySQL
 Target Server Version : 90300 (9.3.0)
 File Encoding         : 65001

 Date: 04/09/2025 16:55:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码，SHA256加密结果',
  `salt` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用于密码哈希的盐值',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态，1=启用，0=禁用',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理员表';

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` (`id`, `username`, `password`, `salt`, `status`, `created_at`, `updated_at`) VALUES (1, 'admin', 'ab05cfba685e6e100e9aa34736e64d67aba2279078093c712f62b48b61512e9a', 'f3e5c9a7d2b1h0i4', 1, '2025-09-04 03:50:18', '2025-09-04 06:55:33');
COMMIT;

-- ----------------------------
-- Table structure for schedules
-- ----------------------------
DROP TABLE IF EXISTS `schedules`;
CREATE TABLE `schedules` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '调度计划唯一ID',
  `task_id` int NOT NULL COMMENT '关联的任务ID',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '对该调度计划的描述，如“每日备份”',
  `while_type` tinyint NOT NULL DEFAULT '0' COMMENT '类型,0 一次性 1,每秒循环 2,每分循环 3,每小时循环 4每天循环 5每周 6每月,7每年',
  `while_value` float NOT NULL DEFAULT '1' COMMENT '循环值, 与上面的类型搭配使用.默认为1',
  `run_at` datetime NOT NULL COMMENT '执行后需要计算并更新下次执行的结果',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用该调度计划，0=禁用, 1=启用',
  `missed_execution_behavior` tinyint NOT NULL COMMENT '错过的,是否马上执行\n',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `allow_run_start_time` time NOT NULL DEFAULT '00:00:00' COMMENT '自动允许开始时间',
  `allow_run_end_time` time NOT NULL DEFAULT '23:59:59' COMMENT '自动允许结束时间',
  PRIMARY KEY (`id`),
  KEY `idx_task_id` (`task_id`),
  KEY `idx_next_run_at_isenabled` (`run_at`,`enabled`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='任务调度计划表';

-- ----------------------------
-- Records of schedules
-- ----------------------------
BEGIN;
INSERT INTO `schedules` (`id`, `task_id`, `description`, `while_type`, `while_value`, `run_at`, `enabled`, `missed_execution_behavior`, `created_at`, `updated_at`, `allow_run_start_time`, `allow_run_end_time`) VALUES (1, 1, '每日运行,起床', 4, 1, '2025-09-05 07:15:00', 1, 0, '2025-09-02 10:14:53', '2025-09-02 11:43:19', '00:00:00', '23:59:59');
INSERT INTO `schedules` (`id`, `task_id`, `description`, `while_type`, `while_value`, `run_at`, `enabled`, `missed_execution_behavior`, `created_at`, `updated_at`, `allow_run_start_time`, `allow_run_end_time`) VALUES (2, 2, '天气', 4, 1, '2025-09-05 07:18:00', 1, 0, '2025-09-02 10:15:28', '2025-09-02 11:30:49', '00:00:00', '23:59:59');
INSERT INTO `schedules` (`id`, `task_id`, `description`, `while_type`, `while_value`, `run_at`, `enabled`, `missed_execution_behavior`, `created_at`, `updated_at`, `allow_run_start_time`, `allow_run_end_time`) VALUES (3, 4, '洗刷', 4, 1, '2025-09-04 21:00:00', 1, 0, '2025-09-02 10:15:28', '2025-09-02 12:51:26', '00:00:00', '23:59:59');
INSERT INTO `schedules` (`id`, `task_id`, `description`, `while_type`, `while_value`, `run_at`, `enabled`, `missed_execution_behavior`, `created_at`, `updated_at`, `allow_run_start_time`, `allow_run_end_time`) VALUES (4, 5, '睡觉', 4, 1, '2025-09-04 21:30:00', 1, 0, '2025-09-02 10:15:28', '2025-09-02 11:30:33', '00:00:00', '23:59:59');
INSERT INTO `schedules` (`id`, `task_id`, `description`, `while_type`, `while_value`, `run_at`, `enabled`, `missed_execution_behavior`, `created_at`, `updated_at`, `allow_run_start_time`, `allow_run_end_time`) VALUES (5, 3, '整点报时', 3, 1, '2025-09-04 17:00:00', 1, 0, '2025-09-04 03:32:36', '2025-09-04 08:30:58', '00:08:00', '21:00:00');
COMMIT;

-- ----------------------------
-- Table structure for task_logs
-- ----------------------------
DROP TABLE IF EXISTS `task_logs`;
CREATE TABLE `task_logs` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志唯一ID',
  `task_id` int NOT NULL COMMENT '关联的任务ID',
  `schedule_id` int NOT NULL COMMENT '触发本次执行的调度ID',
  `status` enum('RUNNING','SUCCESS','FAILURE','TIMEOUT') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '执行状态',
  `output` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '命令执行的标准输出和错误输出',
  `exit_code` int DEFAULT NULL COMMENT '主命令的退出码',
  `started_at` datetime(6) NOT NULL COMMENT '执行开始时间（精确到微秒）',
  `completed_at` datetime(6) DEFAULT NULL COMMENT '执行完成时间（精确到微秒）',
  `duration_ms` int DEFAULT NULL COMMENT '任务执行耗时（毫秒）',
  PRIMARY KEY (`id`),
  KEY `idx_task_id` (`task_id`),
  KEY `idx_status` (`status`),
  KEY `idx_started_at` (`started_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='任务执行日志表';

-- ----------------------------
-- Records of task_logs
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tasks
-- ----------------------------
DROP TABLE IF EXISTS `tasks`;
CREATE TABLE `tasks` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '任务唯一ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '任务名称，用于Web界面显示',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '任务的详细描述',
  `command` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '要执行的主命令（包含路径和参数）',
  `command_on_success` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '主命令成功后（exit code = 0）执行的命令',
  `command_on_failure` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '主命令失败后（exit code != 0）执行的命令',
  `timeout_seconds` int NOT NULL DEFAULT '3600' COMMENT '任务执行超时时间（秒），0为不限制',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用该任务，0=禁用, 1=启用',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_is_enabled` (`enabled`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='任务定义表';

-- ----------------------------
-- Records of tasks
-- ----------------------------
BEGIN;
INSERT INTO `tasks` (`id`, `name`, `description`, `command`, `command_on_success`, `command_on_failure`, `timeout_seconds`, `enabled`, `created_at`, `updated_at`) VALUES (1, '每天早上起床号', NULL, 'php /root/clock/weakup.php', NULL, NULL, 3600, 1, '2025-09-02 09:55:44', '2025-09-02 10:02:53');
INSERT INTO `tasks` (`id`, `name`, `description`, `command`, `command_on_success`, `command_on_failure`, `timeout_seconds`, `enabled`, `created_at`, `updated_at`) VALUES (2, '每天早上天气预报', NULL, 'php /root/clock/goodMorning.php', NULL, NULL, 3600, 1, '2025-09-02 09:56:05', '2025-09-02 10:03:01');
INSERT INTO `tasks` (`id`, `name`, `description`, `command`, `command_on_success`, `command_on_failure`, `timeout_seconds`, `enabled`, `created_at`, `updated_at`) VALUES (3, '每天9-21点的报时', NULL, 'php /root/clock/RightNow/RightNow.php', NULL, NULL, 3600, 1, '2025-09-02 09:56:24', '2025-09-04 08:17:05');
INSERT INTO `tasks` (`id`, `name`, `description`, `command`, `command_on_success`, `command_on_failure`, `timeout_seconds`, `enabled`, `created_at`, `updated_at`) VALUES (4, '每天晚上洗刷闹钟', NULL, 'php /root/clock/21.php', NULL, NULL, 3600, 1, '2025-09-02 09:56:43', '2025-09-02 10:03:09');
INSERT INTO `tasks` (`id`, `name`, `description`, `command`, `command_on_success`, `command_on_failure`, `timeout_seconds`, `enabled`, `created_at`, `updated_at`) VALUES (5, '每天晚上熄灯号', NULL, 'php /root/clock/goodNight.php', NULL, NULL, 3600, 1, '2025-09-02 09:56:54', '2025-09-02 10:03:16');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
