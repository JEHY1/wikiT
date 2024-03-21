package com.wikiT.demo.repository;

import com.wikiT.demo.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<List<Group>> findByMember(String member);
    Optional<List<Group>> findByGroupMakerId(Long groupMakerId);
}
