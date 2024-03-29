package com.wikiT.demo.service;

import com.wikiT.demo.domain.Schedule;
import com.wikiT.demo.dto.AddScheduleRequest;
import com.wikiT.demo.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Schedule submit(AddScheduleRequest request){

        return scheduleRepository.save(request.toEntity());
    }

}
