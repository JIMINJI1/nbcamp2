package com.sparta.schedule.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {
    private String contents;    //일정내용
    private String username;    //사용자이름
    private String password;    //비밀번호
}
