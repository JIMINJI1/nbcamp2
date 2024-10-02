package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.repository.ScheduleRepository;
import com.sparta.schedule.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //RESTful API의 컨트롤러
@RequestMapping("/api") //기본 URL 경로를 설정

public class ScheduleController {
    // ScheduleService 타입의 의존성을 주입받기 위한 필드 선언
    private final ScheduleService scheduleService;
    private final ScheduleRepository scheduleRepository;

    // 생성자 주입을 통해 ScheduleService 객체를 초기화
    public ScheduleController(ScheduleService scheduleService, ScheduleRepository scheduleRepository) {
        // ScheduleService 객체를 클래스의 필드에 할당
        this.scheduleService = scheduleService;
        this.scheduleRepository = scheduleRepository;
    }

    //1. 일정 등록
    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
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
    public ScheduleResponseDto getScheduleById(@PathVariable int scheduleId) {
        return scheduleService.getScheduleById(scheduleId);
    }



    // 일정 수정
    @PutMapping("/schedule/{scheduleId}")
    public int updateSchedule(@PathVariable int scheduleId, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedules(scheduleId, requestDto);
    }

    // 일정 삭제
    @DeleteMapping("/schedule/{scheduleId}")
    public int deleteSchedule(@PathVariable int scheduleId, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.deleteSchedule(scheduleId, requestDto);
    }
}
