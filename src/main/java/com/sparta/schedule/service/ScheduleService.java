package com.sparta.schedule.service;

import com.sparta.schedule.dto.CreateScheduleRequestDto;
import com.sparta.schedule.dto.DeleteScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.dto.UpdateScheduleRequestDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    //ScheduleRepository 타입의 의존성을 주입받기 위한 필드 선언
    private final ScheduleRepository scheduleRepository;


    // 1. 일정 등록하기
    public ScheduleResponseDto createSchedule(CreateScheduleRequestDto requestDto) {
        //DTO에서 받은 정보로 Schedule 객체 생성
        Schedule schedule = new Schedule(
                requestDto.getContents(),
                requestDto.getUsername(),
                requestDto.getPassword()
        );

        // 레포지토리통해서 일정 DB저장
        Schedule savedSchedule = scheduleRepository.save(schedule);

        // 저장된 일정 기반으로 응답 DTO 생성
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(
                savedSchedule.getScheduleId(),
                savedSchedule.getContents(),
                savedSchedule.getUsername(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt()
        );
        return scheduleResponseDto;
    }

//    // 2. 일정 조회하기 - 전체
//    public List<ScheduleResponseDto> getAllSchedules() {
//        //DB 모든 일정 조회
//        return scheduleRepository.findAll();
//    }

    // 2. 일정 조회하기 - 작성자명
    public List<ScheduleResponseDto> getScheduleByUsername(String username) {
        // Schedule 객체 리스트
        List<Schedule> schedules = scheduleRepository.findByUsername(username);

        // Schedule 객체 리스트 -> ScheduleResponseDto 리스트
        return schedules.stream()
                .map(schedule -> new ScheduleResponseDto(
                        schedule.getScheduleId(),
                        schedule.getContents(),
                        schedule.getUsername(),
                        schedule.getCreatedAt(),
                        schedule.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    //2. 일정 조회하기 - 상세
    public ScheduleResponseDto getScheduleById(Long scheduleId) {
        Schedule schedule = scheduleRepository.findDetail(scheduleId);

        return new ScheduleResponseDto(
                schedule.getScheduleId(),
                schedule.getContents(),
                schedule.getUsername(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt());
    }


    // 3. 일정 수정하기
    public ScheduleResponseDto updateSchedules(Long scheduleId, UpdateScheduleRequestDto requestDto) {
        try {
            Schedule schedule = scheduleRepository.findDetail(scheduleId);

            String password = requestDto.getPassword();

            if (schedule == null) {
                throw new IllegalArgumentException("선택한 일정이 존재하지 않습니다.");
            }

            // 비밀번호 검증
            String checkPassword = scheduleRepository.findPasswordById(scheduleId);
            if (!checkPassword.equals(password)) {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
            // 일정 업데이트
            scheduleRepository.update(scheduleId, requestDto.getContents(), requestDto.getUsername(), requestDto.getPassword());
            // 업데이트된 스케줄 다시 조회
            Schedule updatedSchedule = scheduleRepository.findDetail(scheduleId);

            // ScheduleResponseDto를 생성하여 반환
            return new ScheduleResponseDto(updatedSchedule);
        } catch (Exception e) {
            // 로그를 남기거나 적절한 예외를 던집니다.
            System.err.println("일정 수정 중 오류 발생: " + e.getMessage());
            throw new RuntimeException("서버에서 오류가 발생했습니다.", e);
        }
    }


    // 4. 일정 삭제
    public void deleteSchedule(Long scheduleId, DeleteScheduleRequestDto requestDto) {
        // DB 일정 존재하는 지 확인
        Schedule schedule = scheduleRepository.findById(scheduleId);

        String password = requestDto.getPassword();

        if (schedule == null) {
            // 일정이 존재하지 않는 경우
            throw new IllegalArgumentException("일정이 존재하지 않습니다.");
        }


        // 비밀번호 확인
        String checkPassword = scheduleRepository.findPasswordById(scheduleId);
        if (checkPassword.equals(requestDto.getPassword())) {
            scheduleRepository.delete(scheduleId, requestDto.getPassword()); // 삭제 수행
        } else {
            // 비밀번호가 틀린 경우
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }


}
