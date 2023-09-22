
### DB 구조
CREATE TABLE `lecture`.`tb_lecture`
(
    `lecture_no`         int          NOT NULL AUTO_INCREMENT COMMENT '강의 번호',
    `lecturer`           varchar(50)  NOT NULL COMMENT '강연자',
    `lecture_room`        varchar(30)  NOT NULL   COMMENT '강의실',
    `description`        varchar(500) COMMENT '강의 설명',
    `capacity`           int          NOT NULL  COMMENT '정원수',
    `start_date`         datetime     NOT NULL COMMENT '강의 날짜',
    `register_date`      datetime     NOT NULL COMMENT '등록일',
    PRIMARY KEY (`lecture_no`)
);

CREATE TABLE `lecture`.`tb_scrap_view_history`
(
`seq`               int          NOT NULL AUTO_INCREMENT COMMENT '',
`scrapNo`           int          NOT NULL COMMENT '스크랩 번호',
`registerDate`      datetime     NOT NULL COMMENT '등록일',
PRIMARY KEY (`seq`),
KEY idx_scrap_no (`scrapNo`)
);