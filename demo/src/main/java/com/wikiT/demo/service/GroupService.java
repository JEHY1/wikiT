package com.wikiT.demo.service;

import com.wikiT.demo.domain.Group;
import com.wikiT.demo.domain.GroupMaker;
import com.wikiT.demo.domain.InviteMessage;
import com.wikiT.demo.dto.InviteRequest;
import com.wikiT.demo.dto.AddGroupRequest;
import com.wikiT.demo.dto.MemberExpelRequest;
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

    public GroupMaker findGroupMakerByGroupId(Long groupId){
        return groupMakerRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    public List<Group> findByMember(String member){
        return groupRepository.findByMember(member)
                .orElseThrow(() -> new IllegalArgumentException("not found member"));
    }

    public List<Group> findByGroupMakerId(Long groupMakerId){
        return groupRepository.findByGroupMakerId(groupMakerId)
                .orElseThrow(() -> new IllegalArgumentException("not found group"));
    }

    public Group findByGroupMakerIdAndMember(Long groupId, String member){
        return groupRepository.findByGroupMakerIdAndMember(groupId, member)
                .orElseThrow(() -> new IllegalArgumentException("not found group"));
    }

    public String findGroupName(Long groupMakerId){
        return groupMakerRepository.findById(groupMakerId).orElseThrow(() -> new IllegalArgumentException("not found group Id")).getGroupName();
    }

    public InviteMessage invite(InviteRequest request){

        if(duplicateMessage(request.getGroupId(), request.getInviteeEmail())){
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

    public Boolean duplicateMessage(Long groupId, String inviteeEmail){
        if(!inviteMessageRepository.findByGroupIdAndInviteeEmail(groupId, inviteeEmail).isEmpty() || !groupRepository.findByGroupMakerIdAndMember(groupId, inviteeEmail).isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    public Group accept(Long messageId){
        InviteMessage message = inviteMessageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("not found messageId"));

        inviteMessageRepository.deleteById(messageId);
        groupMakerRepository.findById(message.getGroupId())
                .orElseThrow(() -> new IllegalArgumentException("not found Group"));

        Group group = groupRepository.save(Group.builder()
                .groupMakerId(message.getGroupId())
                .groupName(message.getGroupName())
                .member(message.getInviteeEmail())
                .build()
        );

        return group;
    }

    public void denied(Long messageId){
        inviteMessageRepository.deleteById(messageId);
    }

    public void exit(Long groupMakerId, String member){
        Group group = groupRepository.findByGroupMakerIdAndMember(groupMakerId, member)
                        .orElseThrow(() -> new IllegalArgumentException("not found Group"));
        groupRepository.delete(group);
    }

    public String findConstructor(Long groupMakerId){
        GroupMaker group = groupMakerRepository.findById(groupMakerId)
                .orElseThrow(() -> new IllegalArgumentException("not found GroupMaker"));

        return group.getConstructor();
    }

    public void groupRemove(Long groupMakerId){
        List<Group> groups = groupRepository.findByGroupMakerId(groupMakerId)
                .orElseThrow(() -> new IllegalArgumentException("not found groupId"));

        System.err.println("groupSize() : " + groups.size());

        if(groups.size() == 1){
            System.err.println("remove run");
            System.err.println("groupMakerId : " + groups.get(0).getGroupMakerId());
            groupMakerRepository.deleteById(groupMakerId);
            groupRepository.delete(groups.get(0));

        }
    }

    public void expel(MemberExpelRequest request){
        Group group = groupRepository.findByGroupMakerIdAndMember(request.getGroupId(), request.getUserEmail())
                .orElseThrow(() -> new IllegalArgumentException("not found group"));
        groupRepository.delete(group);
    }
}
