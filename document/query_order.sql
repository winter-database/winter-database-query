DROP TABLE IF EXISTS query_order;
CREATE TABLE query_order
(
    id               int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Order Id',
    query_id         int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Query Id',
    table_name       varchar(64)      NOT NULL DEFAULT '' COMMENT 'Table Name',
    column_name      varchar(64)      NOT NULL DEFAULT '' COMMENT 'Column Name',
    aggregate_func   int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Aggregate Func 1|COUNT 2|SUM 3|MAX 4|MIN 5|AVG',
    order_type       int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Order Type 1|ASC 2|DESC',
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
  CHARACTER SET = utf8mb4 COMMENT = 'Query Order';
