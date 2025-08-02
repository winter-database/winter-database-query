DROP TABLE IF EXISTS query_join;
CREATE TABLE query_join
(
    id                  int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Join Id',
    query_id            int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Query Id',
    join_table          varchar(64)      NOT NULL DEFAULT '' COMMENT 'Join Table Name',
    join_sub_query_id   int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Join Sub Query Id',
    join_sub_query_name varchar(64)      NOT NULL DEFAULT '' COMMENT 'Join Sub Query as Name',
    join_type           int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Join Type 1|INNER 2|LEFT 3|RIGHT',
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
  CHARACTER SET = utf8mb4 COMMENT = 'Query Join';

DROP TABLE IF EXISTS query_join_on;
CREATE TABLE query_join_on
(
    id               int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Join On Id',
    query_id         int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Query Id',
    join_id          int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Join Id',
    left_table       varchar(64)      NOT NULL DEFAULT '' COMMENT 'Left Table Name',
    left_column      varchar(64)      NOT NULL DEFAULT '' COMMENT 'Left Column Name',
    right_table      varchar(64)      NOT NULL DEFAULT '' COMMENT 'Right Table Name',
    right_column     varchar(64)      NOT NULL DEFAULT '' COMMENT 'Right Column Name',
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
  CHARACTER SET = utf8mb4 COMMENT = 'Query Join On';
