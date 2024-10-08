-- user 테이블 생성
CREATE TABLE USER (
                      user_id bigint not null auto_increment primary key comment '사용자아이디',
                      name varchar(100) not null comment '이름',
                      email varchar(100) not null comment '이메일',
                      password varchar(255) not null comment '계정 비밀번호',
                      signup_date date not null comment '등록일',
                      update_date date	null comment '수정일'
);

-- schedule 테이블 생성
CREATE TABLE SCHEDULE (
                          schedule_id bigint not null  auto_increment primary key comment '일정아이디',
                          username varchar(100) not null comment '작성자',
                          contents text not null comment '내용',
                          password varchar(255) not null comment '글 비밀번호',
                          create_date  timestamp not null comment '생성일',
                          update_date timestamp not null comment '수정일',
                          user_id BIGINT not null comment '사용자아이디',
                          foreign key (user_id) references USER(user_id) on delete cascade
);

-- 임시로 user_id null 허용
ALTER TABLE schedule MODIFY user_id INT NULL;

-- 더미 데이터 생성
INSERT INTO schedule (schedule_id, contents, username, password, create_date, update_date)
VALUES
    (1, '병원가기', '홍길동', '123', '2024-01-01 10:00:00', '2024-01-01 10:00:00'),
    (2, '회의 참석', '김철수', '123', '2024-01-01 10:00:00', '2024-01-01 10:00:00'),
    (3, '친구 만나기', '김영희', '123', '2024-02-03 10:00:00', '2024-02-03 10:00:00'),
    (4, '운동 하기', '홍길동', '123', '2024-01-15 12:00:00', '2024-01-15 12:00:00'),
    (5, '독서 모임', '김철수', '123', '2024-01-04 08:00:00', '2024-01-04 08:00:00'),
    (6, '대청소 하기', '김영희', '123', '2024-08-03 10:00:00', '2024-08-03 10:00:00'),
    (7, '산책 하기', '홍길동', '123', '2024-08-01 07:00:00', '2024-08-01 07:00:00'),
    (8, '마트 가기', '김철수', '123', '2024-07-02 11:00:00', '2024-07-02 11:00:00'),
    (9, '세차 하기', '김영희', '123', '2024-06-03 10:00:00', '2024-06-03 10:00:00');

