package com.wikiT.demo.repository;

import com.wikiT.demo.domain.InviteMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InviteMessageRepository extends JpaRepository<InviteMessage, Long> {

    Optional<List<InviteMessage>> findByInviteeEmail(String inviteeEmail);
    Optional<InviteMessage> findByGroupIdAndInviteeEmail(Long groupId, String inviteeEmail);
}
