package com.wikiT.demo.service;

import com.wikiT.demo.domain.Group;
import com.wikiT.demo.domain.Schedule;
import com.wikiT.demo.dto.AddScheduleRequest;
import com.wikiT.demo.dto.UpdateScheduleRequest;
import com.wikiT.demo.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Transactional
    public List<Schedule> findMySchedules(String email, List<Group> groups){
        List<Schedule> updateSchedules = scheduleRepository.findBySpace(email)
                .orElseThrow(() -> new IllegalArgumentException("not found schedule"));

        groups.forEach(group -> {
            updateSchedules.addAll(scheduleRepository.findByGroupIdAndSpace(group.getGroupMakerId(), "master")
                    .orElseThrow());
        });

        LocalDateTime now = LocalDateTime.now();

        updateSchedules.forEach(schedule -> {
            if(schedule.getEndAt() != null && schedule.getStatus().equals("run") && !schedule.getEndAt().isAfter(now)){
                schedule.update("timeOut");
            }
        });

        List<Schedule> schedules = scheduleRepository.findBySpaceAndStatus(email, "run")
                .orElseThrow(() -> new IllegalArgumentException("not found Schedule"));
        groups.forEach(group -> {
            schedules.addAll(scheduleRepository.findByGroupIdAndSpaceAndStatus(group.getGroupMakerId(), "master", "run")
                    .orElseThrow());
        });


        schedules.sort((o1, o2) -> {
            if(o1.getEndAt() != null && o2.getEndAt() != null){
                if(o1.getEndAt().isAfter(o2.getEndAt())){
                    return 1;
                }
                else{
                    return -1;
                }
            }
            else if(o1.getEndAt() == null && o2.getEndAt() != null){
                return 1;
            }
            else{
                return -1;
            }
        });

        return schedules;
    }

    public List<Schedule> findMyScheduleOthers(String email, List<Group> groups){
        List<Schedule> schedules = scheduleRepository.findBySpaceAndStatus(email, "timeOut")
                .orElseThrow();
        schedules.addAll(scheduleRepository.findBySpaceAndStatus(email, "complete")
                .orElseThrow());

        groups.forEach(group -> {
            schedules.addAll(scheduleRepository.findByGroupIdAndSpaceAndStatus(group.getGroupMakerId(), "master", "timeOut")
                    .orElseThrow());
            schedules.addAll(scheduleRepository.findByGroupIdAndSpaceAndStatus(group.getGroupMakerId(), "master", "complete")
                    .orElseThrow());
        });

        schedules.sort(((o1, o2) -> {
            if(o1.getEndAt() != null && o2.getEndAt() != null){
                if(o1.getEndAt().isAfter(o2.getEndAt())){
                    return 1;
                }
                else{
                    return -1;
                }
            }
            else if(o1.getEndAt() == null && o2.getEndAt() != null){
                return 1;
            }
            else{
                return -1;
            }
        }));

        return schedules;
    }

    public Schedule findById(Long id){
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found schedule"));
    }


    public void delete(Long scheduleId){
        scheduleRepository.deleteById(scheduleId);
    }

    @Transactional
    public Schedule update(UpdateScheduleRequest request){
        Schedule schedule = scheduleRepository.findById(request.getScheduleId())
                .orElseThrow(() -> new IllegalArgumentException("not found schedule"));

        schedule.update(request);
        return schedule;
    }

    @Transactional
    public Schedule update(Long scheduleId){
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("not found schedule"));

        schedule.update("complete");
        return schedule;
    }

}
