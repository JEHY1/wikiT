package com.wikiT.demo.repository;

import com.wikiT.demo.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<List<Schedule>> findByGroupIdAndSpace(Long groupId, String space);
    Optional<List<Schedule>> findByGroupIdAndSpaceAndStatus(Long groupID, String space, String status);
    Optional<List<Schedule>> findBySpaceAndStatus(String space, String status);
    Optional<List<Schedule>> findBySpace(String space);
}
