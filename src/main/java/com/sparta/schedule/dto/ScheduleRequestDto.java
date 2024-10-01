package com.sparta.schedule.dto;

import lombok.Getter;

import java.sql.Date;

// RequestDto : 일정 등록 할 때 필요한 필드
@Getter
public class ScheduleRequestDto {
    private String contents;    //일정내용
    private String username;    //사용자이름
    private String password;    //비밀번호
}
