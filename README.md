
### DB 구조
CREATE TABLE `lecture`.`tb_lecture`
(
    `lecture_no`         int          NOT NULL AUTO_INCREMENT COMMENT '강의 번호',
    `lecturer`           varchar(50)  NOT NULL COMMENT '강연자',
    `lecture_room`       varchar(30)  NOT NULL   COMMENT '강의실',
    `description`        varchar(500) COMMENT '강의 설명',
    `capacity`           int          NOT NULL  COMMENT '정원수',
    `start_date`         datetime     NOT NULL COMMENT '강의 날짜',
    `register_date`      datetime     NOT NULL COMMENT '등록일',
    PRIMARY KEY (`lecture_no`)
);

CREATE TABLE `lecture`.`tb_lecture_reservation`
(
    `reservation_no`     int          NOT NULL AUTO_INCREMENT COMMENT '예약 번호',
    `lecture_no`         int          NOT NULL COMMENT '강의 번호',
    `employee_id`        varchar(5)   NOT NULL  COMMENT '사번',
    `register_date`       datetime     NOT NULL COMMENT '등록일',
    `update_date`         datetime     NULL COMMENT '수정일',
    `is_canceled`          boolean     NOT NULL COMMENT '취소 여부',
    PRIMARY KEY (`reservation_no`),
    UNIQUE KEY UK_LECTURE_EMPLOYEE (`lecture_no`, `employee_id`)
);