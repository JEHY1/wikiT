package com.wikiT.demo.service;

import com.wikiT.demo.domain.Schedule;
import com.wikiT.demo.dto.AddScheduleRequest;
import com.wikiT.demo.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Schedule submit(AddScheduleRequest request){

        return scheduleRepository.save(request.toEntity());
    }

    public List<Schedule> findByGroupIdAndSpace(Long groupId, String space){

        return scheduleRepository.findByGroupIdAndSpace(groupId, space)
                .orElseThrow(() -> new IllegalArgumentException("not found schedule"));
    }

}
