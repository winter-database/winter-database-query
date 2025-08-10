DROP TABLE IF EXISTS query_filter;
CREATE TABLE query_filter
(
    id               int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    query_id         int(10) unsigned NOT NULL DEFAULT '0' COMMENT '查询主键',
    group_id         int(10) unsigned NOT NULL DEFAULT '0' COMMENT '分组主键',
    parent_id        int(10) unsigned NOT NULL DEFAULT '0' COMMENT '父条件主键',
    table_name       varchar(64)      NOT NULL DEFAULT '' COMMENT '表名',
    column_name      varchar(64)      NOT NULL DEFAULT '' COMMENT '字段名',
    func_type        int(10) unsigned NOT NULL DEFAULT '0' COMMENT '函数类型',
    filter_type      int(10) unsigned NOT NULL DEFAULT '0' COMMENT '条件类型',
    logical_operator int(10) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑与或',
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
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  CHARACTER SET = utf8mb4 COMMENT = '条件';

DROP TABLE IF EXISTS query_filter_expression;
CREATE TABLE query_filter_expression
(
    id                int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    query_id          int(10) unsigned NOT NULL DEFAULT '0' COMMENT '查询主键',
    filter_id         int(10) unsigned NOT NULL DEFAULT '0' COMMENT '条件主键',
    expression_code   int(10) unsigned NOT NULL DEFAULT '0' COMMENT '编码',
    parameter_name    varchar(64)      NOT NULL DEFAULT '' COMMENT '参数名',
    value_string      varchar(255)     NULL     DEFAULT NULL COMMENT '字符串',
    value_integer     int(10)          NULL     DEFAULT NULL COMMENT '整数',
    value_long        bigint(20)       NULL     DEFAULT NULL COMMENT '长整数',
    value_big_decimal decimal(14, 3)   NULL     DEFAULT NULL COMMENT '小数',
    value_date        datetime         NULL     DEFAULT NULL COMMENT '日期时间',
    update_version    int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新版本',
    create_date       datetime         NULL     DEFAULT NULL COMMENT '创建时间',
    last_update       datetime         NULL     DEFAULT NULL COMMENT '更新时间',
    internal_recycle  int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Recycled ? id : 0',
    internal_binlog   int(10) unsigned NOT NULL DEFAULT '0',
    internal_remark   varchar(255)     NOT NULL DEFAULT '',
    internal_insert   datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    internal_update   datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    internal_delete   datetime         NULL     DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  CHARACTER SET = utf8mb4 COMMENT = '表达式';

DROP TABLE IF EXISTS query_filter_in;
CREATE TABLE query_filter_in
(
    id                   int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    query_id             int(10) unsigned NOT NULL DEFAULT '0' COMMENT '查询主键',
    filter_id            int(10) unsigned NOT NULL DEFAULT '0' COMMENT '条件主键',
    is_not               int(10) unsigned NOT NULL DEFAULT '0' COMMENT '取反？',
    value_sub_query_id   int(10) unsigned NOT NULL DEFAULT '0' COMMENT '子查询主键',
    value_sub_query_name varchar(64)      NOT NULL DEFAULT '' COMMENT '子查询名',
    parameter_name       varchar(64)      NOT NULL DEFAULT '' COMMENT '参数名',
    update_version       int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新版本',
    create_date          datetime         NULL     DEFAULT NULL COMMENT '创建时间',
    last_update          datetime         NULL     DEFAULT NULL COMMENT '更新时间',
    internal_recycle     int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Recycled ? id : 0',
    internal_binlog      int(10) unsigned NOT NULL DEFAULT '0',
    internal_remark      varchar(255)     NOT NULL DEFAULT '',
    internal_insert      datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    internal_update      datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    internal_delete      datetime         NULL     DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  CHARACTER SET = utf8mb4 COMMENT = '在列表中';

DROP TABLE IF EXISTS query_filter_in_value;
CREATE TABLE query_filter_in_value
(
    id                int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    query_id          int(10) unsigned NOT NULL DEFAULT '0' COMMENT '查询主键',
    filter_id         int(10) unsigned NOT NULL DEFAULT '0' COMMENT '条件主键',
    filter_in_id      int(10) unsigned NOT NULL DEFAULT '0' COMMENT '列表主键',
    value_string      varchar(255)     NULL     DEFAULT NULL COMMENT '字符串',
    value_integer     int(10)          NULL     DEFAULT NULL COMMENT '整数',
    value_long        bigint(20)       NULL     DEFAULT NULL COMMENT '长整数',
    value_big_decimal decimal(14, 3)   NULL     DEFAULT NULL COMMENT '小数',
    value_date        datetime         NULL     DEFAULT NULL COMMENT '日期时间',
    show_priority     int(10) unsigned NOT NULL DEFAULT '10000' COMMENT '排序',
    update_version    int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新版本',
    create_date       datetime         NULL     DEFAULT NULL COMMENT '创建时间',
    last_update       datetime         NULL     DEFAULT NULL COMMENT '更新时间',
    internal_recycle  int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Recycled ? id : 0',
    internal_binlog   int(10) unsigned NOT NULL DEFAULT '0',
    internal_remark   varchar(255)     NOT NULL DEFAULT '',
    internal_insert   datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    internal_update   datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    internal_delete   datetime         NULL     DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  CHARACTER SET = utf8mb4 COMMENT = '列表值';

DROP TABLE IF EXISTS query_filter_null;
CREATE TABLE query_filter_null
(
    id               int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    query_id         int(10) unsigned NOT NULL DEFAULT '0' COMMENT '查询主键',
    filter_id        int(10) unsigned NOT NULL DEFAULT '0' COMMENT '条件主键',
    is_not           int(10) unsigned NOT NULL DEFAULT '0' COMMENT '取反？',
    update_version   int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新版本',
    create_date      datetime         NULL     DEFAULT NULL COMMENT '创建时间',
    last_update      datetime         NULL     DEFAULT NULL COMMENT '更新时间',
    internal_recycle int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Recycled ? id : 0',
    internal_binlog  int(10) unsigned NOT NULL DEFAULT '0',
    internal_remark  varchar(255)     NOT NULL DEFAULT '',
    internal_insert  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    internal_update  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    internal_delete  datetime         NULL     DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  CHARACTER SET = utf8mb4 COMMENT = '空';

DROP TABLE IF EXISTS query_filter_range;
CREATE TABLE query_filter_range
(
    id                     int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    query_id               int(10) unsigned NOT NULL DEFAULT '0' COMMENT '查询主键',
    filter_id              int(10) unsigned NOT NULL DEFAULT '0' COMMENT '条件主键',
    is_include_lower       int(10) unsigned NOT NULL DEFAULT '0' COMMENT '包含开始？',
    is_include_upper       int(10) unsigned NOT NULL DEFAULT '0' COMMENT '包含结束？',
    from_parameter_name    varchar(64)      NOT NULL DEFAULT '' COMMENT '开始参数名',
    from_value_string      varchar(255)     NULL     DEFAULT NULL COMMENT '开始字符串',
    from_value_integer     int(10)          NULL     DEFAULT NULL COMMENT '开始整数',
    from_value_long        bigint(20)       NULL     DEFAULT NULL COMMENT '开始长整数',
    from_value_big_decimal decimal(14, 3)   NULL     DEFAULT NULL COMMENT '开始小数',
    from_value_date        datetime         NULL     DEFAULT NULL COMMENT '开始日期时间',
    to_parameter_name      varchar(64)      NOT NULL DEFAULT '' COMMENT '结束参数名',
    to_value_string        varchar(255)     NULL     DEFAULT NULL COMMENT '结束字符串',
    to_value_integer       int(10)          NULL     DEFAULT NULL COMMENT '结束整数',
    to_value_long          bigint(20)       NULL     DEFAULT NULL COMMENT '结束长整数',
    to_value_big_decimal   decimal(14, 3)   NULL     DEFAULT NULL COMMENT '结束小数',
    to_value_date          datetime         NULL     DEFAULT NULL COMMENT '结束日期时间',
    update_version         int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新版本',
    create_date            datetime         NULL     DEFAULT NULL COMMENT '创建时间',
    last_update            datetime         NULL     DEFAULT NULL COMMENT '更新时间',
    internal_recycle       int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Recycled ? id : 0',
    internal_binlog        int(10) unsigned NOT NULL DEFAULT '0',
    internal_remark        varchar(255)     NOT NULL DEFAULT '',
    internal_insert        datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    internal_update        datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    internal_delete        datetime         NULL     DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  CHARACTER SET = utf8mb4 COMMENT = '范围';

DROP TABLE IF EXISTS query_filter_wildcard;
CREATE TABLE query_filter_wildcard
(
    id                int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    query_id          int(10) unsigned NOT NULL DEFAULT '0' COMMENT '查询主键',
    filter_id         int(10) unsigned NOT NULL DEFAULT '0' COMMENT '条件主键',
    is_not            int(10) unsigned NOT NULL DEFAULT '0' COMMENT '取反？',
    wildcard_code     int(10) unsigned NOT NULL DEFAULT '0' COMMENT '编码',
    parameter_name    varchar(64)      NOT NULL DEFAULT '' COMMENT '参数名',
    value_string      varchar(255)     NULL     DEFAULT NULL COMMENT '字符串',
    value_integer     int(10)          NULL     DEFAULT NULL COMMENT '整数',
    value_long        bigint(20)       NULL     DEFAULT NULL COMMENT '长整数',
    value_big_decimal decimal(14, 3)   NULL     DEFAULT NULL COMMENT '小数',
    value_date        datetime         NULL     DEFAULT NULL COMMENT '日期时间',
    update_version    int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新版本',
    create_date       datetime         NULL     DEFAULT NULL COMMENT '创建时间',
    last_update       datetime         NULL     DEFAULT NULL COMMENT '更新时间',
    internal_recycle  int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Recycled ? id : 0',
    internal_binlog   int(10) unsigned NOT NULL DEFAULT '0',
    internal_remark   varchar(255)     NOT NULL DEFAULT '',
    internal_insert   datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    internal_update   datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    internal_delete   datetime         NULL     DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  CHARACTER SET = utf8mb4 COMMENT = '模糊匹配';
