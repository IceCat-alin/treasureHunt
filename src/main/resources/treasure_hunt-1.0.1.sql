-- 2018/07/31 by linying start --
ALTER TABLE `activity_statistics` ADD `is_top` tinyint(4) NOT NULL DEFAULT '0' COMMENT '置顶0否1是' AFTER `status`;
UPDATE `activity_type` SET `name` = '同城宝藏' WHERE `id` = 1;
UPDATE `activity_type` SET `name` = '签到藏点' WHERE `id` = 2;
UPDATE `activity_type` SET `name` = '寻宝大赛' WHERE `id` = 3;
DELETE FROM activity_type WHERE id > 3;
UPDATE `activity` SET `type_id` = '1';
-- 2018/07/31 by linying end --