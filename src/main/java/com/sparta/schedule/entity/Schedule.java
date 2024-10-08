package com.sparta.schedule.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

    // 기본 생성자
    public class Schedule {
        private Long scheduleId;
        private String contents;
        private String username;
        private String password;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Schedule(String contents,  String username,  String password) {
            this.contents = contents;
            this.username = username;
            this.password = password;
        }

}
