package com.wikiT.demo.service;

import com.wikiT.demo.domain.Group;
import com.wikiT.demo.domain.GroupMaker;
import com.wikiT.demo.domain.InviteMessage;
import com.wikiT.demo.dto.InviteRequest;
import com.wikiT.demo.dto.AddGroupRequest;
import com.wikiT.demo.repository.GroupMakerRepository;
import com.wikiT.demo.repository.GroupRepository;
import com.wikiT.demo.repository.InviteMessageRepository;
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
    private final InviteMessageRepository inviteMessageRepository;


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

    public InviteMessage invite(InviteRequest request){

        if(duplicateMessage(request.getGroupId())){
            throw new IllegalArgumentException("duplicate");
        }
        else{
            userRepository.findByEmail(request.getInviteeEmail())
                    .orElseThrow(() -> new IllegalArgumentException("not found email"));


            return inviteMessageRepository.save(request.toEntity());
        }
    }

    public List<InviteMessage> findMessage(String email){

        return inviteMessageRepository.findByInviteeEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("no mails"));
    }

    public Boolean duplicateMessage(Long groupId){
        if(inviteMessageRepository.findByGroupId(groupId).isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }
}
