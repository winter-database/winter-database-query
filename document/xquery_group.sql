DROP TABLE IF EXISTS xquery_group;
CREATE TABLE xquery_group
(
    id               int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    query_id         int(10) unsigned NOT NULL DEFAULT '0' COMMENT '查询主键',
    table_name       varchar(64)      NOT NULL DEFAULT '' COMMENT '表名',
    column_name      varchar(64)      NOT NULL DEFAULT '' COMMENT '字段名',
    show_priority    int(10) unsigned NOT NULL DEFAULT '10000' COMMENT '排序',
    update_version   int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新版本',
    create_date      datetime         NULL     DEFAULT NULL COMMENT '创建时间',
    last_update      datetime         NULL     DEFAULT NULL COMMENT '更新时间',
    internal_recycle int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Recycled ? id : 0',
    internal_binlog  int(10) unsigned NOT NULL DEFAULT '0',
    internal_remark  varchar(255)     NOT NULL DEFAULT '',
    internal_insert  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    internal_update  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    internal_delete  datetime         NULL     DEFAULT NULL,
    PRIMARY KEY (id),
    KEY KEY_QUERY_ID_SHOW_PRIORITY (internal_recycle, query_id, show_priority)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  CHARACTER SET = utf8mb4 COMMENT = '分组';
