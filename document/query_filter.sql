DROP TABLE IF EXISTS query_filter;
CREATE TABLE query_filter
(
    id               int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Filter Id',
    query_id         int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Query Id',
    group_id         int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Group Id used for HAVING, if 0 used for WHERE',
    parent_id        int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Parent Id used for Hierarchical Tree e.g. (filter AND (filter OR filter))',
    logical_operator int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Logical Operator 1|AND 2|OR',
    table_name       varchar(64)      NOT NULL DEFAULT '' COMMENT 'Table Name',
    column_name      varchar(64)      NOT NULL DEFAULT '' COMMENT 'Column Name',
    aggregate_func   int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Aggregate Func 1|COUNT 2|SUM 3|MAX 4|MIN 5|AVG',
    filter_func      int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Filter Func 1|EXPRESSION 2|IN 3|NULL 4|RANGE 5|WILDCARD',
    show_priority    int(10) unsigned NOT NULL DEFAULT '10000' COMMENT 'Show Priority used for ORDER BY',
    update_version   int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Last Update Version used for Optimistic Lock',
    create_date      datetime         NULL     DEFAULT NULL COMMENT 'Create Date',
    last_update      datetime         NULL     DEFAULT NULL COMMENT 'Last Update Date',
    internal_recycle int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Recycled ? id : 0',
    internal_binlog  int(10) unsigned NOT NULL DEFAULT '0',
    internal_remark  varchar(255)     NOT NULL DEFAULT '',
    internal_insert  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    internal_update  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    internal_delete  datetime         NULL     DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  CHARACTER SET = utf8mb4 COMMENT = 'Query Filter';

DROP TABLE IF EXISTS query_filter_expression;
CREATE TABLE query_filter_expression
(
    id                int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Filter Expression Id',
    query_id          int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Query Id',
    filter_id         int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Filter Id',
    expression_code   int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Expression Code 1|EQ 2|NE 3|GT 4|LT 5|GE 6|LE',
    value_id          int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Sub Query Id',
    value_name        varchar(64)      NOT NULL DEFAULT '' COMMENT 'Value Name used for REFERENCE PARAMETER',
    value_string      varchar(255)     NULL     DEFAULT NULL COMMENT 'Value String',
    value_integer     int(10)          NULL     DEFAULT NULL COMMENT 'Value Integer',
    value_long        bigint(20)       NULL     DEFAULT NULL COMMENT 'Value Long',
    value_big_decimal decimal(14, 3)   NULL     DEFAULT NULL COMMENT 'Value Big Decimal',
    value_date        datetime         NULL     DEFAULT NULL COMMENT 'Value Date',
    update_version    int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Last Update Version used for Optimistic Lock',
    create_date       datetime         NULL     DEFAULT NULL COMMENT 'Create Date',
    last_update       datetime         NULL     DEFAULT NULL COMMENT 'Last Update Date',
    internal_recycle  int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Recycled ? id : 0',
    internal_binlog   int(10) unsigned NOT NULL DEFAULT '0',
    internal_remark   varchar(255)     NOT NULL DEFAULT '',
    internal_insert   datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    internal_update   datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    internal_delete   datetime         NULL     DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  CHARACTER SET = utf8mb4 COMMENT = 'Query Filter Expression';

DROP TABLE IF EXISTS query_filter_in;
CREATE TABLE query_filter_in
(
    id               int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Filter In Id',
    query_id         int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Query Id',
    filter_id        int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Filter Id',
    is_not           int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'NOT IN ? 1|YES 0|NO',
    value_id         int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Sub Query Id',
    value_name       varchar(64)      NOT NULL DEFAULT '' COMMENT 'Value Name used for REFERENCE PARAMETER LIST',
    update_version   int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Last Update Version used for Optimistic Lock',
    create_date      datetime         NULL     DEFAULT NULL COMMENT 'Create Date',
    last_update      datetime         NULL     DEFAULT NULL COMMENT 'Last Update Date',
    internal_recycle int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Recycled ? id : 0',
    internal_binlog  int(10) unsigned NOT NULL DEFAULT '0',
    internal_remark  varchar(255)     NOT NULL DEFAULT '',
    internal_insert  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    internal_update  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    internal_delete  datetime         NULL     DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  CHARACTER SET = utf8mb4 COMMENT = 'Query Filter In';

DROP TABLE IF EXISTS query_filter_in_value;
CREATE TABLE query_filter_in_value
(
    id                int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Filter In Value Id',
    query_id          int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Query Id',
    filter_id         int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Filter Id',
    filter_in_id      int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Filter In Id',
    value_string      varchar(255)     NULL     DEFAULT NULL COMMENT 'Value String',
    value_integer     int(10)          NULL     DEFAULT NULL COMMENT 'Value Integer',
    value_long        bigint(20)       NULL     DEFAULT NULL COMMENT 'Value Long',
    value_big_decimal decimal(14, 3)   NULL     DEFAULT NULL COMMENT 'Value Big Decimal',
    value_date        datetime         NULL     DEFAULT NULL COMMENT 'Value Date',
    show_priority     int(10) unsigned NOT NULL DEFAULT '10000' COMMENT 'Show Priority used for ORDER BY',
    update_version    int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Last Update Version used for Optimistic Lock',
    create_date       datetime         NULL     DEFAULT NULL COMMENT 'Create Date',
    last_update       datetime         NULL     DEFAULT NULL COMMENT 'Last Update Date',
    internal_recycle  int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Recycled ? id : 0',
    internal_binlog   int(10) unsigned NOT NULL DEFAULT '0',
    internal_remark   varchar(255)     NOT NULL DEFAULT '',
    internal_insert   datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    internal_update   datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    internal_delete   datetime         NULL     DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  CHARACTER SET = utf8mb4 COMMENT = 'Query Filter In Value';

DROP TABLE IF EXISTS query_filter_null;
CREATE TABLE query_filter_null
(
    id               int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Filter Null Id',
    query_id         int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Query Id',
    filter_id        int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Filter Id',
    is_not           int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'NOT NULL ? 1|YES 0|NO',
    update_version   int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Last Update Version used for Optimistic Lock',
    create_date      datetime         NULL     DEFAULT NULL COMMENT 'Create Date',
    last_update      datetime         NULL     DEFAULT NULL COMMENT 'Last Update Date',
    internal_recycle int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Recycled ? id : 0',
    internal_binlog  int(10) unsigned NOT NULL DEFAULT '0',
    internal_remark  varchar(255)     NOT NULL DEFAULT '',
    internal_insert  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    internal_update  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    internal_delete  datetime         NULL     DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  CHARACTER SET = utf8mb4 COMMENT = 'Query Filter Null';

DROP TABLE IF EXISTS query_filter_range;
CREATE TABLE query_filter_range
(
    id                     int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Filter Range Id',
    query_id               int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Query Id',
    filter_id              int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Filter Id',
    is_include_lower       int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Include Lower ? 1|YES 0|NO',
    is_include_upper       int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Include Upper ? 1|YES 0|NO',
    from_value_id          int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'From Sub Query Id',
    from_value_name        varchar(64)      NOT NULL DEFAULT '' COMMENT 'From Value Name used for REFERENCE PARAMETER',
    from_value_string      varchar(255)     NULL     DEFAULT NULL COMMENT 'From Value String',
    from_value_integer     int(10)          NULL     DEFAULT NULL COMMENT 'From Value Integer',
    from_value_long        bigint(20)       NULL     DEFAULT NULL COMMENT 'From Value Long',
    from_value_big_decimal decimal(14, 3)   NULL     DEFAULT NULL COMMENT 'From Value Big Decimal',
    from_value_date        datetime         NULL     DEFAULT NULL COMMENT 'From Value Date',
    to_value_id            int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'To Sub Query Id',
    to_value_name          varchar(64)      NOT NULL DEFAULT '' COMMENT 'To Value Name used for REFERENCE PARAMETER',
    to_value_string        varchar(255)     NULL     DEFAULT NULL COMMENT 'To Value String',
    to_value_integer       int(10)          NULL     DEFAULT NULL COMMENT 'To Value Integer',
    to_value_long          bigint(20)       NULL     DEFAULT NULL COMMENT 'To Value Long',
    to_value_big_decimal   decimal(14, 3)   NULL     DEFAULT NULL COMMENT 'To Value Big Decimal',
    to_value_date          datetime         NULL     DEFAULT NULL COMMENT 'To Value Date',
    update_version         int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Last Update Version used for Optimistic Lock',
    create_date            datetime         NULL     DEFAULT NULL COMMENT 'Create Date',
    last_update            datetime         NULL     DEFAULT NULL COMMENT 'Last Update Date',
    internal_recycle       int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Recycled ? id : 0',
    internal_binlog        int(10) unsigned NOT NULL DEFAULT '0',
    internal_remark        varchar(255)     NOT NULL DEFAULT '',
    internal_insert        datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    internal_update        datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    internal_delete        datetime         NULL     DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  CHARACTER SET = utf8mb4 COMMENT = 'Query Filter Range';

DROP TABLE IF EXISTS query_filter_wildcard;
CREATE TABLE query_filter_wildcard
(
    id               int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Filter Wildcard Id',
    query_id         int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Query Id',
    filter_id        int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Filter Id',
    is_not           int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'NOT LIKE ? 1|YES 0|NO',
    wildcard_code    int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Wildcard Code 1|CONTAINS 2|STARTS 3|ENDS',
    value_id         int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Sub Query Id',
    value_name       varchar(64)      NOT NULL DEFAULT '' COMMENT 'Value Name used for REFERENCE PARAMETER',
    value_string     varchar(255)     NULL     DEFAULT NULL COMMENT 'Value String',
    update_version   int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Last Update Version used for Optimistic Lock',
    create_date      datetime         NULL     DEFAULT NULL COMMENT 'Create Date',
    last_update      datetime         NULL     DEFAULT NULL COMMENT 'Last Update Date',
    internal_recycle int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Recycled ? id : 0',
    internal_binlog  int(10) unsigned NOT NULL DEFAULT '0',
    internal_remark  varchar(255)     NOT NULL DEFAULT '',
    internal_insert  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    internal_update  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    internal_delete  datetime         NULL     DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  CHARACTER SET = utf8mb4 COMMENT = 'Query Filter Wildcard';
