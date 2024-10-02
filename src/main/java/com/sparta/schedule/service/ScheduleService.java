package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    //
    private final ScheduleRepository scheduleRepository;

    //
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 1. 일정 등록하기
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        // DTO 사용하여 Schedule 객체 생성
        Schedule schedule = new Schedule(requestDto);
        // 레포지토리 통해서 일정 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);
        // 저장된 일정 사용하여 응답 dto 생성
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

//    // 2. 일정 조회하기 - 전체
//    public List<ScheduleResponseDto> getAllSchedules() {
//        //DB 모든 일정 조회
//        return scheduleRepository.findAll();
//    }

    // 2. 일정 조회하기 - 작성자명
    public List<ScheduleResponseDto> getScheduleByUsername(String username) {
        return scheduleRepository.findByUsername(username);
    }

    //2. 일정 조회하기 - 상세
    public ScheduleResponseDto getScheduleById(int scheduleId) {
        return scheduleRepository.findDetail(scheduleId);
    }



    // 3. 일정 수정하기
    public int updateSchedules(int scheduleId, ScheduleRequestDto requestDto) {
        try {
            Schedule schedule = scheduleRepository.findById(scheduleId);

            if (schedule != null) {
                scheduleRepository.update(scheduleId, requestDto.getContents(), requestDto.getUsername(), requestDto.getPassword());
                return scheduleId;
            } else {
                throw new IllegalArgumentException("선택한 일정이 존재하지 않습니다.");
            }
        } catch (Exception e) {
            // 로그를 남기거나 적절한 예외를 던집니다.
            System.err.println("일정 수정 중 오류 발생: " + e.getMessage());
            throw new RuntimeException("서버에서 오류가 발생했습니다.", e);
        }
    }


    // 4. 일정 삭제
    public int deleteSchedule(int scheduleId, ScheduleRequestDto requestDto) {
        // DB 일정 존재하는 지 확인
        Schedule schedule = scheduleRepository.findById(scheduleId);

        if (schedule == null) {
            // 일정이 존재하지 않는 경우
            throw new IllegalArgumentException("일정이 존재하지 않습니다.");
        }

        // 비밀번호 확인
        String checkPassword = scheduleRepository.findPasswordById(scheduleId);
        if (checkPassword.equals(requestDto.getPassword())) {
            scheduleRepository.delete(scheduleId, requestDto.getPassword()); // 삭제 수행
            return scheduleId; // 삭제된 일정 ID 반환
        } else {
            // 비밀번호가 틀린 경우
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }


}
