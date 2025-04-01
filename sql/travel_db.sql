USE travel_db;

drop table if exists board, member;

CREATE TABLE member (
            no BIGINT AUTO_INCREMENT PRIMARY KEY,
            id VARCHAR(255) UNIQUE NOT NULL,
            pw VARCHAR(255),
            name VARCHAR(20) NOT NULL,
            member_role VARCHAR(100) DEFAULT 'ROLE_USER' NOT NULL,
            member_email VARCHAR(100) UNIQUE NOT NULL,
            created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
            created_person VARCHAR(255),
            modified_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
            modified_person VARCHAR(255)

);

CREATE TABLE board (
                       board_no BIGINT AUTO_INCREMENT PRIMARY KEY,
                       board_type ENUM('NOTICE', 'POST') NOT NULL DEFAULT 'POST' COMMENT '게시글 타입: NOTICE(공지), POST(일반게시글)',
                       title VARCHAR(255) NOT NULL,
                       content TEXT NOT NULL,
                       view_count INT DEFAULT 0,
--     member_no BIGINT NOT NULL,
                       member_id VARCHAR(255) NOT NULL,
--     member_name VARCHAR(20),
                       created_date DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
                       modified_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',

--     FOREIGN KEY (member_no) REFERENCES member(no),
                       FOREIGN KEY (member_id) REFERENCES member(id)
);

