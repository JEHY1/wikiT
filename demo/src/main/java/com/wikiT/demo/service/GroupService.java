package com.wikiT.demo.service;

import com.wikiT.demo.domain.Group;
import com.wikiT.demo.domain.GroupMaker;
import com.wikiT.demo.dto.InviteRequest;
import com.wikiT.demo.dto.AddGroupRequest;
import com.wikiT.demo.repository.GroupMakerRepository;
import com.wikiT.demo.repository.GroupRepository;
import com.wikiT.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMakerRepository groupMakerRepository;
    private final UserRepository userRepository;


    public GroupMaker submit(AddGroupRequest request){

        GroupMaker groupMaker = groupMakerRepository.save(request.toEntity());
        groupRepository.save(request.toEntityGroup(groupMaker.getId()));
        return groupMaker;
    }

    public List<Group> findByMember(String member){
        return groupRepository.findByMember(member)
                .orElseThrow(() -> new IllegalArgumentException("not found member"));
    }

    public List<Group> findByGroupMakerId(Long groupMakerId){
        return groupRepository.findByGroupMakerId(groupMakerId)
                .orElseThrow(() -> new IllegalArgumentException("not found group"));
    }

    public String findGroupName(Long groupMakerId){
        return groupMakerRepository.findById(groupMakerId).orElseThrow(() -> new IllegalArgumentException("not found group Id")).getGroupName();
    }

    public Group invite(InviteRequest request){

        System.err.println("email : " + request.getEmail());

        userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("not found email"));

        return groupRepository.save(request.toEntity());
    }
}