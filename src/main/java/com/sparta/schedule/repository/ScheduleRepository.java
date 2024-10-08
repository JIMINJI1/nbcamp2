package com.sparta.schedule.repository;

import com.sparta.schedule.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.*;
import java.util.List;

@Repository
@RequiredArgsConstructor

public class ScheduleRepository {
    //JdbcTemplate 객체를 필드 선언
    private final JdbcTemplate jdbcTemplate;

    //1. 일정 등록
    public Schedule save(Schedule schedule) {
        //DB 저장
        // 기본 키를 반환받기 위한 객체
        KeyHolder keyHolder = new GeneratedKeyHolder();

        //SQL 쿼리
        String sql = "INSERT INTO schedule (contents, username, password, create_date, update_date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement ps = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    // Schedule 객체의 값을 PreparedStatement에 바인딩
                    ps.setString(1, schedule.getContents());
                    ps.setString(2, schedule.getUsername());
                    ps.setString(3, schedule.getPassword());
                    ps.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
                    ps.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));

                    return ps;
                },
                keyHolder);

        //DB Insert 후 받아 온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setScheduleId(id);

        return schedule;
    }

//    //2. 일정 조회 -전체
//    public List<ScheduleResponseDto> findAll() {
//        String sql = "SELECT * FROM schedule ORDER BY update_date DESC";
//
//        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
//            @Override
//            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
//                // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
//                int scheduleId = rs.getInt("schedule_id");  // 일정 ID
//                String contents = rs.getString("contents");  // 일정 내용
//                String username = rs.getString("username");  // 사용자 이름
//                Date  = rs.getDate("create_date"); // 수정일
//                return new ScheduleResponseDto(scheduleId, contents, username, createDate);
//            }
//        });
//    }

    // 2. 일정 조회 - 작성자명으로 검색 조회
    public List<Schedule> findByUsername(String username) {
        String sql = "SELECT * FROM schedule WHERE username = ? ORDER BY update_date DESC";
        return jdbcTemplate.query(sql, new Object[]{username}, new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                Schedule schedule = new Schedule();
                schedule.setScheduleId(rs.getLong("schedule_id"));  // 일정 ID
                schedule.setContents(rs.getString("contents"));     // 일정 내용
                schedule.setUsername(rs.getString("username"));     // 사용자 이름
                schedule.setCreatedAt(rs.getTimestamp("create_date").toLocalDateTime()); // 작성일
                schedule.setUpdatedAt(rs.getTimestamp("update_date").toLocalDateTime()); // 수정일
                return schedule;
            }
        });
    }


    // 2. 일정 조회 - 개별
    public Schedule findDetail(Long scheduleId) {
        String sql = "SELECT * FROM schedule WHERE schedule_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{scheduleId}, (rs, rowNum) -> {
            Schedule schedule = new Schedule();
            schedule.setScheduleId(rs.getLong("schedule_id"));
            schedule.setContents(rs.getString("contents"));
            schedule.setUsername(rs.getString("username"));
            schedule.setCreatedAt(rs.getTimestamp("create_date").toLocalDateTime());
            schedule.setUpdatedAt(rs.getTimestamp("update_date").toLocalDateTime());

            return schedule;
        });
    }


    // 비밀번호 메소드
    public String findPasswordById(Long scheduleId) {
        String sql = "SELECT password FROM schedule WHERE schedule_id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, scheduleId);
    }

    //3. 일정 수정
    // => 비밀번호 일치 시 수정 가능
    public void update(Long scheduleId, String contents, String username, String password) {
        String sql = "UPDATE schedule SET contents =?, username=? , update_date=? WHERE Schedule_id = ?";
        String sql2 = "UPDATE schedule SET contents = ?, update_date = ? WHERE schedule_id = ?";
        String sql3 = "UPDATE schedule SET username = ?, update_date = ? WHERE schedule_id = ?";

        Timestamp now = new Timestamp(System.currentTimeMillis());

        // 일정, 작성자 모두 null 아닐 경우 수정 함 => 하나만 수정 하거나 둘 다 수정 하거나

        if (StringUtils.hasText(contents) && StringUtils.hasText(username)) {
            jdbcTemplate.update(sql, contents, username, now, scheduleId);
        } else if (StringUtils.hasText(contents)) {
            jdbcTemplate.update(sql2, contents, now, scheduleId);
        } else if (StringUtils.hasText(username)) {
            jdbcTemplate.update(sql3, username, now, scheduleId);
        }
    }

    //4. 일정 삭제
    // => 비밀번호 일치 시 수정 삭제 가능
    public void delete(Long scheduleId, String password) {
        String sql = "DELETE FROM schedule WHERE Schedule_id = ?";

        jdbcTemplate.update(sql, scheduleId);
    }


    // 특정 ID 메모 조회하는 findById 메소드
    public Schedule findById(Long scheduleId) {
        // DB 조회
        String sql = "SELECT * FROM schedule WHERE schedule_id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            // 결과 존재하는지 확인
            if (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setUsername(resultSet.getString("username"));
                schedule.setContents(resultSet.getString("contents"));
                return schedule;
            } else {
                return null; // 없으면 null 반환
            }
        }, scheduleId);
    }

}
