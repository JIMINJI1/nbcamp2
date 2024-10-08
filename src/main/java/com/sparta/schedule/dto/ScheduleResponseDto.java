package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

//ResponseDto : 일정 조회 할 때 필요한 필드
@Getter

@AllArgsConstructor
public class ScheduleResponseDto {
    private Long scheduleId;  // 일정 ID
    private String contents;  // 일정 내용
    private String username;  // 사용자 이름
    private LocalDateTime createdAt; // 작정일
    private LocalDateTime updatedAt; // 수정일

    // Schedule 객체를 인자로 받는 생성자
    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.username = schedule.getUsername();
        this.contents = schedule.getContents();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }

}
