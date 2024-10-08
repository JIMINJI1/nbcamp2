package com.sparta.schedule.controller;

import com.sparta.schedule.dto.CreateScheduleRequestDto;
import com.sparta.schedule.dto.DeleteScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.dto.UpdateScheduleRequestDto;
import com.sparta.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController //RESTful API의 컨트롤러

@RequestMapping("/api") //기본 URL 경로를 설정

public class ScheduleController {
    // ScheduleService 타입의 의존성을 주입받기 위한 필드 선언
    private final ScheduleService scheduleService;

    //1. 일정 등록
    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody CreateScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    // 일정 조회 - 전체
//    @GetMapping("/schedule")
//    public List<ScheduleResponseDto> getAllSchedules() {
//        return scheduleService.getAllSchedules();
//    }

    // 일정조회 - 작성자명
    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getScheduleByUsername(@RequestParam String username) {
        return scheduleService.getScheduleByUsername(username);
    }

    //일정 조회 - 상세
    @GetMapping("/schedule/{scheduleId}")
    public ScheduleResponseDto getScheduleById(@PathVariable Long scheduleId) {
        return scheduleService.getScheduleById(scheduleId);
    }


    // 일정 수정
    @PutMapping("/schedule/{scheduleId}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long scheduleId, @RequestBody UpdateScheduleRequestDto requestDto) {
        return scheduleService.updateSchedules(scheduleId, requestDto);
    }


    // 일정 삭제
    @DeleteMapping("/schedule/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId, @RequestBody DeleteScheduleRequestDto requestDto) {
        scheduleService.deleteSchedule(scheduleId, requestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }


}
