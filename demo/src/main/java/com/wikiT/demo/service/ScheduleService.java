package com.wikiT.demo.service;

import com.wikiT.demo.domain.Group;
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

    public List<Schedule> findMySchedules(String email, List<Group> groups){
        List<Schedule> schedules = scheduleRepository.findBySpace(email)
                .orElseThrow(() -> new IllegalArgumentException("not found schedule"));

        groups.forEach(group -> {
            schedules.addAll(scheduleRepository.findByGroupIdAndSpace(group.getGroupMakerId(), "master")
                    .orElseThrow());
        });

//        schedules.sort((o1, o2) -> o1.getStartAt() - o2.getStartAt());
        return schedules;
    }


    public void delete(Long scheduleId){
        scheduleRepository.deleteById(scheduleId);
    }

}
