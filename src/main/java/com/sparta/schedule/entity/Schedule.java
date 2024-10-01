package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor

    // 기본 생성자
    public class Schedule {
        private int scheduleId;
        private String contents;
        private String username;
        private String password;
        private Date createDate;
        private Date updateDate;

       //DTO를 사용하는 생성자
        public Schedule(ScheduleRequestDto requestDto) {
            this.contents = requestDto.getContents();
            this.username = requestDto.getUsername();
            this.password = requestDto.getPassword();
        }

}
