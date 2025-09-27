DROP TABLE IF EXISTS xquery;
CREATE TABLE xquery
(
    id                int(10) unsigned    NOT NULL AUTO_INCREMENT COMMENT '主键',
    query_name        varchar(64)         NOT NULL DEFAULT '' COMMENT '名称',
    is_distinct       int(10) unsigned    NOT NULL DEFAULT '0' COMMENT '去重？',
    is_asterisk       int(10) unsigned    NOT NULL DEFAULT '0' COMMENT '全字段？',
    is_parameter_name int(10) unsigned    NOT NULL DEFAULT '0' COMMENT '忽略参数名？',
    from_table        varchar(64)         NOT NULL DEFAULT '' COMMENT '表名',
    page_offset       bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '开始行数',
    page_limit        int(10) unsigned    NOT NULL DEFAULT '0' COMMENT '每页行数',
    query_description varchar(255)        NOT NULL DEFAULT '' COMMENT '描述',
    query_remark      varchar(255)        NOT NULL DEFAULT '' COMMENT '备注',
    show_priority     int(10) unsigned    NOT NULL DEFAULT '10000' COMMENT '排序',
    update_version    int(10) unsigned    NOT NULL DEFAULT '0' COMMENT '更新版本',
    create_date       datetime            NULL     DEFAULT NULL COMMENT '创建时间',
    last_update       datetime            NULL     DEFAULT NULL COMMENT '更新时间',
    internal_recycle  int(10) unsigned    NOT NULL DEFAULT '0' COMMENT 'Recycled ? id : 0',
    internal_binlog   int(10) unsigned    NOT NULL DEFAULT '0',
    internal_remark   varchar(255)        NOT NULL DEFAULT '',
    internal_insert   datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    internal_update   datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    internal_delete   datetime            NULL     DEFAULT NULL,
    PRIMARY KEY (id),
    KEY KEY_SHOW_PRIORITY (internal_recycle, show_priority)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  CHARACTER SET = utf8mb4 COMMENT = '查询';
