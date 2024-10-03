package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

import java.sql.Date;

//ResponseDto : 일정 조회 할 때 필요한 필드
@Getter
public class ScheduleResponseDto {
    private int scheduleId;  // 일정 ID
    private String contents;  // 일정 내용
    private String username;  // 사용자 이름
    private Date createDate; // 작정일
    private Date updateDate; // 수정일

    // Schedule 객체를 인자로 받는 생성자
    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.username = schedule.getUsername();
        this.contents = schedule.getContents();
        this.createDate = schedule.getCreateDate();
        this.updateDate = schedule.getUpdateDate();
    }

    // scheduleId, contents, username, createDate,updateDate을 인자로 받는 생성자 추가
    public ScheduleResponseDto(int scheduleId, String contents, String username, Date createDate,Date updateDate) {
        this.scheduleId = scheduleId;
        this.contents = contents;
        this.username = username;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

}
