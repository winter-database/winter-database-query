DROP TABLE IF EXISTS query;
CREATE TABLE query
(
    id                  int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Query Id',
    query_name          varchar(255)     NOT NULL DEFAULT '' COMMENT 'Query Name',
    is_distinct         int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'DISTINCT ? 1|YES 0|NO',
    from_table          varchar(64)      NOT NULL DEFAULT '' COMMENT 'From Table Name',
    from_sub_query_id   int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'From Sub Query Id',
    from_sub_query_name varchar(64)      NOT NULL DEFAULT '' COMMENT 'From Sub Query as Name',
    page_type           int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Page Type 1|OFFSET 2|ID_RANGE',
    page_size           int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Page Limit',
    query_description   varchar(255)     NOT NULL DEFAULT '' COMMENT 'Query Description',
    query_remark        varchar(255)     NOT NULL DEFAULT '' COMMENT 'Query Remark',
    show_priority       int(10) unsigned NOT NULL DEFAULT '10000' COMMENT 'Show Priority used for ORDER BY',
    update_version      int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Last Update Version used for Optimistic Lock',
    create_date         datetime         NULL     DEFAULT NULL COMMENT 'Create Date',
    last_update         datetime         NULL     DEFAULT NULL COMMENT 'Last Update Date',
    internal_recycle    int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Recycled ? id : 0',
    internal_binlog     int(10) unsigned NOT NULL DEFAULT '0',
    internal_remark     varchar(255)     NOT NULL DEFAULT '',
    internal_insert     datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    internal_update     datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    internal_delete     datetime         NULL     DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  CHARACTER SET = utf8mb4 COMMENT = 'Query';
