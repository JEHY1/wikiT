package com.wikiT.demo.service;

import com.wikiT.demo.domain.GroupMaker;
import com.wikiT.demo.dto.AddGroupRequest;
import com.wikiT.demo.repository.GroupMakerRepository;
import com.wikiT.demo.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMakerRepository groupMakerRepository;

    public GroupMaker submit(AddGroupRequest request){

        groupRepository.save(request.toEntityGroup());
        return groupMakerRepository.save(request.toEntity());
    }
}
